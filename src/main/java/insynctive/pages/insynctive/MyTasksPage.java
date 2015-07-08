package insynctive.pages.insynctive;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import insynctive.pages.Page;
import insynctive.pages.PageInterface;

public class MyTasksPage extends Page implements PageInterface {
	
	String enviroment;
	
	@FindBy (id = "body_body_tasksListN_row0_lnkTaskName_0")
	WebElement firstTask;
	@FindBy (id = "popupCustom_CIF-1")
	WebElement taskPopup;
	@FindBy (id = "frmTask")
	WebElement taskFrame;
	@FindBy (id = "btnAcknowledge")
	WebElement singBtn;
	@FindBy (id = "popupSign_panelSign_name")
	WebElement signTextType;
	@FindBy (id = "rappdf")
	WebElement pdfIframe;
	@FindBy (id="btnApplySig_CD")
	WebElement btnApplySignature;
	@FindBy (id="pdfWebControl")
	WebElement radPdfIframe;
	@FindBy (id="pdfe_paged0")
	WebElement pdfPage;
	@FindBy (xpath = ".//*[@id='buttonDone']/button")
	WebElement DoneButton;
	@FindBy (id = "pdfe_msgt")
	WebElement banner;
	
	
	public MyTasksPage(WebDriver driver, String enviroment) {
		super(driver);
		this.enviroment = enviroment;
		this.PAGE_URL = "http://" + enviroment + ".insynctiveapps.com/Insynctive.Hub/Protected/MyTasks.aspx?view=agent";
		this.PAGE_TITLE = "MyTasks";
		PageFactory.initElements(driver, this);
	}
	
	public void openFirstTask()
	{
		firstTask.click();
	}
	public boolean SingPDF() throws IOException, InterruptedException
	{
		driver.wait(20000);
		String outerIframe = driver.getWindowHandle();
		waitUntilIsLoaded(taskPopup);
		swichToIframe(taskPopup);
		waitUntilIsLoaded(taskFrame);
		swichToIframe(taskFrame);
		waitUntilIsLoaded(singBtn);
		
		singBtn.click();
		driver.wait(5000);
		waitUntilIsLoaded(pdfIframe);
		swichToIframe(pdfIframe);
		waitUntilIsLoaded(signTextType);
		signTextType.sendKeys("Insynctive test");
		btnApplySignature.click();
		
		swichToIframe(radPdfIframe);
		driver.wait(5000);
		waitUntilIsLoaded(pdfPage);
		
		Actions act = new Actions(driver);
		act.moveToElement(pdfPage,15,15).click().build().perform();
		driver.wait(5000);
	
		driver.switchTo().window(outerIframe);
		swichToIframe(taskPopup);
		swichToIframe(taskFrame);
		waitUntilIsLoaded(DoneButton);
		DoneButton.click();
		driver.wait(5000);
		
		boolean isHere = driver.findElements(By.xpath(".//*[@id='buttonDone']/button")).size() > 0;
		if(isHere)
			return false;
		else
			return true;
	}

	@Override
	public boolean isPageLoad() {
		return isElementPresent(firstTask);
	}

	@Override
	public void waitPageIsLoad() throws IOException, InterruptedException {
		waitUntilIsLoaded(firstTask);
	}
	
	
	

}
