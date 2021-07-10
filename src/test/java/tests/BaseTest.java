package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wdWait;
    DriverManager driverManager;

    public void init(String browser, int waitTime){
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }

    public void quitBrowser(){
        driverManager.quitWebDriver();
    }

    public void takeScreenshot(String name) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/screenshots/"+name+".png"));
    }

    public void reportScreenshot(String name, String description) throws IOException {
        takeScreenshot(name);
        Path content = Paths.get("src/test/screenshots/"+name+".png");
        try(InputStream is = Files.newInputStream(content)){
            Allure.addAttachment(description,is );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}