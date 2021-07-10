package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage extends CommonMethods{
    WebDriver driver;
    WebDriverWait wdWait;

    public BasePage(WebDriver driver, WebDriverWait wdWait) {
        super(driver, wdWait);
        this.driver = driver;
        this.wdWait = wdWait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Prijava']/..")
    WebElement login;
    @FindBy(xpath = "//span[text()='Registracija']/..")
    WebElement register;
    @FindBy(xpath = "//div[@class=\"search-container\"]//input")
    WebElement search;
    @FindBy(css = "button.megabtn")
    WebElement proizvodi;
    @FindBy(xpath = "//div[@class=\"burger-button\"]")
    List<WebElement> proizvodiB;
    @FindBy(xpath = "//button[@class=\"btn primary\"]")
    WebElement cookie;

    public void acceptCookies(){
        try {
            clickElement(cookie);
        }catch (Exception e){
            System.out.println("No cookies :(");
        }
    }
    //Burger menu appears when the page is scrolled down
    public void clickBurgerMenu() throws InterruptedException {
        clickElement(proizvodiB.get(0));
    }
    //Clicks menu (Proizvodi)
    public void clickMenu() throws InterruptedException {
        clickElement(proizvodi);
    }
    //Clicks a sub menu given a sub menu name text
    public void selectSubMenu(String name) throws InterruptedException {
        clickElement(driver.findElement(By.xpath("//ul[@class='items  ']/li/a[contains(text(),'"+name+"')]")));
    }
    //Clicks a sub menu main category (bold text) given sub menu and category text
    public void selectSubMenuMainCategory(String subMenu, String categoryName) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='items  ']/li/a[contains(text(),'"+subMenu+"')]"))).perform();
        clickElement(driver.findElement(By.xpath("//ul[@class='items  ']/li/a[contains(text(),'"+subMenu+"')]/../ul//ul//a/h3[text()='"+categoryName+"']")));
    }
    //Clicks any link in sub menu items given sub menu and link text
    public void selectSubMenuLink(String subMenu, String link) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='items  ']/li/a[contains(text(),'"+subMenu+"')]"))).perform();
        clickElement(driver.findElement(By.xpath("//ul[@class='items  ']/li/a[contains(text(),'"+subMenu+"')]/../ul//ul//a[text()='"+link+"']")));
    }
}