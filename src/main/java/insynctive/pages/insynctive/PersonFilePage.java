package insynctive.pages.insynctive;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import insynctive.pages.Page;
import insynctive.pages.PageInterface;
import insynctive.pages.insynctive.PersonFilePage.gender;
import insynctive.pages.insynctive.PersonFilePage.maritalStatus;
import insynctive.utils.Sleeper;

public class 
PersonFilePage extends Page implements PageInterface {

	String enviroment;
	
	public enum gender{
		MALE("Male"), FEMALE("Female"), UNKNOWN("Unknown"); 
		public final String name;
		
		gender(String name){
			this.name = name;
		}
	}
	
	public enum maritalStatus{
		UNKNOWN("Unknown"), SINGLE("Single"), MARRIED("Married"), DIVORCED("Divorced"), WIDOWER("Widower"), PARTNER("Partner"); 
		public final String status;
		
		maritalStatus(String status){
			this.status = status;
		}
	}
	
	@FindBy(id = "phone-text")
	WebElement phoneNumberLink;
	@FindBy(id = "title")
	WebElement titleLink;
	@FindBy(id = "department")
	WebElement departamentLink;
	@FindBy(id = "loadingSpinner")
	WebElement loadingSpinner;
	
	//Links
	@FindBy(xpath = "//*[@id=\"full-name-field\"]/div[1]/span")
	WebElement fullNameLink;
	@FindBy(id = "date-picker")
	WebElement birthDateLink;
	@FindBy(className = "gender-picker")
	WebElement genderLink;
	@FindBy(className = "marital-picker")
	WebElement maritalLink;
	@FindBy(className = "email-primary")
	WebElement primaryEmailLink;
	
	//iFrames
	@FindBy(id = "tabFrame")
	WebElement tabiFrame;
	@FindBy(css = ".standard-popover-content > iframe:nth-child(1)")
	WebElement editNameiFrame;
	@FindBy(css = ".standard-popover-content > iframe:nth-child(1)")
	WebElement birthDateiFrame;
	@FindBy(css = ".standard-popover-content > iframe:nth-child(1)")
	WebElement primaryEmailiFrame;
	@FindBy(css = ".standard-popover-content > iframe:nth-child(1)")
	WebElement titleiFrame;
	
	//title
	@FindBy(id = "Title")
	WebElement titleField;
	@FindBy(id = "Department")
	WebElement departamentField;
	@FindBy(id = "saveBtn")
	WebElement saveChangeTitle;
	
	//FullName
	@FindBy(id = "FirstName")
	WebElement firstName;
	@FindBy(id = "LastName")
	WebElement lastName;
	@FindBy(id = "MiddleName")
	WebElement middleName;
	@FindBy(id = "PreviousName")
	WebElement maidenName;
	@FindBy(xpath = "//*[@id='saveBtn']/span[1]")
	WebElement saveChangeFullName;

	//Birth Date
	@FindBy(id = "dateInput")
	WebElement dateInput;
	@FindBy(id = "btnSave")
	WebElement saveChangeBirthDate;
	
	//Primary Email
	@FindBy(className = "editor-text-input")
	WebElement primaryEmailField;
	@FindBy(className = "accept-button")
	WebElement saveChangePrimaryEmail;
	@FindBy(id = "saveBtn")
	WebElement savePrimaryEmailChange;
	
	public PersonFilePage(WebDriver driver, String enviroment) {
		super(driver);
		this.enviroment = enviroment;
		this.PAGE_URL = "NO URL";
		this.PAGE_TITLE = "NO TITLE";
		PageFactory.initElements(driver, this);
	}
	
	public void changeName(String nameIn, String lastNameIn, String middleNameIn, String maidenNameIn) throws IOException, InterruptedException{
		waitPageIsLoad();
		fullNameLink.click();
		swichToIframe(editNameiFrame);
		setTextInField(firstName, nameIn);
		setTextInField(lastName, lastNameIn);
		setTextInField(middleName, middleNameIn);
		setTextInField(maidenName, maidenNameIn);
		saveChangeFullName.click();
		waitUntilnotVisibility(saveChangeFullName);
	}
	
	public void changeGender(gender gender) throws IOException, InterruptedException{
		waitPageIsLoad();
		genderLink.click();
		driver.findElement(By.xpath("//div[@class='enum-item' and contains(., '"+gender.name+"')]")).click();
	}

	public void changeBirthDate(String birthDate) throws IOException, InterruptedException {
		waitPageIsLoad();
		birthDateLink.click();
		swichToIframe(birthDateiFrame);
		waitUntilnotVisibility(loadingSpinner);
		waitUntilIsLoaded(dateInput);
		//TODO REMOVE THIS SLEEPER
		Sleeper.sleep(5000, driver);
		setTextInField(dateInput, birthDate);
		saveChangeBirthDate.click();
	}
	
	public void changeMaritalStatus(maritalStatus status) throws IOException, InterruptedException{
		waitPageIsLoad();
		maritalLink.click();
		driver.findElement(By.xpath("//div[@class='enum-item' and contains(., '"+status.status+"')]")).click();
	}

	public void changePrimaryEmail(String newEmail) throws IOException, InterruptedException {
		waitPageIsLoad();
		Sleeper.sleep(1000, driver);
		primaryEmailLink.click();
		waitUntilnotVisibility(loadingSpinner);
		swichToIframe(primaryEmailiFrame);
		primaryEmailLink.click();
		setTextInField(primaryEmailField, newEmail);
		saveChangePrimaryEmail.click();
		savePrimaryEmailChange.click();
		waitUntilnotVisibility(saveChangePrimaryEmail);
	}

	public void changeTitle(String title, String departament) throws IOException, InterruptedException {
		waitPageIsLoad();
		swichToFirstFrame(driver);
		titleLink.click();
		swichToIframe(titleiFrame);
		setTextInField(titleField, title);
		setTextInField(departamentField, departament);
		saveChangeTitle.click();
		waitUntilnotVisibility(saveChangeTitle);
	}

	public boolean isChangePrimaryEmail(String email) throws IOException, InterruptedException {
		waitPageIsLoad();
		return primaryEmailField.getText().equals(email);
	}

	public boolean isChangeName(String name, String lastName, String middlename,
			String maidenName) throws IOException, InterruptedException {
		waitPageIsLoad();
		String assertName = name+" "+middlename;
		assertName += (!maidenName.equals("")) ? " ("+maidenName+") " : " ";
		assertName += lastName;
		
		return fullNameLink.getText().equals(assertName);
	}

	public boolean isChangeGender(gender genderType) throws IOException, InterruptedException {
		waitPageIsLoad();
		return genderLink.getText().equals(genderType.name);
	}

	public boolean isChangeMaritalStatus(maritalStatus maritalStat) throws IOException, InterruptedException {
		waitPageIsLoad();
		return maritalLink.getText().equals(maritalStat.status);
	}
	
	@Override
	public boolean isPageLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void waitPageIsLoad() throws IOException, InterruptedException {
		swichToFirstFrame(driver);
		waitUntilnotVisibility(loadingSpinner);
		swichToIframe(tabiFrame);
		waitUntilIsLoaded(birthDateLink);
		waitUntilIsLoaded(genderLink);
		waitUntilIsLoaded(maritalLink);
		waitUntilIsLoaded(primaryEmailLink);
		waitUntilIsLoaded(fullNameLink);
		Sleeper.sleep(2000, driver);
	}

	public boolean isChangeTitle(String title, String departament) {
		swichToFirstFrame(driver);
		boolean assertTitle =((titleLink == null && departament.equals("")) || titleLink.getText().equals(title));
		boolean assertDepartament =((departamentLink == null && departament.equals("")) || departamentLink.getText().equals(departament));
		return assertDepartament && assertTitle;
	}
}
