package stepDefinitions.dp2_steps;

import base.BasePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.dp2_PO.DP2_HomePage_PO;

public class DP2_HomePage_Steps extends BasePage {
    private DP2_HomePage_PO dp2_HomePage_PO;

    public DP2_HomePage_Steps(DP2_HomePage_PO dp2_HomePage_PO) {
        this.dp2_HomePage_PO = dp2_HomePage_PO;
    }

    @Given("I navigate to bulls home page")
    public void i_access_the_bulls_home_page() {
        dp2_HomePage_PO.navigateTo_DP2_HomePage();
    }

    @When("I navigate to footer section")
    public void i_navigate_to_footer_section() throws InterruptedException {
        dp2_HomePage_PO.navigateToFooterSection();
    }

    @When("I am presented with links across different sections under footer")
    public void i_am_presented_with_links_across_different_sections_under_footer() throws InterruptedException {
        dp2_HomePage_PO.processFooterLinksData();
    }


}
