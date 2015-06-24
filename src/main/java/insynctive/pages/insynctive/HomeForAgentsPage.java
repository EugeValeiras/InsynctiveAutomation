package insynctive.pages.insynctive;

import insynctive.pages.Page;
import insynctive.pages.PageInterface;
import insynctive.utils.PersonData;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//TODO get subject of email.
public class HomeForAgentsPage extends Page implements PageInterface {

	String enviroment;
	String emailSubject;
	
	/* Home */
	@FindBy(css = "#body_body_mainTab_TPTCR_btnAddPerson_0_CD > span")
	WebElement addPersonButton;
	@FindBy(id = "ctl00_ctl00_body_body_mainTab_grdListPeople_DXMainTable")
	WebElement personTable;
	
	/* ADD Person */
	@FindBy(id = "firstNameTextBox")
	WebElement firstNameTextBox;
	@FindBy(id = "lastNameTextBox")
	WebElement lastNameTextBox;
	@FindBy(id = "emailTextBox")
	WebElement emailTextBox;
	@FindBy(id = "inviteToSSCheckBox")
	WebElement inviteToCheckBox;
	@FindBy(id = "btnSaveNewPerson")
	WebElement saveButton;
	@FindBy(id = "invitationHtmlContent")
	WebElement invitationHtmlContent;
	@FindBy(id = "//*[@id=\"dvSubjectPreview\"]/span")
	WebElement subjectOfEmail;
	
	/* Person File */
	@FindBy(id = "popupCustom_CIF-1")
	WebElement personFIleIframe;
	@FindBy(id = "body_txtEmail")
	WebElement emailLink;

	/* Import Persons */
	@FindBy(id = "body_body_mainTab_TPTCR_btnImport_0_CD")
	WebElement importPersonButton;
	
	/* Template Page */
	@FindBy(id = "tab_Apps")
	WebElement tabApp;

	public HomeForAgentsPage(WebDriver driver, String enviroment) {
		super(driver);
		this.enviroment = enviroment;
		this.PAGE_URL = "http://" + enviroment + ".insynctiveapps.com/Insynctive.Hub/Protected/Invitations.aspx?page";
		this.PAGE_TITLE = "Invitations";
		PageFactory.initElements(driver, this);
	}

	public void createPerson(PersonData personData) throws Exception {
		waitPageIsLoad();
		addPersonButton.click();
		waitAddPersonIsLoad();
		setText_FirstNameField(personData.getName());
		setText_LastNameField(personData.getLastName());
		setText_EmailField(personData.getEmail());
		inviteToCheckBox.click();
		waitInvitePersonLoad();
//		personData.setEmailInvitationSubject(subjectOfEmail.getText()); 
		saveButton.click();
	}
	
	/* Actions **/
	public void importPersons() throws Exception{
		waitUntilIsLoaded(importPersonButton);
		importPersonButton.click();
		
		ImportPersonPage importPersonPage = new ImportPersonPage(driver, enviroment);
		importPersonPage.importPersons();
	}
	
	public void goToApps() throws Exception{
		waitUntilIsLoaded(tabApp);
		tabApp.click();
	}
	

	/* Waits **/
	public void waitInvitePersonLoad() throws IOException,
			InterruptedException {
		waitUntilIsLoaded(saveButton);
		waitUntilIsLoaded(invitationHtmlContent);
	}

	public void waitPageIsLoad() throws IOException,
	InterruptedException {
		waitUntilIsLoaded(importPersonButton);
		waitUntilIsLoaded(personTable);
	}

	public void waitAddPersonIsLoad() throws Exception {
		waitUntilIsLoaded(firstNameTextBox);
		waitUntilIsLoaded(lastNameTextBox);
		waitUntilIsLoaded(emailTextBox);
		waitUntilIsLoaded(inviteToCheckBox);
	}

	/* Private Actions **/
	private void setText_FirstNameField(String text) {
		firstNameTextBox.clear();
		firstNameTextBox.sendKeys(text);
		firstNameTextBox.sendKeys(Keys.TAB);
	}
	
	private void setText_LastNameField(String text) {
		lastNameTextBox.clear();
		lastNameTextBox.sendKeys(text);
		lastNameTextBox.sendKeys(Keys.TAB);
	}
	
	private void setText_EmailField(String text) {
		emailTextBox.clear();
		emailTextBox.sendKeys(text);
		emailTextBox.sendKeys(Keys.TAB);
	}
	
	/* Checks **/
	public boolean isPageLoad(){
		return importPersonButton.isDisplayed() 
				&& personFIleIframe.isDisplayed();
	}
	
	public boolean checkIfPersonIsCreated(PersonData personData){
		try{
			//Check if Iframe is open
			waitUntilIsLoaded(personFIleIframe);
//			swichToIframe(personFIleIframe);
//			waitUntilIsLoaded(emailLink);
//			assertElementText(emailLink, personData.getEmail());
		} catch (Exception e){
			return false;
		}
		return true;
	}

}
