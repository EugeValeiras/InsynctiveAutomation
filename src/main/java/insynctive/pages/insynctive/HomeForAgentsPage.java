package insynctive.pages.insynctive;

import insynctive.pages.Page;
import insynctive.pages.PageInterface;
import insynctive.utils.PersonData;
import insynctive.utils.Sleeper;

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
	@FindBy(id = "btnAddPerson")
	WebElement addPersonButton;
	@FindBy(id = "grid")
	WebElement personTable;
	@FindBy(id = "txtName")
	WebElement nameSearch;
	@FindBy(id = "txtPrimaryEmail")
	WebElement emailSearch;
	@FindBy(xpath = "//*[@id='grid']/div[1]/div[2]/p")
	WebElement personLink;
	@FindBy(xpath = "//*[@id='grid']/div[1]/div[6]/p")
	WebElement emailAssert;
	@FindBy(id = "loadingSpinner")
	WebElement loadingSpinner;
	
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
	@FindBy(id = "JQWindowBigOverlayCenterHeader")
	WebElement personFileTitle;

	/* Import Persons */
	@FindBy(id = "btnImportAndSync")
	WebElement importPersonButton;
	
	/* Template Page */
	@FindBy(id = "tab_Apps")
	WebElement tabApp;

	public HomeForAgentsPage(WebDriver driver, String enviroment) {
		super(driver);
		this.enviroment = enviroment;
		this.PAGE_URL = "http://" + enviroment + ".insynctiveapps.com/Insynctive.Hub/Protected/Invitations.aspx?SkipGuide=True";
		this.PAGE_TITLE = "Invitations";
		PageFactory.initElements(driver, this);
	}

	public void createPerson(PersonData personData) throws Exception {
		waitPageIsLoad();
		addPersonButton.click();
		waitAddPersonIsLoad();
		setTextInField(firstNameTextBox, personData.getName());
		setTextInField(lastNameTextBox, personData.getLastName());
		setTextInField(emailTextBox, personData.getEmail());
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

	public void openPersonFile(String personName) throws IOException, InterruptedException {
		waitPageIsLoad(); 
		emailSearch.sendKeys(personName);
		waitUntilnotVisibility(loadingSpinner);
		Sleeper.sleep(8000,driver);
		waitUntilIsLoaded(personLink);
		personLink.click();
	}

	public boolean isPersonFileOpened() {
		return personFileTitle.getText().equals("Person");
	}

}
