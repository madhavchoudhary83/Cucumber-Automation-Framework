package pageObjects.cp_PO;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CP_NewsAndFeatures_PO extends BasePage {

    private @FindBy(xpath = "//h3[text()='NEWS']")
    WebElement newsPage_Header;
    private @FindBy(xpath = "//h3[text()='VIDEOS']//ancestor::div[@class='ColumnsComponents_container__YMzra']")
    WebElement videos_Feed_Section;
    private @FindBy(xpath = "//h3[text()='VIDEOS']//ancestor::div[@class='ColumnsComponents_container__YMzra']//div[@data-testid='content-grid']//ul/li")
    List<WebElement> all_VideoFeeds;
    private @FindBy(xpath = "//h3[text()='VIDEOS']//ancestor::div[@class='ColumnsComponents_container__YMzra']//div[@data-testid='content-grid']//ul/li//time/span")
    List<WebElement> videoFeed_Duration;


    public CP_NewsAndFeatures_PO() {
        super();
    }

    public void validate_NewsPage_Loaded() {
        log.info("Checking if 'News & Features' is loaded !");
        waitFor(newsPage_Header);
        Assert.assertTrue(newsPage_Header.isDisplayed());
    }

    public void moveTo_VideoFeeds_Section() {
        log.info("Move to Videos feed section on New page.");
        moveToElement(videos_Feed_Section);
    }

    public void get_VideoFeeds_Details() {
        ArrayList<String> videosDuration_List = new ArrayList<>();
        for (WebElement e : videoFeed_Duration) {
            String videoDuration = e.getText();
            videosDuration_List.add(videoDuration.trim());
        }
        log.debug(videosDuration_List);

        log.info("Total Videos Feeds available on page : " + all_VideoFeeds.size());
        ArrayList<String> videos_OlderThan_3Days = (ArrayList<String>) filterList(videosDuration_List, "d", 3);
        log.info("Total Videos Feeds available on page which are older than 3 or more days : " + videos_OlderThan_3Days.size());
    }

    List<String> filterList(List<String> list, String regex, int val) {
        return list
                .stream()
                .filter(s -> s.contains(regex))
                .map(s -> s.replace("d", ""))
                .filter(s -> Integer.parseInt(s) >= val)
                .collect(Collectors.toList());
    }
}
