package insynctive.utils.process;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import insynctive.pages.PageInterface;
import insynctive.pages.insynctive.exception.ElementNotFoundException;
import insynctive.utils.Sleeper;
import insynctive.utils.WhenStart;

public class W4 extends Process implements PageInterface {
	
	@FindBy(css = "#searchAppsResult > table > tbody > tr:nth-child(5) > td:nth-child(2) > table > tbody > tr > td > a")
	public WebElement processLink;
	@FindBy(id = "lnkChangeIsRequiredActiveEmployeeW2")
	public WebElement chngeRequiredActiveEMployee;
	
	public String TaskName = "Fill in and sign W-4 Form";
	public boolean needActiveEmployee;
	
	public W4(WhenStart whenStart, boolean needActiveEmployee) {
		this.whenStart = whenStart;
		this.needActiveEmployee = needActiveEmployee;
	}

	@Override
	public void completeSteps() throws Exception {
		waitPageIsLoad();
		swichToIframe(contentiframe);
		if(needActiveEmployee) clickAButton(chngeRequiredActiveEMployee);
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
	public boolean isPageLoad() {
		return false;
	}

	@Override
	public void waitPageIsLoad() throws Exception {
		waitUntilIsLoaded(nextButton);
		swichToIframe(contentiframe);
		waitUntilIsLoaded(chngeRequiredActiveEMployee);
		swichToFirstFrame(driver);
	}
	
	@Override
	public WebElement getElement() throws IOException, InterruptedException, ElementNotFoundException{
		waitUntilIsLoaded(processLink);
		return processLink;
	}

}

