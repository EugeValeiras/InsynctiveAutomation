package insynctive.pages.insynctive;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import insynctive.checklist.Checklist;
import insynctive.pages.Page;
import insynctive.pages.PageInterface;

public class CheckListsPage extends Page implements PageInterface{

	String enviroment;
	
	/* Checklists */
	@FindBy(id = "body_body_checklistsTab_gwProcesses_DXMainTable")
	WebElement personsTable;
	@FindBy(css = "#body_body_checklistsTab_TPTCR_btnAddChecklist_0_CD > span")
	WebElement btnStartChecklist;
	@FindBy(css = "#body_body_checklistsTab_TPTCR_btnTemplates_0_CD > span")
	WebElement btnTemplate;
	@FindBy(id = "body_body_checklistsTab_TC")
	WebElement tabsBody;
	@FindBy(id = "body_body_popupChecklist_lkpChoosePerson_I")
	WebElement personName;
	@FindBy(id = "body_body_popupChecklist_cboTemplateTransition_I")
	WebElement checklistName;
	@FindBy(id = "body_body_popupChecklist_btnStart")
	WebElement startChecklist;
	@FindBy(id = "body_body_popupChecklist_cboTemplateTransition_DDD_L_LBI4T0")
	WebElement firstChecklist;
	@FindBy(id= "body_body_popupChecklist_cbSkip_S_D")
	WebElement skipChecklists;
	@FindBy(xpath = ".//*[@id='body_body_popupChecklist_lkpChoosePerson_DDD_gv_tcrow0']/div")
	WebElement firstName;
	
	/* ADD Template iFrame */
	@FindBy(css = "#popupCustom_CIF-1")
	WebElement iframeAddTemplate;
	
	public CheckListsPage(WebDriver driver, String enviroment) {
		super(driver);
		this.enviroment = enviroment;
		this.PAGE_URL = "http://" + enviroment + ".insynctiveapps.com/Insynctive.Hub/Protected/CheckLists.aspx";
		this.PAGE_TITLE = "Checklists";
		PageFactory.initElements(driver, this);
	}

	/* Actions **/
	public void createTemplate(Checklist checkList) throws IOException, InterruptedException{
		waitUntilIsLoaded(btnTemplate);
		btnTemplate.click();
		swichToIframe(iframeAddTemplate);
		
		TemplatePage templatePage = new TemplatePage(driver, enviroment);
		templatePage.addTemplate(checkList);
	}
	
	public void startChecklist(String checkList, String person) throws IOException, InterruptedException
	{
		waitUntilIsLoaded(btnStartChecklist);
		btnStartChecklist.click();
		
		waitUntilIsLoaded(personName);
		personName.click();
		personName.sendKeys(person);
		personName.sendKeys(Keys.TAB);
			
		waitUntilIsLoaded(checklistName);
		checklistName.click();
		checklistName.sendKeys(checkList);
		waitUntilIsLoaded(firstChecklist);
		firstChecklist.click();

		waitUntilIsLoaded(skipChecklists);
		waitUntilIsLoaded(startChecklist);
		startChecklist.click();
	}
	
	/* Waits **/
	public void waitPageIsLoad() throws IOException, InterruptedException {
		waitUntilIsLoaded(personsTable);
		waitUntilIsLoaded(btnStartChecklist);
		waitUntilIsLoaded(btnTemplate);
		waitUntilIsLoaded(tabsBody);
	}

	/* Checks **/
	public boolean isPageLoad() {
		return btnStartChecklist.isDisplayed() && 
				btnTemplate.isDisplayed();
	}
}
