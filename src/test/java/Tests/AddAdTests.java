package Tests;

import Pages.AddAdPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class AddAdTests extends BaseTest {
    @BeforeMethod
    @Parameters({"browser", "version"})
    public void setUp(String browserValue, String versionValue) {
        baseSetUp(browserValue, versionValue);
    }

    @Test
    @Parameters({"category", "subcategory", "adTitle", "object", "structure", "checkboxType"})
    public void addAdTest(String categoryValue, String subCategoryValue, String adTitleValue, String objectTypeValue,
                          String objectStructureValue, String checkboxTypeValue) throws IOException, InterruptedException {
        AddAdPage addAdPage = new AddAdPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        addAdPage.signedInUser("Ipopovic991@gmail.com", "Ivan991");
        addAdPage.clickDashboardBtn();
        addAdPage.clickAddAdBtn();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'" + categoryValue + "')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(text(),'" + categoryValue + "')]")));
        addAdPage.clickCategory(categoryValue);
        addAdPage.clickSubCategory(subCategoryValue);
        addAdPage.addAddress("Test Address bb");
        addAdPage.addDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua.");
        addAdPage.enterPriceOf("1");
        addAdPage.enterPriceUpTo("5");
        addAdPage.enterTitle(adTitleValue);
        addAdPage.selectCity();
        addAdPage.addedPhone(System.currentTimeMillis() + "");
        addAdPage.addEmail("test" + System.currentTimeMillis() + "@mailinator.com");
        addAdPage.clickSubmitBtn();
        addAdPage.uploadAdImage();
        wait.until(ExpectedConditions.visibilityOf(addAdPage.nextStepBtn2));
        wait.until(ExpectedConditions.elementToBeClickable(addAdPage.nextStepBtn2));
        addAdPage.clickNextStepBtn();
        addAdPage.selectObjectType(objectTypeValue);
        addAdPage.selectObjectStructure(objectStructureValue);
        addAdPage.enterPersons();
        addAdPage.clickCheckbox(checkboxTypeValue);
        addAdPage.clickSaveAdBtn();
        wait.until(ExpectedConditions.visibilityOf(addAdPage.actualMsg));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']//div/../div[contains" +
                "(text(),'Oglas uspešno dodat, idite na plaćanje')]")));
        addAdPage.checkIsAdAdded();
        addAdPage.checkAdsList(adTitleValue);
    }

    @AfterMethod
    public void tearDown() {
        baseTearDown();
    }
}
