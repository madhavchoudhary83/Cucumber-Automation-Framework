package utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import static drivers.DriverFactory.getDriver;

public class CucumberReportHelper {

    public static void attachFile(Scenario scenario, File filename) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeMilliseconds = Long.toString(timestamp.getTime());

        File file = new File(filename.toURI());
        byte[] bytes = new byte[(int) file.length()];

        scenario.attach(bytes, "text/plain", timeMilliseconds);

    }
}
