package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.FilteHelper;

import java.util.List;

import static constants.Global_Constants.TEST_BACKUP_PATH;
import static constants.Global_Constants.TEST_OUTPUT_PATH;
import static drivers.DriverFactory.cleanupDriver;
import static drivers.DriverFactory.getDriver;

public class Hooks {
    @Before
    public void setup() {
        getDriver();
    }

    @AfterStep
    public void captureExceptionImage(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (Exception e) {
                System.out.println("Error during screenshot" + e);
            }
        }
    }

    @Before("@attachoutput")
    public void cleanUpTestOutputFiles(Scenario scenario) {
        //  TextFileHelper.deleteAllFilesFromTarget(TEST_OUTPUT_PATH);
    }

    @After("@attachoutput")
    public void attachFileToScenario(Scenario scenario) {
        try {
            //Backup Test Output to target path
            FilteHelper.copyAllFilesFromSourceToTarget(TEST_OUTPUT_PATH, TEST_BACKUP_PATH);

            List<String> filesList = FilteHelper.getFileNameFromPath(TEST_OUTPUT_PATH);
            int fileCount = 1;
            if (!filesList.isEmpty()) {
                for (String file : filesList) {
                    String filePathToAttach = FilteHelper.getAbsoulutePath(TEST_BACKUP_PATH).replace(".", "") + file;
                    String filePathToRemove = FilteHelper.getAbsoulutePath(TEST_OUTPUT_PATH).replace(".", "") + file;
                    System.out.println("File to be linked in report:" + filePathToAttach);

                    // Attach file link to scenario report
                    scenario.log("<a href='file:///" + filePathToAttach + "'>TestOutputLink" + fileCount + "</a>");

                    // Attach File to Scenario results
                    String fileData = FilteHelper.readFileData(filePathToAttach);
                    scenario.attach(fileData, "text/plain", scenario.getName());

                    //Remove file
                    System.out.println("File to be deleted from temp location:" + filePathToAttach);
                    FilteHelper.deleteFile(filePathToRemove);

                    fileCount++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error during file attachment" + e);
        }
    }

    @After(order = 1)
    public void tearDown() {
        cleanupDriver();
    }
}
