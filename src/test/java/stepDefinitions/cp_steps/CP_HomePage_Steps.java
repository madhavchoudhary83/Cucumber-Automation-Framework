package stepDefinitions.cp_steps;

import base.BasePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.cp_PO.CP_HomePage_PO;
import pageObjects.cp_PO.CP_NewsAndFeatures_PO;

public class CP_HomePage_Steps extends BasePage {
    private CP_NewsAndFeatures_PO cp_NewsAndFeatures_PO;
    private CP_HomePage_PO cp_HomePage_PO;

    public CP_HomePage_Steps(CP_HomePage_PO cp_HomePage_PO) {
        this.cp_HomePage_PO = cp_HomePage_PO;
    }

    @Given("I navigate to warriors home page")
    public void i_access_the_warriors_home_page() {
        cp_HomePage_PO.navigateTo_CP_HomePage();
    }

    @When("I select News & Features option from secondary menu")
    public void i_select_news_and_features_option_from_secondary_menu() throws InterruptedException {
        cp_HomePage_PO.expand_SecondaryMenu();
        cp_HomePage_PO.select_NewsAndFeatures_FromSecondaryMenu();
    }

    @When("I select Mens option from Shop menu")
    public void i_select_mens_option_from_shop_menu() throws InterruptedException {
        cp_HomePage_PO.expand_PrimaryMenu_Shop();
        cp_HomePage_PO.select_Mens_From_ShopMenu();
        cp_HomePage_PO.switchTo_Mens_Shopping_Window();
    }

}
