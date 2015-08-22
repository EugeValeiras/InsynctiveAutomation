package insynctive.pages;

import insynctive.pages.insynctive.exception.ElementIsAllwaysVisible;
import insynctive.pages.insynctive.exception.ElementNotFoundException;
import insynctive.pages.insynctive.exception.WrongMessageException;
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
    
    /* InApp */
	@FindBy(css = "#jqxNotificationDefaultContainer-top-right > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(2)")
	public WebElement inAppNotification;
	
	@FindBy(css = "#jqxNotificationDefaultContainer-top-right > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3) > div:nth-child(1)")
	public WebElement closeInAppNotification;

	@FindBy(id = "popupCustom_CIF-1")
	public WebElement taskPopup;
	
	@FindBy(id = "frmTask")
	public WebElement taskFrame;
    
    public Page(){
    	
    }
    
    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void swichToIframe(WebElement iframe) throws IOException, InterruptedException, ElementNotFoundException{
    	try{
    		waitUntilIsLoaded(iframe);
    		driver.switchTo().frame(iframe);
    	} catch (Exception ex){
    		throw new ElementNotFoundException(getMessageFromWebElement(iframe) +" is not found.", null);
    	}
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

    public void setElementText(WebElement element, String text) throws ElementNotFoundException {
        try{
        	element.clear();
        	element.sendKeys(text);
        	Assert.assertEquals(element.getAttribute("value"), text);
        } catch (NullPointerException nEx){
        	throw new ElementNotFoundException(getMessageFromWebElement(element)+" is NULL", null);
        }
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

    public void waitUntilIsLoaded(WebElement element) throws IOException, InterruptedException, ElementNotFoundException {
       try{
    	   new WebDriverWait(driver, SELENIUM_TIMEOUT_SEC).until(ExpectedConditions.visibilityOf(element));
       } catch (Exception ex){
    	   throw new ElementNotFoundException(getMessageFromWebElement(element)+" is not found", null);
       }
    }

    public void waitUntilnotVisibility(WebElement element) throws IOException, InterruptedException, Throwable {
    	int times = 0;
    	try{
    		while(element != null && times < 10){
    			Sleeper.sleep(2, driver);
    			times++;
    		}
    	} catch (Exception ex){
    		throw new ElementIsAllwaysVisible("The element "+getMessageFromWebElement(element)+" is still visible", null);
    	}
    }

    public void waitUntilNameIsVisible(WebElement element, String name) throws IOException, InterruptedException, ElementIsAllwaysVisible {
    	int times = 0;
    	try{
    		while(!element.getText().equals(name) && times < 10){
    			Sleeper.sleep(2, driver);
    			times++;
    		}
    	} catch (Exception ex){
    		throw new ElementIsAllwaysVisible("The text "+element.getText()+" is different from "+name, null);
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
    
    public void clickAButton(WebElement element) throws Exception {
    	waitUntilIsLoaded(element);
    	element.click();
    }
    
	public void setTextInField(WebElement textField, String text) throws ElementNotFoundException {
		try {
			textField.clear();
			textField.sendKeys(text);
		} catch (NullPointerException nEx){
			throw new ElementNotFoundException(getMessageFromWebElement(textField)+" is not found",null);
		}
	}
	
	public void setTextInCombo(WebElement combo, String text) throws ElementNotFoundException {
		try{
			combo.sendKeys(text);
			Sleeper.sleep(1000, driver);
			combo.sendKeys(Keys.ENTER);
		} catch (NullPointerException nEx){
			throw new ElementNotFoundException(getMessageFromWebElement(combo)+" is not found",null);
		}
	}
	
	public void checkInAppMessage(String assertMessage) throws WrongMessageException, IOException, InterruptedException, ElementNotFoundException {
		swichToFirstFrame(driver);
		waitUntilIsLoaded(inAppNotification);
		String ErrorMessage = "InApp message Do not match > InApp<"+inAppNotification.getText()+"> Expected<"+assertMessage+">";
		if(!inAppNotification.getText().equals(assertMessage)) throw new WrongMessageException(ErrorMessage, null);	
		closeInAppNotification.click();
	}
	public String getMessageFromWebElement(WebElement element){
		String[] elementSplit = element.toString().split("-> ");
		return (elementSplit.length > 0) ? 
				(elementSplit[1].split("]").length > 0 ? 
						elementSplit[1].split("]")[0] : elementSplit[1]) 
				: element.toString(); 
	}
}