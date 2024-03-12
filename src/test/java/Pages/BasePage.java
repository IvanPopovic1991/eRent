package Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* Methods for clicking on a web element, typing text into input fields, and getting specific text; also describes actions
       related to web elements, by printing text to the console.
     */
    public void clickElement(WebElement element, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        }
    }

    public void typeText(WebElement element, String text, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " in " + log + " field");
        } catch (StaleElementReferenceException e) {
            element.clear();
            element.sendKeys(text);
            System.out.println("Typed " + text + " in " + log + " field");
        }
    }

    public void selectElement(String attributeValue, String textValue, String log) {
        try {
            Select dropdown = new Select(driver.findElement(By.xpath("" + attributeValue + "")));
            dropdown.selectByVisibleText("" + textValue + "");
            System.out.println("Selected " + log);
        } catch (StaleElementReferenceException e) {
            Select dropdown = new Select(driver.findElement(By.xpath("" + attributeValue + "")));
            dropdown.selectByVisibleText("" + textValue + "");
        }

    }

    public String getText(By by, String log) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
            System.out.println("Text " + log + " is displayed");
            return driver.findElement(by).getText();
        } catch (StaleElementReferenceException e) {
            System.out.println("Text " + log + " is displayed");
            return driver.findElement(by).getText();
        }
    }

    //Method for taking screenshots
    public void takeScreenshot(String screenshotName) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C:\\Users\\ivanp\\Desktop\\Automation projects\\eRent\\src\\test\\java\\" +
                "screenshot\\" + screenshotName + ".png"));
    }

    //Method for taking screenshot of particular element
    public void takeElementScreenshot(String path, String screenshotName) throws IOException {
        WebElement element = driver.findElement(By.xpath(path + ""));
        File file = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C:\\Users\\ivanp\\Desktop\\Automation projects\\eRent\\src\\test\\java\\" +
                "screenshot\\" + screenshotName + ".png"));
    }

    //Method for taking full page screenshot (works only for Firefox browser)
    public void takeFullPageScreenshot(String screenshotName) throws IOException {
        File src = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
        FileHandler.copy(src, new File("C:\\Users\\ivanp\\Desktop\\Automation projects\\eRent\\src\\test\\java\\screenshot\\" + screenshotName + ".png"));
    }
}
