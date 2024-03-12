package Tests;

import Pages.BasePage;
import jdk.jfr.Description;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/* OpenTheWebApp class includes methods for accessing the e-Rent web application and verifying access to the same on different
 browsers. Also includes a screenshot as a visual proof.
 */
public class OpenTheWebApp extends BaseTest {

    //setUp method with two parameters - browser values and browser version values (concrete values are taken from the TestNG file)
    @BeforeMethod
    @Parameters({"browser", "version"})
    public void setUp(String browserValue, String versionValue) {
        baseSetUp(browserValue, versionValue);
    }

    //Method contains steps for accessing web app on browser and taking screenshot
    @Test(description = "Navigate to the e-Rent web app on Google, Firefox and Microsoft Edge browsers")
    @Parameters({"browser"})
    @Description("htpps://erentfrontend.onrender.com/ link redirects user to to the eRent web application")
    public void openTheWebApp(String browser) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(titleIs("e-Rent"));
        new BasePage(driver).takeScreenshot("e-Rent Homepage");
        if(browser.equals("Firefox")) {
            new BasePage(driver).takeFullPageScreenshot("Erent web application");
        }
    }

    //tearDown method for driver quitting
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
