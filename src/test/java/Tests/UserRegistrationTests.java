package Tests;

import Pages.BasePage;
import Pages.RegistrationPage;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.*;

/* UserRegistrationTests class includes tests/methods for registering new accounts on the e-Rent web application, validate error
and validation messages, link redirections, etc. Also includes a screenshot as a visual proof.
*/

public class UserRegistrationTests extends BaseTest {
    @BeforeMethod
    @Parameters({"browser", "version"})
    public void setUp(String browserValue, String versionValue) {
        baseSetUp(browserValue, versionValue);
    }

    @Test(description = "A user successfully registered new account")
    @Description("The user registered account successfully. After account registration same is redirected to home page")
    public void userRegistration() throws IOException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.userIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.registerBtn)).click();
        registrationPage.userRegistrationMethod("Testq", "Testa", "Test address 29",
                "test" + System.currentTimeMillis() + "@mailinator.com", "Aa123!");
        registrationPage.clickUserIcon();
        String userName = driver.findElement(By.xpath("//p[contains(text(),'Testq')]")).getText();
        assertEquals(userName, "Testq");
        new BasePage(driver).takeScreenshot("User registered account successfully");
    }

    @Test(description = "Obavezno polje error message appears if the user does not fill the form")
    @Description("If the user tries to submit empty form (empty fields) error message field is mandatory appears")
    public void validationErrorMessages() throws IOException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.userIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.registerBtn)).click();
        registrationPage.clickElement(registrationPage.submitBtn, " submit button");
        registrationPage.assertErrorMsg();
        new BasePage(driver).takeScreenshot("Error messages triggered successfully");
        System.out.println("Error messages triggered successfully");
    }

    @Test(description = "Validation message for each field should appear if the user insert non valid data")
    @Description("If the user enters non-valid characters appropriate validation message appears")
    public void validationMsg() throws IOException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.userIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.registerBtn)).click();
        registrationPage.enterFirstName("+-!");
        registrationPage.enterLastName("+-!");
        registrationPage.enterAddress("+-!");
        registrationPage.enterEmail("+-!");
        registrationPage.enterPassword("+-!1");
        registrationPage.assertValidationMsg();
        new BasePage(driver).takeScreenshot("Validation messages for fields triggered successfully");
        System.out.println("Validation messages for fields triggered successfully");
    }

    @Test(description = "Check if 'Prijavite se' link redirects the user to Login page ")
    @Description("Click on the Sign in linked text on Register page redirects user to Sign in page")
    public void loginLinkRedirection() throws IOException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.userIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.registerBtn)).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", registrationPage.loginLink);
        String URL = driver.getCurrentUrl();
        System.out.println("User is redirected to " + URL + " page");
        new BasePage(driver).takeScreenshot("User is redirected to Login page after he clicks on Prijavite se link");
    }

    @Test(description = "Check if Vratite se na pocetnu link redirects user to home page")
    @Description("Click on the Back to home linked text on Register page redirects user to Home page")
    public void backToHomeLinkRedirection() throws IOException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.userIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.registerBtn)).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", registrationPage.backToHomeLink);
        String URL = driver.getCurrentUrl();
        System.out.println("User is redirected " + URL + " page");
        new BasePage(driver).takeScreenshot("User is redirected to Home page after he clicks on Vratite se na početnu" +
                " link");
    }

    @Test(description = "Check if error message for checkbox 'Prihvatam uslove korišćenja' appears if user does not click the same")
    @Description("Validation 'I accept the terms of use' checkbox as mandatory field - Alert window appears if user does not check" +
            "I accept the terms of use checkbox")
    public void acceptTheTermsAndConditions() throws IOException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.userIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.registerBtn)).click();
        registrationPage.enterFirstName("Testq");
        registrationPage.enterLastName("Testa");
        registrationPage.enterAddress("Test 123");
        registrationPage.enterEmail("test" + System.currentTimeMillis() + "@mailinator.com");
        registrationPage.enterPassword("Aa1111");
        registrationPage.clickElement(registrationPage.submitBtn, " submit button");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']//..//div[contains(" +
                "text(),'Molimo Vas da prihvatite uslove korišćenja!')]")));
        wait.until(ExpectedConditions.visibilityOf(registrationPage.termsAndConditionMsg));
        Assert.assertEquals(registrationPage.termsAndConditionMsg.getText(), "Molimo Vas da prihvatite uslove korišćenja!");
        if (registrationPage.termsAndConditionMsg.isDisplayed()) {
            System.out.println("Molimo Vas da prihvatite uslove korišćenja! message is displayed");
        }
        new BasePage(driver).takeScreenshot("Molimo Vas da prihvatite uslove korišćenja! message is displayed");
    }

    @Test(description = "Check if user tries to register already registered email address")
    @Description("If user tries to register already registered email address alert window appears")
    @Parameters({"browser", "version"})
    public void AlreadyRegisteredUser(String browserValue, String versionValue) throws IOException, InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.userIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.registerBtn)).click();
        registrationPage.enterFirstName("Testq");
        registrationPage.enterLastName("Testa");
        registrationPage.enterAddress("Test address 29");
        registrationPage.enterEmail("test" + System.currentTimeMillis() + "@mailinator.com");
        WebElement alrUsedEmail = driver.findElement(By.xpath("//input[@id='email']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
        String oldEmailAddress = alrUsedEmail.getAttribute("value");
        registrationPage.enterPassword("Aa123!");
        registrationPage.clickElement(registrationPage.checkbox, " 'I accept terms of use' checkbox");
        registrationPage.clickElement(registrationPage.submitBtn, " Submit button");
        Thread.sleep(2000);
        baseTearDown();
        baseSetUp(browserValue, versionValue);
        driver.get("https://erentfrontend.onrender.com/");
        RegistrationPage registrationPage1 = new RegistrationPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage1.userIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage1.registerBtn)).click();
        registrationPage1.userRegistrationMethod("Testq", "Testa", "Test address 29", oldEmailAddress,
                "Aa123!");
        wait.until(ExpectedConditions.visibilityOf(registrationPage1.alrExistWindow));
        if (registrationPage1.alrExistWindow.isDisplayed()) {
            System.out.println("User already exists alert window is displayed");
        }
        new BasePage(driver).takeScreenshot("User exists alert window appears");
    }

    @AfterMethod
    public void tearDown() {
        baseTearDown();
    }
}
