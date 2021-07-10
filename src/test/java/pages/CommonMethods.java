package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class CommonMethods {
    WebDriver driver;
    WebDriverWait wdWait;

    public CommonMethods(WebDriver driver, WebDriverWait wdWait) {
        this.driver = driver;
        this.wdWait = wdWait;
        PageFactory.initElements(driver, this);
    }

    public void setAttributeValue(WebElement element, String attribute, String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",
                element, attribute, value
        );
    }
    public void clickElement(WebElement element) throws InterruptedException {
        //scrollElementIntoView(element);
        wdWait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        try {
            element.click();
        } catch (StaleElementReferenceException e){
            element.click();
        }
    }
    public void typeText(WebElement element, String value){
        wdWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }
    public String getTextFromElement(WebElement element){
        wdWait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    public String getTextFromElementIfTextPresent(WebElement element, String value){
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.textToBePresentInElement(element, value));
        return element.getText();
    }
    public void selectElement(WebElement element, String text){
        //wdWait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public void selectElement(WebElement element, int index){
        //wdWait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    public void selectElementByValue(WebElement element, String text){
        //wdWait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(element);
        select.selectByValue(text);
    }
    public void scrollElementIntoView(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public String getRandomString(int num){
        char[] chars = {'A','a','B','b','C','c','D','d'};
        String randomChars = "";
        for (int i = 0; i < num; i++){
            Random r = new Random();
            int newR = r.nextInt(chars.length-1);
            randomChars+=chars[newR];
        }
        return randomChars;
    }
}