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

	/* W4 */
	@FindBy(css = "#searchAppsResult > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > a:nth-child(1)")
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
		waitUntilIsLoaded(linkTask);
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

