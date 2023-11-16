package pageObjects.cp_PO;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FilteHelper;

import java.util.ArrayList;
import java.util.List;

import static constants.Global_Constants.TEST_OUTPUT_PATH;

public class CP_ShopPage_PO extends BasePage {

    private @FindBy(xpath = "//input[@id='typeahead-input-desktop']")
    WebElement search_txt;
    private @FindBy(xpath = "//div[@class='search-container']//button[@aria-label='Search Product']")
    WebElement search_btn;
    private @FindBy(xpath = "//div[@class='product-card row']")
    List<WebElement> product_Card;
    private @FindBy(xpath = "//div[@class='product-card row']//div[@class='product-card-title']/a")
    WebElement product_Card_Title;
    private @FindBy(xpath = "//div[@class='product-card row']//div[@class='price-card']//span[@class='lowest']//span[@aria-hidden='true']")
    WebElement product_Card_Price;
    private @FindBy(xpath = "//div[@class='product-grid-top-area']//div[@class='pagination-container']//li[contains(@class,'show-for-large')]")
    List<WebElement> product_Result_Pages;
    private @FindBy(xpath = "//div[@class='product-grid-top-area']//div[@class='pagination-container']//li[@class='next-page']")
    WebElement product_Result_Pages_Next_Btn;

    public CP_ShopPage_PO() {
        super();
    }
    public void set_SearchText(String searchStr) {
        sendKeys(search_txt, searchStr);
    }

    public void clickOnSearch() {
        waitForWebElementAndClick(search_btn);
    }

    public void search_By_ProductName(String searchStr) throws InterruptedException {
        for (int i=0; i<5;i++) {
            try {
                clickOnSearch();
                set_SearchText(searchStr);
                clickOnSearch();
                break;
            } catch (ElementNotInteractableException e) {
              //  log.error("Unable to interact with element" + e);
                performEscape();
                wait(3000);
            }
        }
    }
    public void waitForUrl(String searchKey) throws InterruptedException {
        while (! getDriver().getCurrentUrl().contains("SIDE_NAV")) {
            wait(5000);
        }
    }

    public void storeProductsData (String searchKey) throws InterruptedException {
        List<String> output =  getProductDetails("Jacket");
        FilteHelper.writeToFile(output, TEST_OUTPUT_PATH + "TC1_productDetails.txt");
    }

    public void selectItemFromAllDepartmentsFilter(String filterVal) throws InterruptedException {
        String loc = "//div[@class='side-nav-facet-items allDepartmentsBoxes']//li/a/span[text()='"+ filterVal+"']//parent::a";
        click(loc);
        waitForUrl(filterVal);
    }

    public int getResultPagesCount(){
        return product_Result_Pages.size();
    }

    public List<String> getProductDetails(String searchKey) throws InterruptedException {

        int currentPage=1;
        int mainCounter = 1;
        String cardTitle;
        String cardPrice;
        String cardTopSellerMessage1;
        String cardTopSellerMessage2 ;
        String cardTopSellerCompleteMessage;
        int totalResultsPagesCount = getResultPagesCount();
        List<String> productDetailsOutput = new ArrayList<>();

        while (currentPage <= totalResultsPagesCount ){
            List<WebElement> products = getDriver().findElements(By.xpath("//div[@class='product-card row']"));

            for (int i=1; i<=products.size();i++){
                String mainCard_loc = "(//div[@class='column']/div[@class='product-card row'])[" + i + "]";
                String cardTitle_loc = mainCard_loc + "//div[@class='product-card-title']/a";
                String cardPrice_loc = mainCard_loc + "//div[@class='price-card']//span[@class='lowest']//span[@class='sr-only']";
                String cardTopSellerMsg1_Loc = mainCard_loc + "//div[@class='product-vibrancy-container']//span[@class='top-seller-vibrancy-message']/span[1]";
                String cardTopSellerMsg2_Loc = mainCard_loc + "//div[@class='product-vibrancy-container']//span[@class='top-seller-vibrancy-message']/span[2]";
                cardTitle = "";
                cardPrice = "";
                cardTopSellerMessage1 = "";
                cardTopSellerMessage2 = "";

                try {
                    cardTitle = getDriver().findElement(By.xpath(cardTitle_loc)).getText();
                    cardPrice = getDriver().findElement(By.xpath(cardPrice_loc)).getText();
                    cardTopSellerMessage1 = getDriver().findElement(By.xpath(cardTopSellerMsg1_Loc)).getText();
                    cardTopSellerMessage2 = getDriver().findElement(By.xpath(cardTopSellerMsg2_Loc)).getText();

                } catch (NoSuchElementException e) {
                    if (cardTopSellerMessage1.equals("")) cardTopSellerMessage1 = "N/A";
                }
                cardTopSellerCompleteMessage = cardTopSellerMessage1 + " " + cardTopSellerMessage2;

                if (!cardTitle.trim().equals("") && ! cardTitle.isEmpty()) {
                    String outputMsg = mainCounter + ". [Page-" + currentPage + "] | Title:"+ cardTitle + " | " + "Price:"+ cardPrice + " | " + "Top Seller Message:"+cardTopSellerCompleteMessage.trim();
                    //log.debug(outputMsg);
                    productDetailsOutput.add(outputMsg);
                    mainCounter++;
                }
            }

            if (currentPage < totalResultsPagesCount) {
                waitForWebElementAndClick(product_Result_Pages_Next_Btn);
                wait(2000);
            }
            currentPage++;

        }
        return productDetailsOutput;
    }

}