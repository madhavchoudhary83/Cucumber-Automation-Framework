package pageObjects.dp2_PO;

import base.BasePage;
import constants.Global_Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FilteHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static constants.Global_Constants.TEST_OUTPUT_PATH;

public class DP2_HomePage_PO extends BasePage {
    private @FindBy(xpath = "//nav[@aria-label='Global NBA Navigation']")
    WebElement top_navBar;
    private @FindBy(xpath = "//nav[@aria-label='Chicago Bulls navigation']")
    WebElement bulls_navBar;
    private @FindBy(xpath = "//footer//div/nav[1]")
    WebElement footer_section;
    private @FindBy(xpath = "//footer//a[@href]")
    List<WebElement> footer_links;
    private @FindBy(xpath = "//div[@id='onetrust-button-group']//button[@id='onetrust-accept-btn-handler']")
    WebElement footer_cookiePrompt;

    public DP2_HomePage_PO() {
        super();
    }

    public void navigateTo_DP2_HomePage() {
        navigateTo_URL(Global_Constants.DP2_HOMEPAGE_URL);
        waitFor(top_navBar);
        waitFor(bulls_navBar);
    }

    public void navigateToFooterSection() throws InterruptedException {
        moveToElement(footer_section);
        handleCookiePrompt();
    }

    public void handleCookiePrompt() throws InterruptedException {
        try {
            waitFor(footer_cookiePrompt);
            waitForWebElementAndClick(footer_cookiePrompt);
            wait(5000);
        } catch (Exception e) {
            // do nothing if the prompt doesn't show up
        }
    }

    public void processFooterLinksData(){
        List<String> linksData = getLinksDataFromFooter();
        FilteHelper.writeToFile(linksData, TEST_OUTPUT_PATH + "TC4_FooterlinkDetails.csv");
        List<String> duplicates = filterDuplicateLinks(linksData);
        if (duplicates.size() > 0) {
            duplicates.add(0,"List of Duplicate Links in Footer");
            FilteHelper.writeToFile(duplicates, TEST_OUTPUT_PATH + "TC4_DuplicateFooterlinksList.txt");
        } else {
            log.info("No duplicate links found in the footer sections of Bulls home page");
            duplicates.add(0,"No Duplicate Links found in footer.");
        }
        log.info("Following duplicate links found in the footer sections of Bulls home page :" + "\n" + duplicates.toString());
    }

    public List<String> filterDuplicateLinks(List<String> mainList) {
        Set<String> elements = new HashSet<String>();
        return mainList.stream()
                .map(s -> s.split(",")[0])
                .filter(s -> !elements.add(s))
                .collect(Collectors.toList());
    }

    public List<String> getLinksDataFromFooter() {
        List<String> linksData = new ArrayList<>();

        for (int i = 1; i <= footer_links.size(); i++) {
            String footerLink_loc = "(//footer//a[@href])[" + i + "]";
            String href = "";
            String linkText = "";

            href = gelElementText(footerLink_loc);
            linkText = gelElementAttributeValue(footerLink_loc, "href");
           // log.debug( i + ". LinkText:" + linkText + "| Link:" + href);
            linksData.add(linkText + "," + href);
        }

        return linksData;
    }

}
