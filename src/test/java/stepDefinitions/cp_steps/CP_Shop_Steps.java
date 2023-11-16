package stepDefinitions.cp_steps;

import base.BasePage;
import io.cucumber.java.en.When;
import pageObjects.cp_PO.CP_ShopPage_PO;

public class CP_Shop_Steps extends BasePage {
    private CP_ShopPage_PO cp_ShopPage_PO;

    public CP_Shop_Steps(CP_ShopPage_PO cp_ShopPage_PO) {
        this.cp_ShopPage_PO = cp_ShopPage_PO;
    }

    @When("I find for {} using search bar")
    public void i_find_for_product_using_searchbar(String searchVal) throws InterruptedException {
        cp_ShopPage_PO.search_By_ProductName(searchVal);
    }

    @When("I filter for {} under All Departments using side navigation bar")
    public void i_filter_for_product_under_All_Departments_using_side_navigation_bar(String searchVal) throws InterruptedException {
        cp_ShopPage_PO.selectItemFromAllDepartmentsFilter(searchVal);
    }

    @When("I should be presented with list of {} across pages")
    public void i_should_be_presented_with_list_of_product_across_pages(String searchVal) throws InterruptedException {
        cp_ShopPage_PO.storeProductsData(searchVal);
    }

}
