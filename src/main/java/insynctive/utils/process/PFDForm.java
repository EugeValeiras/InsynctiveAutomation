package insynctive.utils.process;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import insynctive.pages.PageInterface;
import insynctive.utils.WhenStart;

public class PFDForm extends Process implements PageInterface {

	@FindBy(css = "#searchAppsResult > table > tbody > tr:nth-child(2) > td:nth-child(2) > table > tbody > tr > td > a")
	public WebElement processLink;
	
	public PFDForm(WhenStart whenStart) {
		this.whenStart = whenStart;
	}

	@Override
	public void waitPageIsLoad() throws Exception, Throwable {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean isPageLoad() {
		// TODO Auto-generated method stub
		return false;
	}
}
