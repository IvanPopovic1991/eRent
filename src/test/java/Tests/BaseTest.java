package Tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class BaseTest {
    WebDriver driver;

    /*
    baseSetUp method for launching different browsers and different versions
    */
    public void baseSetUp(String browser, String version) {

        switch (browser) {
            case "CHROMENew": {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\ivanp\\Desktop\\Automation projects\\eRent\\src\\main\\" +
                        "resources\\chromedriver" + version + ".exe");
                ChromeOptions options = new ChromeOptions();
                options.setBrowserVersion(version);
                options.setBinary("C:\\Users\\ivanp\\Downloads\\chrome-win64\\chrome.exe");
                driver = new ChromeDriver(options);
            }
            break;
            case "Edge": {
                System.setProperty("webdriver.edge.driver", "C:\\Users\\ivanp\\Desktop\\Automation projects\\eRent\\src\\main\\" +
                        "resources\\msedgedriver" + version + ".exe");
                driver = new EdgeDriver();
            }
            break;
            case "Firefox": {
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\ivanp\\Desktop\\Automation projects\\eRent\\src\\main\\" +
                        "resources\\geckodriver" + version + ".exe");
                driver = new FirefoxDriver();
            }
            break;
            default: {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\ivanp\\Desktop\\Automation projects\\eRent\\src\\main\\" +
                        "resources\\chromedriver" + version + ".exe");
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
            }
        }
        // navigate to the e-Rent web app and maximize the window
        driver.get("https://erentfrontend.onrender.com");
        driver.manage().window().maximize();
    }

    public void newWindowIsOpen() {
        String currentHandle = driver.getWindowHandle();
        //Get all the handles currently available
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (!actual.equalsIgnoreCase(currentHandle)) {
                //Switch to the opened tab
                driver.switchTo().window(actual);
            }
        }
    }

    public void baseTearDown() {
        driver.quit();
    }
}
