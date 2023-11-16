package pageObjects.cp_PO;

import base.BasePage;
import constants.Global_Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CP_HomePage_PO extends BasePage {

    private @FindBy(xpath = "//nav[@data-testid='utilty-nav']")
    WebElement top_navBar;
    private @FindBy(xpath = " //nav[@aria-label='Golden State Warriors navigation']")
    WebElement warriors_navBar;
    private @FindBy(xpath = "//nav[@aria-label='header-secondary-menu']/ul/li/a")
    WebElement secodary_menubar;
    private @FindBy(xpath = "//nav[@aria-label='header-secondary-menu']/ul/li[@aria-expanded='true']")
    WebElement secodary_menubar_expanded;
    private @FindBy(xpath = "//div[@role='dialog']")
    WebElement dialogPrompt;
    private @FindBy(xpath = "//div[@role='dialog']/div/div[contains(@class,'cursor-pointer')]")
    WebElement dialogPrompt_closeButton;
    private @FindBy(xpath = "//nav[@class='style__headerSecondaryMenu_1HJt3']//ul[@role='menubar']//li//a[@title='News & Features']")
    WebElement menuItem_NewsAndFeatures;

    private @FindBy(xpath = "//nav[@aria-label='header-primary-menu']//li//a/span[text()='Shop']")
    WebElement primaryMenu_Shop;

    private @FindBy(xpath = "//nav[@aria-label='header-primary-menu']//li//a/span[text()='Shop']//ancestor::li//nav[@aria-label='submenu']//li/a[text()=\"Men's\"]")
    WebElement primaryMenu_SubMenuOption_Mens;

    private @FindBy(xpath = "//nav[@aria-label='header-primary-menu']//ul/li[contains(@data-testid,'https://shop')][@aria-haspopup='true'][@aria-expanded='true']")
    WebElement shop_menu_expanded;

    public CP_HomePage_PO() {
        super();
    }

    public void navigateTo_CP_HomePage() {
        navigateTo_URL(Global_Constants.CP_HOMEPAGE_URL);
        handleDialogOnPageLoad();
        waitFor(top_navBar);
        waitFor(warriors_navBar);
    }
    public void handleDialogOnPageLoad() {
        if (dialogPrompt.isDisplayed()) {
            log.info("Closing dialog prompt.");
            waitForWebElementAndClick(dialogPrompt_closeButton);
        }
    }

    public void expand_SecondaryMenu() {
        log.info("Expanding secondary menu to select options.");
        moveToElement(secodary_menubar);
    }

    public void select_NewsAndFeatures_FromSecondaryMenu() throws InterruptedException {
        log.info("Selecting 'News & Features' from secondary menu.");
        int count = 0;
        while (!isElementPresent(secodary_menubar_expanded) || count > 5) {
            expand_SecondaryMenu();
            count++;
            wait(5000);
        }
        waitForWebElementAndClick(menuItem_NewsAndFeatures);
    }

    public void expand_PrimaryMenu_Shop() {
        log.info("Expanding primary menu to select options.");
        moveToElement(primaryMenu_Shop);
    }

    public void select_Mens_From_ShopMenu() throws InterruptedException {
        log.info("Selecting 'Mens' from Shop menu.");
        int count = 0;
        while (!isElementPresent(shop_menu_expanded) || count > 5) {
            expand_SecondaryMenu();
            count++;
            wait(5000);
        }
        waitForWebElementAndClick(primaryMenu_SubMenuOption_Mens);
    }

    public void switchTo_Mens_Shopping_Window() throws InterruptedException {
        log.info("Navigating to 'Mens' shopping page");
        for (String currWindow : getDriver().getWindowHandles() ) {
            getDriver().switchTo().window(currWindow);
            if (getDriver().getTitle().contains("Men's")){
                log.info("Navigated to 'Mens' shopping page");
                break;
            }
        }
    }



}
