package insynctive.checklist.process;

import insynctive.pages.Page;
import insynctive.pages.PageInterface;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class W4 extends Page implements Process, PageInterface {

	String version;
	
	/* Checklist */
	@FindBy(id = "btnActivity_CD")
	WebElement btnAddV4Process;
	
	@FindBy(id = "popupCustom_CIF-1")
	WebElement teplateiFrame;

	@FindBy(id = "popupNoHeader_CIF-1")
	WebElement w4Iframe;

	/* W4 */
	@FindBy(css = "#searchAppsResult > table > tbody > tr:nth-child(3) > td:nth-child(2) > table > tbody > tr > td > a")
	WebElement linkTask;
	
	public W4(WebDriver driver) {
		super(driver);
		this.PAGE_URL = "NO PAGE";
		this.PAGE_TITLE = "Apps";
		PageFactory.initElements(driver, this);
	}
	
	@Override
	public void createTask() throws IOException, InterruptedException{
		waitPageIsLoad();
		btnAddV4Process.click();
		swuichToIframe(w4Iframe);
		linkTask.click();
	}
	
	@Override
	public void waitPageIsLoad() throws IOException, InterruptedException {
		waitUntilIsLoaded(btnAddV4Process);
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Process: " + this.getClass().getSimpleName() + " | Version: " + version;
	}

	@Override
	public boolean isPageLoad() {
		return btnAddV4Process.isDisplayed();
	}
}

