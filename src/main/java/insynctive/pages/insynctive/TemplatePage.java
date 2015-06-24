package insynctive.pages.insynctive;

import insynctive.checklist.Checklist;
import insynctive.checklist.process.Process;
import insynctive.pages.Page;
import insynctive.pages.PageInterface;
import insynctive.utils.Debugger;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TemplatePage extends Page implements PageInterface{

	String enviroment;
	
	/* Templates Page */
	@FindBy(id = "lblTitle")
	WebElement labelTemplates;
	@FindBy(css = "#btnAddTemplate_CD > span")
	WebElement btnAddTemplate;
	@FindBy(id = "gvTemplates_DXMainTable")
	WebElement tableTemplates;
	
	//#gvTemplates_DXDataRow0 > 
		//#gvTemplates_tccell0_0 > 
			//a <== First table row
	
	/* Add Template */
	@FindBy(id = "gvTemplates_DXPEForm_efnew_txtTemplateName_I")
	WebElement txtBoxAddTemplateName;
	@FindBy(css = "#gvTemplates_DXPEForm_efnew_btnUpdate_CD > span")
	WebElement btnOkAddTemplate;
	
	/* Edit Template */
	@FindBy(id = "lnkReturn_CD")
	WebElement btnReturnToTemplate;
	@FindBy(id = "ASPxButton1_CD")
	WebElement btnAddV3Process;
	@FindBy(id = "btnActivity_CD")
	WebElement btnAddV4Process;
	@FindBy(id = "callbackPanel")
	WebElement tableOfProcess;
	
	@FindBy(id = "popupCustom_CIF-1")
	WebElement iframeTemplate;
	
	public TemplatePage(WebDriver driver, String enviroment) {
		super(driver);
		this.enviroment = enviroment;
		this.PAGE_URL = "http://" + enviroment + ".insynctiveapps.com/Insynctive.Hub/Protected/Templates.aspx?";
		this.PAGE_TITLE = "Templates";
		PageFactory.initElements(driver, this);
	}

	/* Actions **/
	public void addTemplate(Checklist checkList) throws IOException, InterruptedException{
		waitUntilIsLoaded(btnAddTemplate);
		btnAddTemplate.click();
		waitAddTemplateLoad();
		setText_TemplateName(checkList.getName()); //<-- TODO Get ID of Test Run
		btnOkAddTemplate.click();
		
		waitEditTemplateLoad();
		for(Process proc : checkList.getProcess()){
			Debugger.subLog(proc);
			proc.createTask();
		}
	}
	
	/* Waits **/
	public void waitPageIsLoad() throws IOException, InterruptedException {
		waitUntilIsLoaded(labelTemplates);
		waitUntilIsLoaded(btnAddTemplate);
		waitUntilIsLoaded(tableTemplates);
	}
	
	public void waitAddTemplateLoad() throws IOException, InterruptedException{
		waitUntilIsLoaded(txtBoxAddTemplateName);
		waitUntilIsLoaded(btnOkAddTemplate);
	}

	public void waitEditTemplateLoad() throws IOException, InterruptedException{
		waitUntilIsLoaded(btnReturnToTemplate);
		waitUntilIsLoaded(btnAddV4Process);
	}
	
	/* Private Actions **/
	private void setText_TemplateName(String text) {
		txtBoxAddTemplateName.clear();
		txtBoxAddTemplateName.sendKeys(text);
	}
	
	/* Checks **/
	public boolean isPageLoad() {
		return btnAddTemplate.isDisplayed() && 
				tableTemplates.isDisplayed();
	}
	
	public boolean isAddTemplateLoad() {
		return btnOkAddTemplate.isDisplayed() && 
				txtBoxAddTemplateName.isDisplayed();
	}
	
	public boolean isEdiTemplateLoad() {
		return btnReturnToTemplate.isDisplayed() && 
				btnAddV3Process.isDisplayed() &&
				btnAddV4Process.isDisplayed();
	}
}
