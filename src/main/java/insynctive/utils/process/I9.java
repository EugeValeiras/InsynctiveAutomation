package insynctive.utils.process;

import java.io.IOException;

import insynctive.pages.Page;
import insynctive.pages.PageInterface;
import insynctive.pages.insynctive.exception.ElementNotFoundException;
import insynctive.utils.Sleeper;
import insynctive.utils.WhenStart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class I9 extends Process implements PageInterface {

	@FindBy(css = "#searchAppsResult > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > a:nth-child(1)")
	public WebElement processLink;
	public String TaskName = "Fill in and sign I-9 Form";
	
	//First Step
	@FindBy(id = "representativeKey")
	WebElement companyRepresentative;
	@FindBy(id = "btnYourselfRepresentative")
	WebElement pickYourselt;
	
	//Second Step
	@FindBy(id = "changeStartConditions")
	WebElement changeStartCondition;
	@FindBy(id = "immeditStartConditions")
	WebElement startInmediatlyAfterCertainAct;
	@FindBy(id = "asapStartConditions")
	WebElement startASAP;
	
	public I9(WhenStart whenStart) {
		this.whenStart = whenStart;
	}

	@Override
	public void completeSteps() throws Exception {
		waitPageIsLoad();
		swichToIframe(contentiframe);
		clickAButton(companyRepresentative);
		clickAButton(pickYourselt);
		swichToFirstFrame(driver);
		clickAButton(nextButton);
		//TODO
//		clickAButton(changeStartCondition);
//		clickAButton(startASAP);
		Sleeper.sleep(4000, driver);
		clickAButton(nextButton);
		Sleeper.sleep(3000, driver);
	}
	
	@Override
	public void waitPageIsLoad() throws IOException, InterruptedException, ElementNotFoundException{
		waitUntilIsLoaded(nextButton);
		swichToIframe(contentiframe);
		waitUntilIsLoaded(companyRepresentative);
		swichToFirstFrame(driver);
	}

	@Override
	public boolean isPageLoad() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public WebElement getElement() throws IOException, InterruptedException, ElementNotFoundException{
		waitUntilIsLoaded(processLink);
		return processLink;
	}
}
