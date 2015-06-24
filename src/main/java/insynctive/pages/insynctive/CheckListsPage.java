package insynctive.pages.insynctive;

import java.io.IOException;

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
