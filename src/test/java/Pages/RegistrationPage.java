package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // First name xpath
    @FindBy(xpath = "//input[@id='firstName']")
    WebElement firstName;
    // Last name field xpath
    @FindBy(xpath = "//input[@id='lastName']")
    WebElement lastName;
    // Address field xpath
    @FindBy(xpath = "//input[@id='address']")
    WebElement address;
    // Email field xpath
    @FindBy(xpath = "//input[@id='email']")
    WebElement email;
    // Password field xpath
    @FindBy(xpath = "//input[@id='password']")
    WebElement password;
    // Checkbox 'Prihvatam uslove koriscenja'
    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement checkbox;
    // Submit button xpath
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitBtn;
    @FindBy(xpath = "//div[@class='relative flex h-20 w-20 cursor-pointer items-center justify-center bg-primary-yellow lg:ml-auto ']")
    public WebElement userIcon;
    // Registruj se button
    @FindBy(xpath = "//a[@href='/register' and contains(text(),'Registruj se')]")
    public WebElement registerBtn;
    // First name field error message
    @FindBy(xpath = "//label[@for='firstName']//span[contains(text(),'Obavezno polje')]")
    public WebElement firstNameErrMsg;
    // Last name field error message
    @FindBy(xpath = "//label[@for='lastName']//span[contains(text(),'Obavezno polje')]")
    public WebElement lastNameErrMsg;
    // Address field error message
    @FindBy(xpath = "//label[@for='address']//span[contains(text(),'Obavezno polje')]")
    public WebElement addressErrMsg;
    // Email field error message
    @FindBy(xpath = "//label[@for='email']//span[contains(text(),'Obavezno polje')]")
    public WebElement emailErrMsg;
    // Password field error message
    @FindBy(xpath = "//label[@for='password']//span[contains(text(),'Obavezno polje')]")
    public WebElement passwordErrMsg;

    // First name field validation message
    @FindBy(xpath = "//label[@for='firstName']//span[contains(text(),'Sadrži nevažeće znakove.')]")
    public WebElement firstNameValidationMsg;
    // Last name field validation message
    @FindBy(xpath = "//label[@for='lastName']//span[contains(text(),'Sadrži nevažeće znakove.')]")
    public WebElement lastNameValidationMsg;
    // Address field validation message
    @FindBy(xpath = "//label[@for='address']//span[contains(text(),'Sadrži nevažeće znakove.')]")
    public WebElement addressValidationMsg;
    // Email field validation message
    @FindBy(xpath = "//label[@for='email']//span[contains(text(),'Unesite ispravnu e-mail adresu.')]")
    public WebElement emailValidationMsg;
    // Password field validation message
    @FindBy(xpath = "//label[@for='password']//span[contains(text(),'Lozinka mora da sadrži minimalno 5 karaktera.')]")
    public WebElement passwordValidationMsg;
    // Prijavite se link
    @FindBy(xpath = "//a[@href='/login' and contains(text(),'Prijavite se')]")
    public WebElement loginLink;
    // Vratite se na početnu link
    @FindBy(xpath = "//p[@class='text-[10px] font-medium 2xl:text-xl']")
    public WebElement backToHomeLink;
    @FindBy(xpath = "//div[@role='alert']//..//div[contains(text(),'Molimo Vas da prihvatite uslove korišćenja!')]")
    public WebElement termsAndConditionMsg;
    @FindBy(xpath = "//div[contains(text(),'userAlreadyExists')]")
    public WebElement alrExistWindow;

    // First name enter method
    public void enterFirstName(String firstNameValue) {
        typeText(firstName, firstNameValue, " First name");
    }

    // Last name enter method
    public void enterLastName(String lastNameValue) {
        typeText(lastName, lastNameValue, " Last name");
    }

    // Address enter method
    public void enterAddress(String addressValue) {
        typeText(address, addressValue, " Address");
    }

    // Email enter method
    public void enterEmail(String emailValue) {
        typeText(email, emailValue, " Email");
    }

    // Password enter method
    public void enterPassword(String passwordValue) {
        typeText(password, passwordValue, " Password");
    }

    // User registration method
    public void userRegistrationMethod(String firstNameValue, String lastNameValue, String addressValue, String emailValue,
                                       String passwordValue) {
        enterFirstName(firstNameValue);
        enterLastName(lastNameValue);
        enterAddress(addressValue);
        enterEmail(emailValue);
        enterPassword(passwordValue);
        clickElement(checkbox, " checkbox field");
        clickElement(submitBtn, " submit button");
    }

    public void clickUserIcon() {
        clickElement(userIcon, " user icon button");
    }

    public void clickRegisterBtn() {
        clickElement(registerBtn, " register button");
    }

    public void assertErrorMsg() {
        Assert.assertEquals(firstNameErrMsg.getText(), "Obavezno polje");
        Assert.assertEquals(lastNameErrMsg.getText(), "Obavezno polje");
        Assert.assertEquals(addressErrMsg.getText(), "Obavezno polje");
        Assert.assertEquals(emailErrMsg.getText(), "Obavezno polje");
        Assert.assertEquals(passwordErrMsg.getText(), "Obavezno polje");
    }

    public void assertValidationMsg() {
        Assert.assertEquals(firstNameValidationMsg.getText(), "Sadrži nevažeće znakove.");
        Assert.assertEquals(lastNameValidationMsg.getText(), "Sadrži nevažeće znakove.");
        Assert.assertEquals(addressValidationMsg.getText(), "Sadrži nevažeće znakove.");
        Assert.assertEquals(emailValidationMsg.getText(), "Unesite ispravnu e-mail adresu.");
        Assert.assertEquals(passwordValidationMsg.getText(), "Lozinka mora da sadrži minimalno 5 karaktera.");
    }

    public void clickSignUpLink() {
        clickElement(loginLink, " prijavite se link");
    }

    public void backToHome() {
        clickElement(backToHomeLink, " Vratite se na početnu link");
    }
}
