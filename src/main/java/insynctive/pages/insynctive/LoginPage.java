package insynctive.pages.insynctive;

import insynctive.pages.Page;
import insynctive.pages.PageInterface;
import insynctive.utils.LoginData;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends Page implements PageInterface{
	
	/* Account of test */
	String enviroment;
	
	/* Login */
    @FindBy(id = "login_UserName_I")
     WebElement loginUsernameField;
    @FindBy(id = "login_Password_I")
     WebElement loginPasswordField;
    @FindBy(id = "login_Login_CD")
    WebElement loginButton;
    @FindBy(id = "google-login")
    WebElement googleLoginButton;

    public LoginPage(WebDriver driver, String enviroment) {
        super (driver);
        this.enviroment = enviroment;
        this.PAGE_URL = "http://"+ enviroment + ".insynctiveapps.com/";
        this.PAGE_TITLE = "Login";
        PageFactory.initElements(driver, this);
    }

    /* Actions **/
    public void login(String email, String password) throws Exception {
        setText_LoginField(email);
        setText_PassField(password);
        clickToLogin();
    }
    
    public  void clickToLogin() {
        loginButton.click();
    }
    
    public void fillLoginForm(LoginData loginData) {
        loginUsernameField.sendKeys(loginData.getUserName());
        loginPasswordField.click();
        loginPasswordField.sendKeys(loginData.getPass());
    }
    
    /* Waits **/
    public void waitPageIsLoad() throws IOException, InterruptedException{
    	waitUntilIsLoaded(loginUsernameField);
    	waitUntilIsLoaded(loginUsernameField);
    	waitUntilIsLoaded(loginButton);
    }

    /* Private Actions **/
    private void setText_LoginField(String text) {
        loginUsernameField.clear();
    	loginUsernameField.sendKeys(text);
        loginUsernameField.sendKeys(Keys.TAB);
    }

    private void setText_PassField(String text) {
    	loginPasswordField.clear();
        loginPasswordField.sendKeys(text);
    }
    
    /* Cheks **/
    public boolean isPageLoad(){
		return loginButton.isDisplayed() 
				&& loginPasswordField.isDisplayed() 
				&& loginUsernameField.isDisplayed();
	}
    
    public boolean isLoggedIn() throws Exception{
    	HomeForAgentsPage homePage = new HomeForAgentsPage(driver, enviroment);
    	homePage.waitPageIsLoad();
    	return driver.getTitle().equals(homePage.PAGE_TITLE);
    }
    
    public  boolean isNotLoggedIn() {
        return driver.findElements(By.xpath("//span[@class='js-auth-signin b-navbar__exit h-ml-10']")).size() > 0;
    }
}
