package Tests;

import Pages.BasePage;
import Pages.SignInPage;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class UserSignInTests extends BaseTest {
    /* User sign in tests include successfully logged-in user test case (as positive test case) and test cases related to
    not logged-in user (as a negative test case)
    */

    @BeforeMethod
    @Parameters({"browser", "version"})
    public void setUp(String browserValue, String versionValue) {
        baseSetUp(browserValue, versionValue);
    }

    @Test(description = "Check if the user can log in to the web application successfully")
    @Description("The user can log in to the application successfully if he has entered valid credentials")
    public void UserIsSignedIn() throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signedInUser("Ipopovic991@gmail.com", "Ivan991");
        signInPage.clickUserIcon();
        String userName = driver.findElement(By.xpath("//p[contains(text(),'Ivan')]")).getText();
        Assert.assertEquals(userName, "Ivan");
        new BasePage(driver).takeScreenshot("User is successfully signed in");
        System.out.println("User is successfully signed in");
    }

    @Test(description = "Check if the alert window appears if the user does not exist")
    @Description("The user cannot log in to the web application if he is not registered")
    public void userDoesNotExist() throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signedInUser("ipopovic123@gmail.com", "Abc123!");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInPage.userNotExist));
        if (signInPage.userNotExist.isDisplayed()) {
            System.out.println("The user does not exist error message appears");
        }
        new BasePage(driver).takeScreenshot("Korisnik ne postoji alert window appears");
    }

    @Test(description = "Check if required field error messages are triggered successfully")
    @Description("If the user tries to log in to the app but does not insert email address and password required field message appear")
    public void requiredFieldsMsg() throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickUserIcon();
        signInPage.clickLogInBtn();
        signInPage.clickElement(signInPage.signInBtn, " sign in button");
        if (signInPage.emailErrorMsg.isDisplayed() && signInPage.passwordErrMsg.isDisplayed()) {
            Assert.assertEquals(signInPage.emailErrorMsg.getText(), "Obavezno polje");
            System.out.println("Email error message - Obavezno polje message is triggered successfully");
            Assert.assertEquals(signInPage.passwordErrMsg.getText(), "Obavezno polje");
            System.out.println("Password error message - Obavezno polje message is triggered successfully");
        }
        new BasePage(driver).takeScreenshot("Obavezno polje messages are triggered successfully");
    }

    @Test(description = "Check if validation messages are triggered successfully")
    @Description("If the user insert non-valid email address and password validation messages appears")
    public void nonValidEmailAndPassword() throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.signedInUser("ipopovic991", "91");
        if (signInPage.nonValidMEmail.isDisplayed() && signInPage.nonValidPassword.isDisplayed()) {
            Assert.assertEquals(signInPage.nonValidMEmail.getText(), "Unesite ispravnu e-mail adresu.");
            System.out.println("Unesite ispravnu email adresu message is triggered");
            Assert.assertEquals(signInPage.nonValidPassword.getText(), "Lozinka mora da sadrži minimalno 5 karaktera.");
            System.out.println("Lozinka mora da sadrži minimalno 5 karaktera. message is triggered");
        }
        new BasePage(driver).takeScreenshot("Validation messages are triggered successfuly");
    }

    @Test(description = "Check Zaboravili ste lozinku link redirection")
    @Description("If the user clicks on the Zaboravili ste lozinku link he is redirected to change password page")
    public void changePassLinkRedirection() throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickUserIcon();
        signInPage.clickLogInBtn();
        signInPage.forgotPasswordLinkRedirection();
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("https://erentfrontend.onrender.com/change-password"));
        new BasePage(driver).takeScreenshot("Promena lozinke page");
    }

    @Test(description = "Check if the Registrujte se brzo i lako link redirects you to register page")
    @Description("If user clicks on the Registrujte se brzo i lako link he is redirected to register account page")
    public void checkRegisterLinkRedirection() throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickUserIcon();
        signInPage.clickLogInBtn();
        signInPage.registerLinkRedirection();
        String registerUrl = driver.getCurrentUrl();
        if (registerUrl.contains("https://erentfrontend.onrender.com/register")) {
            System.out.println("The user is redirected to https://erentfrontend.onrender.com/register page");
        }
        new BasePage(driver).takeScreenshot("Kreirajte E-Rent nalog page");
    }

    @Test(description = "Check if the Vratite se na pocetnu link redirects you to Home page")
    @Description("If the user clicks on the Vratite se na pocetnu link he is redirected to Home page")
    public void checkBackToHomePageLink() throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.clickUserIcon();
        signInPage.clickLogInBtn();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signInPage.backToHomeLink);
        String homePageURL = "https://erentfrontend.onrender.com/";
        String currentURL = driver.getCurrentUrl();
        if (currentURL.equals(homePageURL)) {
            System.out.println("The user is redirected to https://erentfrontend.onrender.com/ page");
            new BasePage(driver).takeScreenshot("Home page");
        }
    }

    @Test(description = "Check if the user can log in to the application using Sign in with Google button ")
    @Description("The user can sign in to the application using Sign in with Google button")
    public void checkSignInWithGoogle() throws IOException {
        SignInPage signInPage = new SignInPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        signInPage.clickUserIcon();
        signInPage.clickLogInBtn();
        signInPage.clickElement(signInPage.signInWithGoogleBtn, " sign in with Google button");
        newWindowIsOpen();
        signInPage.signInWithGoogleBtnMethod("john2921990doe@gmail.com", "29Decembar1990");
        wait.until(ExpectedConditions.visibilityOf(signInPage.userIcon));
        wait.until(ExpectedConditions.elementToBeClickable(signInPage.userIcon));
        signInPage.clickUserIcon();
        String name = driver.findElement(By.xpath("//p[contains(text(),'John')]")).getText();
        Assert.assertEquals(name, "John");
        new BasePage(driver).takeScreenshot("User is successfully signed in using Google button");
    }

    @AfterMethod
    public void tearDown() {
        baseTearDown();
    }
}
