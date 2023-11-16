package stepDefinitions.cp_steps;

import base.BasePage;
import io.cucumber.java.en.And;
import pageObjects.cp_PO.CP_NewsAndFeatures_PO;

public class CP_NewsAndFeatures_Steps extends BasePage {
    private CP_NewsAndFeatures_PO cp_NewsAndFeatures_PO;

    public CP_NewsAndFeatures_Steps(CP_NewsAndFeatures_PO cp_NewsAndFeatures_PO) {
        this.cp_NewsAndFeatures_PO = cp_NewsAndFeatures_PO;
    }

    @And("I should be presented with News Page")
    public void i_should_be_presented_with_news_page() {
        cp_NewsAndFeatures_PO.validate_NewsPage_Loaded();
    }

    @And("I should be presented with Video Feeds on page")
    public void i_should_be_presented_with_video_feeds_on_page() {
        cp_NewsAndFeatures_PO.moveTo_VideoFeeds_Section();
        cp_NewsAndFeatures_PO.get_VideoFeeds_Details();
    }
}
