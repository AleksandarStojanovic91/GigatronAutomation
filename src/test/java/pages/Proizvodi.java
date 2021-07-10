package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Proizvodi extends CommonMethods{
    WebDriver driver;
    WebDriverWait wdWait;

    public Proizvodi(WebDriver driver, WebDriverWait wdWait) {
        super(driver,wdWait);
        this.driver = driver;
        this.wdWait = wdWait;
    }

    @FindBy(xpath = "//div[@class=\"row product-items-main elastic-grid view-grid\"]//h4")
    public List<WebElement> titles;
    @FindBy(xpath = "//div[@class=\"row product-items-main elastic-grid view-grid2\"]//h4")
    public List<WebElement> titles4;

    /** Use this method to filter products
     *  choose filter method
     *  and filter by brand
     * @param filterMethod Ex Asortiman, Proizvodjac etc
     * @param filterField Ex Apple, Bubice
     */
    public void filter(String filterMethod, String filterField) throws InterruptedException {
        clickElement(driver.findElement(By.xpath("//div[@class='spec-title' and contains(text(),'"+filterMethod+"')]/../div[@class='filters']//span[text()='"+filterField+"']")));
    }
}
