package insynctive.pages;

import insynctive.utils.Sleeper;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Page {

	public static final int SELENIUM_TIMEOUT_SEC = 30;
    public WebDriver driver;
    public String PAGE_URL;
    public String PAGE_TITLE;
    
	@FindBy(id = "popupCustom_CIF-1")
	public WebElement taskPopup;
	@FindBy(id = "frmTask")
	public WebElement taskFrame;
    
    public Page(){
    	
    }
    
    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void swichToIframe(WebElement iframe) throws IOException, InterruptedException{
    	waitUntilIsLoaded(iframe);
		driver.switchTo().frame(iframe);
    }
    
    public static boolean exists(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException ignored) {
            return false;
        }
    }

    public String getPageUrl() {
        return PAGE_URL;
    }

    public String getPageTitle() {
        return PAGE_TITLE;
    }

    public void loadPage() {
        driver.get(getPageUrl());
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void setElementText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        Assert.assertEquals(element.getAttribute("value"), text);
    }

    public void assertElementText(WebElement element, String text) {
        Assert.assertEquals(element.getAttribute("value"), text);
    }

    public void checkIfElementVisible(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "element isn't found");
    }

    public String getDriverTitle() {
        return driver.getTitle();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void waitUntilIsLoaded(WebElement element) throws IOException, InterruptedException {
        new WebDriverWait(driver, SELENIUM_TIMEOUT_SEC).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilnotVisibility(WebElement element) throws IOException, InterruptedException {
    	int times = 0;
    	while(element != null && times < 10){
			Sleeper.sleep(2, driver);
			times++;
		}
    }

    public void waitUntilNameIsVisible(WebElement element, String name) throws IOException, InterruptedException {
    	int times = 0;
    	while(!element.getText().equals(name) && times < 10){
    		Sleeper.sleep(2, driver);
    		times++;
    	}
    }
 
    public void waitUntilTitleContains(String str) throws IOException, InterruptedException {
    	new WebDriverWait(driver, SELENIUM_TIMEOUT_SEC).until(ExpectedConditions.titleContains(str));
    }

    public void selectValueInDropdown(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public boolean isElementPresent(WebElement element) {
        try{
            element.getTagName();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public boolean isElementExisting(WebElement we) {
        try {
            we.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void turnOffImplicitWaits() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    protected void turnOnImplicitWaits(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    protected boolean isElementHiddenNow(String locator) {
        turnOffImplicitWaits();
        boolean result = false;
        try {
            result = ExpectedConditions.invisibilityOfElementLocated(By.id(locator)).apply(driver);
        } finally {
            turnOnImplicitWaits(5);
        }
        return result;
    }

    public boolean isElementPresentbySize(String locator) {
        return driver.findElements(By.id(locator)).size() > 0;
    }

    public boolean isElementDisplayedNowOLD(WebElement element) {
        turnOffImplicitWaits();
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        } finally {
            turnOnImplicitWaits(5);
        }
    }
    
    public void swichToFirstFrame(WebDriver driver){
    	driver.switchTo().window(driver.getWindowHandle());
    }
    
    public void clickAButton(WebElement element) throws IOException, InterruptedException{
    	waitUntilIsLoaded(element);
    	element.click();
    }
    
	public void setTextInField(WebElement textField, String text) {
		textField.clear();
		textField.sendKeys(text);
	}
	
	public void setTextInCombo(WebElement combo, String text) {
		combo.sendKeys(text);
		Sleeper.sleep(1000, driver);
		combo.sendKeys(Keys.ENTER);
	}
	
}