package tests;

import excel_utils.ExcelUtilities;
import io.qameta.allure.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.Proizvodi;

import java.io.IOException;
import java.util.Map;

public class TestClass2 extends BaseTest{
    @BeforeMethod
    public void setup(){
        init("CHROME",30);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        //takeScreenshot("Failure_"+System.currentTimeMillis());
        reportScreenshot("Failure_"+System.currentTimeMillis(), "Screenshot on failure");
        quitBrowser();
    }

    @Test(description = "Gigatron filter test")
    @Description("Trebalo bi da verifikuje filtriranje")
    @TmsLink("PR-4836")
    @Issue("PR-4836")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("EP001")
    @Feature("F001")
    @Story("S001")
    @Attachment(value = "Attachment Test",type = "text/html")
    public void gigatronMenuNavigation() throws InterruptedException, IOException {
        driver.get("https://gigatron.rs/");
        wdWait = new WebDriverWait(driver,10);
        BasePage bp = new BasePage(driver,wdWait);

        Map<String,String> data = new ExcelUtilities().getRowData("src/test/testData/Test.xlsx", "TestSheet","2");

        bp.acceptCookies();
        bp.clickMenu();
        bp.selectSubMenuMainCategory("Oprema za računare","SLUŠALICE");

        reportScreenshot("SLUŠALICE","SLUŠALICE");

        Proizvodi proizvodi = new Proizvodi(driver,wdWait);
        proizvodi.filter(data.get("TipFiltera"),data.get("AsortimanFilter"));
        Thread.sleep(2000);
        proizvodi.filter("Proizvođač","Huawei");
        Thread.sleep(2000);
        proizvodi.filter("Proizvođač","Hoco");

        for(int i = 0; i < proizvodi.titles.size();i++){
            Assert.assertTrue((proizvodi.titles.get(i).getText().contains("HUAWEI") || proizvodi.titles.get(i).getText().contains("HOCO")) && !proizvodi.titles.get(i).getText().contains("HAMA"));
        }
    }

    @Test(description = "Gigatron filter test")
    @Description("Trebalo bi da verifikuje filtriranje")
    @TmsLink("PR-4836")
    @Issue("PR-4836")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("EP001")
    @Feature("F001")
    @Story("S001")
    @Attachment(value = "Attachment Test",type = "text/html")
    public void gigatronMenuNavigation2() throws InterruptedException, IOException {
        driver.get("https://gigatron.rs/");
        wdWait = new WebDriverWait(driver,10);
        BasePage bp = new BasePage(driver,wdWait);

        Map<String,String> data = new ExcelUtilities().getRowData("src/test/testData/Test.xlsx", "TestSheet","2");

        bp.acceptCookies();
        bp.clickMenu();
        bp.selectSubMenuMainCategory("Oprema za računare","SLUŠALICE");

        reportScreenshot("SLUŠALICE","SLUŠALICE");

        Proizvodi proizvodi = new Proizvodi(driver,wdWait);
        proizvodi.filter(data.get("TipFiltera"),data.get("AsortimanFilter"));
        Thread.sleep(2000);
        proizvodi.filter("Proizvođač","Huawei");
        Thread.sleep(2000);
        proizvodi.filter("Proizvođač","Hoco");

        for(int i = 0; i < proizvodi.titles.size();i++){
            Assert.assertTrue((proizvodi.titles.get(i).getText().contains("HUAWEI") || proizvodi.titles.get(i).getText().contains("HOCO")) && !proizvodi.titles.get(i).getText().contains("HAMA"));
        }
    }

}