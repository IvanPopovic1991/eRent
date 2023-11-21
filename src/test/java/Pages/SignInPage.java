package Pages;

import Tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='relative flex h-20 w-20 cursor-pointer items-center justify-center bg-primary-yellow lg:ml-auto ']")
    public WebElement userIcon;

    @FindBy(xpath = "//div[@class='flex w-full flex-col  text-[#898989]']//a[@href='/login']")
    public WebElement logInBtn;

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signInBtn;

    @FindBy(xpath ="//div[@role='alert']//div[contains(text(),'Korisnik ne postoji')]")
    public WebElement userNotExist;

    @FindBy(xpath = "//label[@for='email']//span[contains(text(),'Obavezno polje')]")
    public WebElement emailErrorMsg;

    @FindBy(xpath = "//label[@for='password']//span[contains(text(),'Obavezno polje')]")
    public WebElement passwordErrMsg;

    @FindBy(xpath = "//label[@for='email']//span[contains(text(),'Unesite ispravnu e-mail adresu.')]")
    public WebElement nonValidMEmail;

    @FindBy(xpath = "//label[@for='password']//span[contains(text(),'Lozinka mora da sadrži minimalno 5 karaktera.')]")
    public WebElement nonValidPassword;

    @FindBy(xpath = "//a[@href='/change-password']")
    WebElement changePassword;

    @FindBy(xpath = "//a[@href='/register']")
    WebElement registerLink;

    @FindBy(xpath = "//p[contains(text(),'Vratite se na početnu')]")
    public WebElement backToHomeLink;

    @FindBy(xpath = "//div[@role='button']")
    public WebElement signInWithGoogleBtn;

    @FindBy(xpath = "//input[@type='email']")
    public WebElement gmail;

    @FindBy(xpath = "//div[@id='identifierNext']")
    WebElement nextBtn;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement gmailPassword;

    @FindBy(xpath = "//div[@id='passwordNext']")
    WebElement passwordNextBtn;

    //Click on the user icon on Home page
    public void clickUserIcon() {
        clickElement(userIcon, " user icon on Home page");
    }

    // Click on UlogujSe/SignIn button
    public void clickLogInBtn() {
        clickElement(logInBtn, " sign in / uloguj se button");
    }

    // Enter email function
    public void enterEmail(String emailValue) {
        typeText(email, emailValue, " email");
    }

    // Enter password function
    public void enterPassword(String passwordValue) {
        typeText(password, passwordValue, " password");
    }

    // Sign In complete flow method
    public void signedInUser(String emailValue, String passwordValue) {
        clickUserIcon();
        clickLogInBtn();
        enterEmail(emailValue);
        enterPassword(passwordValue);
        clickElement(signInBtn, " sign in button");
    }
    public void forgotPasswordLinkRedirection(){
        clickElement(changePassword," zaboravili ste lozinku? link");
    }

    public void registerLinkRedirection(){
        clickElement(registerLink," Registruj se brzo i lako link");
    }
    public void backToHomePage(){
        clickElement(backToHomeLink," Vratite se na početnu link");
    }

    public void signInWithGoogleBtnMethod(String gmailValue, String gmailPasswordValue){
        typeText(gmail,gmailValue," email ");
        clickElement(nextBtn," Dalje button");
        typeText(gmailPassword,gmailPasswordValue," password ");
        clickElement(passwordNextBtn," 2nd Dalje button");
    }
}
