package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

public class AddAdPage extends BasePage {
    public AddAdPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='relative flex h-20 w-20 cursor-pointer items-center justify-center bg-primary-yellow lg:ml-auto ']")
    public WebElement userIconBtn;
    @FindBy(xpath = "//div[@class='flex w-full flex-col  text-[#898989]']//a[@href='/login']")
    public WebElement logInBtn;
    @FindBy(xpath = "//input[@name='email']")
    public WebElement logInEmail;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement logInPassword;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signInBtn;
    @FindBy(xpath = "//div[@class='flex w-full flex-col  text-[#898989]']//a[@href='/dashboard' and text()='Kontrolni panel']")
    public WebElement dashboardBtn;
    @FindBy(xpath = "//button[@type='button']")
    public WebElement addAddBtn;
    @FindBy(xpath = "//label[@for='subCategoryId']/../div[1]")
    public WebElement subCategoryMenu;
    @FindBy(xpath = "//input[@name='address']")
    public WebElement address;
    @FindBy(xpath = "//div[@role='textbox']")
    public WebElement descriptionField;
    @FindBy(xpath = "//input[@name='priceFrom']")
    public WebElement priceOf;
    @FindBy(xpath = "//input[@name='priceUpTo']")
    public WebElement priceUpTo;
    @FindBy(xpath = "//input[@name='title']")
    public WebElement title;
    @FindBy(xpath = "//div[@class='mt-[1.75rem]']//div[1]")
    public WebElement city;
    @FindBy(xpath = "//div[@class=' css-1dimb5e-singleValue']")
    public WebElement cities;
    @FindBy(xpath = "//input[@name='phone']")
    public WebElement phoneNumber;
    @FindBy(xpath = "//input[@name='email']")
    public WebElement email;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement nextStepBtn;
    @FindBy(xpath = "//label[@for='imageInput']/../input[@id='imageInput']")
    public WebElement uploadImgWindow;
    @FindBy(xpath = "//button[@type='button']//div[contains(text(),'Sledeći korak')]")
    public WebElement nextStepBtn2;
    @FindBy(xpath = "//label[text()='Tip objekta']/../div[1]")
    public WebElement typeObjectMenu;
    @FindBy(xpath = "//label[text()='Vrsta sobe']")
    public WebElement structureMenu;
    @FindBy(xpath = "//input[@name='numberOfPeople']")
    public WebElement persons;
    @FindBy(xpath = "//button[@type='button']")
    public WebElement saveAd;

    @FindBy(xpath = "//div[@role='alert']//div/../div[contains(text(),'Oglas uspešno dodat, idite na plaćanje')]")
    public WebElement actualMsg;

    public void signedInUser(String emailValue, String passwordValue) {
        clickElement(userIconBtn, " user icon in navigation bar");
        clickElement(logInBtn, " Uloguj se button");
        typeText(logInEmail, emailValue, " email ");
        typeText(logInPassword, passwordValue, " password ");
        clickElement(signInBtn, "sign in button");
    }

    public void clickDashboardBtn() {
        clickElement(userIconBtn, "user icon in the navigation bar");
        clickElement(dashboardBtn, "kontrolni panel button ");
    }

    public void clickAddAdBtn() {
        clickElement(addAddBtn, "dodaj oglas button");
    }

    public void clickCategory(String category) {
        clickElement(driver.findElement(By.xpath("//h1[contains(text(),'" + category + "')]")), "kategorija - " + category + "");
    }

    public void clickSubCategory(String subcategory) {
        clickElement(subCategoryMenu, "subcategory dropdown menu");
        selectElement("//select[@id='subCategoryId']", "" + subcategory + "", subcategory);
    }

    public void addAddress(String addressValue) {
        typeText(address, addressValue, "address");
    }

    public void addDescription(String descriptionValue) {
        typeText(descriptionField, descriptionValue, "description");
    }

    public void enterPriceOf(String priceOfValue) {
        typeText(priceOf, priceOfValue, "price of");
    }

    public void enterPriceUpTo(String priceUpToValue) {
        typeText(priceUpTo, priceUpToValue, "price up to");
    }

    public void enterTitle(String titleValue) {
        typeText(title, titleValue, "title");
    }

    public void selectCity() throws InterruptedException {
        clickElement(city, " city dropdown menu");
        Thread.sleep(1500);
        String cityName = "Crna Gora - Budva";
        WebElement cityExample = driver.findElement(By.xpath("//div[@class=' css-qr46ko']//div[@role='option' and " +
                "contains(text(),'" + cityName + "')]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(cityExample));

        WebElement cityDropDown = driver.findElement(By.xpath("//div[@class=' css-qr46ko']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[1];", cityDropDown,
                cityExample.getLocation().getY());

        cityExample.click();
        System.out.println("Clicked " + cityName);
    }

    public void addedPhone(String phoneNumberValue) {
        typeText(phoneNumber, phoneNumberValue, "phone number");
    }

    public void addEmail(String emailValue) {
        typeText(email, emailValue, "email");
    }

    public void clickSubmitBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", nextStepBtn);
        System.out.println("Clicked sledeći korak button");
    }

    public void uploadAdImage() {
        String filePath = "C:/Users/ivanp/Desktop/Ace Digital Studio/Erent/More.jpg";
        driver.findElement(By.xpath("//label[@for='imageInput']/../input[@id='imageInput']")).sendKeys(filePath);
        System.out.println("Image uploaded");
    }

    public void clickNextStepBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", nextStepBtn2);
        System.out.println("Clicked sledeći korak button");
    }

    public void selectObjectType(String objectType) {
        clickElement(typeObjectMenu, " type of Object dropdown menu");
        selectElement("//select[@name='objectType']", "" + objectType + "", objectType);
    }

    public void selectObjectStructure(String objectStructureValue) {
        clickElement(structureMenu, "object structure menu");
        selectElement("//select[@name='roomType']", "" + objectStructureValue + "", objectStructureValue);
    }

    public void enterPersons() {
        typeText(persons, "6", "persons");
    }

    public void clickCheckbox(String checkboxType) {
        driver.findElement(By.xpath("//label[contains(text(),'" + checkboxType + "')]")).click();
        System.out.println("Clicked " + checkboxType + "");
    }

    public void clickSaveAdBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", saveAd);
        System.out.println("Clicked sačuvaj oglas button");
    }

    public void checkIsAdAdded() throws IOException {
        String actualMsgText = actualMsg.getText();
        Assert.assertEquals(actualMsgText, "Oglas uspešno dodat, idite na plaćanje");
        if (actualMsg.isDisplayed()) {
            System.out.println("Oglas uspešno dodat, idite na plaćanje message is displayed");
        }
        new BasePage(driver).takeScreenshot("Oglas je uspesno dodat");
    }

    public void checkAdsList(String adTitle) throws IOException {
        WebElement ad = driver.findElement(By.xpath("//table//tbody//tr//h1[contains(text(),'" + adTitle + "')]"));
        String adTitleText = ad.getText();
        if (ad.isDisplayed()) {
            Assert.assertEquals(adTitleText, adTitle);
            System.out.println(adTitle + " is displayed");
        }
        new BasePage(driver).takeElementScreenshot("//tr//td//h1[contains(text(),'" + adTitle + "')]", adTitle + "");
        new BasePage(driver).takeScreenshot(adTitle + "");
    }
}
