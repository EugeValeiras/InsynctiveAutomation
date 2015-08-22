package insynctive.pages.insynctive;

import insynctive.checklist.Checklist;
import insynctive.pages.Page;
import insynctive.pages.PageInterface;
import insynctive.utils.PersonData;
import insynctive.utils.PersonData.Gender;
import insynctive.utils.PersonData.MaritalStatus;
import insynctive.utils.Sleeper;
import insynctive.utils.Task;
import insynctive.utils.USAddress;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class 
PersonFilePage extends Page implements PageInterface {

	String enviroment;
	
	@FindBy(id = "phone-text")
	WebElement phoneNumberLink;
	@FindBy(id = "title-text")
	WebElement titleLink;
	@FindBy(id = "department-text")
	WebElement departamentLink;
	@FindBy(id = "loadingSpinner")
	WebElement loadingSpinner;
	
	//Links
	
	@FindBy(css = "#statusesListHeader > li:nth-child(1)")
	WebElement personalLink;
	@FindBy(xpath = "//*[@id=\"full-name-field\"]/div[1]/span")
	WebElement fullNameLink;
	@FindBy(id = "name")
	WebElement nameLink;
	@FindBy(id = "date-picker")
	WebElement birthDateLink;
	@FindBy(className = "gender-picker")
	WebElement genderLink;
	@FindBy(className = "marital-picker")
	WebElement maritalLink;
	@FindBy(className = "email-primary")
	WebElement primaryEmailLink;
	@FindBy(id = "addPhoneBtn")
	WebElement addPhoneNumberLink;
	@FindBy(id = "add-address")
	WebElement addAddressLink;
	@FindBy(id = "addBtn")
	WebElement addSocialSecurityNumber;
	
	
	//iFrames
	@FindBy(id = "tabFrame")
	WebElement tabiFrame;
	@FindBy(className = "header-name-popover")
	WebElement editNameintoTitleiFrame;
	@FindBy(className = "full-name-popover")
	WebElement editNameiFrame;
	@FindBy(className = "date-picker-popover")
	WebElement birthDateiFrame;
	@FindBy(className = "emails-edit-popover")
	WebElement primaryEmailiFrame;
	@FindBy(className = "phones-add-popover")
	WebElement editPhoneNumberiFrame;
	@FindBy(className = "ssn-popover")
	WebElement socialSecurtyiFrame;
	@FindBy(className = "address-add-popover")
	WebElement usAddressiFrame;
	@FindBy(className = "address-edit-popover")
	WebElement usAddressEditiFrame;
	
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
	@FindBy(css = ".error-msg")
	WebElement errorMessageEmail;
	
	//Has Not Dependents
	@FindBy(css = "#dependents-grid > span.no-dependents")
	WebElement hasNotDependentsLink;
	
	@FindBy(css = "#dependents-grid > span.no-dependents")
	WebElement hasNotDependentsLabel;
	
	//Add Phone Number
	@FindBy(id = "addBtn")
	WebElement addPhoneNumberLink2;
	@FindBy(id = "inputAddPhone")
	WebElement addPhoneNumberInput;
	@FindBy(id = "btnAddPhone")
	WebElement buttonAddPhone;
	@FindBy(id = "saveBtn")
	WebElement saveChangeAddPhoneNumber;
	@FindBy(id = "mobile-phone")
	WebElement mobilePhoneNumber;
	@FindBy(className = "delete-phone-btn")
	WebElement deletePhoneNumber;

	//Add Address
	@FindBy(id = "address1")
	WebElement streetAddressField;
	@FindBy(id = "address2")
	WebElement streetAddressOptionalField;
	@FindBy(id = "city")
	WebElement cityField;
	@FindBy(css = "#state_dd_chosen > a")
	WebElement stateCombo;
	@FindBy(id = "zip-code")
	WebElement zipCodeField;
	@FindBy(css = "#county_dd_chosen > a")
	WebElement countyCombo;
	@FindBy(id = "is-mailing")
	WebElement sameAsHomeAddressCheck;
	@FindBy(id = "save-address")
	WebElement saveAddressButton;
	@FindBy(css = ".address-name > span:nth-child(1)")
	WebElement addressSaved;

	//Tasks
	@FindBy(css = "#statusesListHeader > li:nth-child(2)")
	WebElement tasksTab;
	@FindBy(css = "#btnAssignTask > span")
	WebElement assignTaskButton;
	@FindBy(id = "lName")
	WebElement taskName;
	@FindBy(id = "txtTaskInstructions")
	WebElement taskInstuctions;
	@FindBy(className = "froala-element")
	WebElement taskAdditionalInstructions;
	@FindBy(id = "btnAssignTask")
	WebElement btnAssignTask;
	@FindBy(id = "RunningChecklistsTab")
	WebElement runningChecklist;
	@FindBy(id = "btnStartChecklist")
	WebElement startChecklistButton;
	@FindBy(css = "#contentHeight > iframe")
	WebElement startChecklistiFrame;
	@FindBy(css = "#ddChecklist_chosen > a")
	WebElement checkListsCombo;
	@FindBy(id = "btnStartChecklist")
	WebElement assignChecklistButton;
	@FindBy(css = "div.row:nth-child(3) > div:nth-child(1) > span:nth-child(1)")
	WebElement firstChecklist;
	@FindBy(id = "delete-addresses")
	WebElement removeAddressButton;
	@FindBy(css = "#pendingTasksList > div.task-list > div:nth-child(1) > span > div")
	WebElement firstTaskLink;
	@FindBy(id = "froalaEditor")
	WebElement AdditionalInstructioniFrame;
	@FindBy(id = "ssnBtn")
	WebElement ssnInsert;
	@FindBy(id = "ssn-input")
	WebElement ssnTextField;
	@FindBy(id = "saveSSN")
	WebElement saveSsn;
	
	public PersonFilePage(WebDriver driver, String enviroment) {
		super(driver);
		this.enviroment = enviroment;
		this.PAGE_URL = "NO URL";
		this.PAGE_TITLE = "NO TITLE";
		PageFactory.initElements(driver, this);
	}

	/*Complete Test Methods*/
	public void changeName(String nameIn, String lastNameIn, String middleNameIn, String maidenNameIn) throws Throwable {
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

	public void changeNameIntoTitle(String nameIn, String lastNameIn, String middleNameIn, String maidenNameIn) throws Throwable {
		waitPageIsLoad();
		swichToFirstFrame(driver);
		nameLink.click();
		swichToIframe(editNameintoTitleiFrame);
		setTextInField(firstName, nameIn);
		setTextInField(lastName, lastNameIn);
		setTextInField(middleName, middleNameIn);
		setTextInField(maidenName, maidenNameIn);
		saveChangeFullName.click();
		waitUntilnotVisibility(saveChangeFullName);
	}
	
	public void changeGender(Gender gender) throws Throwable {
		waitPageIsLoad();
		genderLink.click();
		driver.findElement(By.xpath("//div[@class='enum-item' and contains(., '"+gender.name+"')]")).click();
	}

	public void changeBirthDate(String birthDate) throws Throwable {
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
	
	public void changeMaritalStatus(MaritalStatus status) throws Throwable {
		waitPageIsLoad();
		maritalLink.click();
		driver.findElement(By.xpath("//div[@class='enum-item' and contains(., '"+status.status+"')]")).click();
	}

	public void changePrimaryEmail(PersonData person) throws Throwable {
		waitPageIsLoad();
		Sleeper.sleep(1000, driver);
		primaryEmailLink.click();
		waitUntilnotVisibility(loadingSpinner);
		swichToIframe(primaryEmailiFrame);
		waitUntilIsLoaded(primaryEmailLink);
		primaryEmailLink.click();
		waitUntilIsLoaded(primaryEmailField);
		setTextInField(primaryEmailField, person.getEmail());
		waitUntilIsLoaded(saveChangePrimaryEmail);
		saveChangePrimaryEmail.click();
		waitUntilIsLoaded(savePrimaryEmailChange);
		savePrimaryEmailChange.click();
		waitUntilnotVisibility(saveChangePrimaryEmail);
	}

	public void changeTitle(String title, String departament) throws Throwable {
		waitPageIsLoad();
		swichToFirstFrame(driver);
		nameLink.click();
		swichToIframe(editNameintoTitleiFrame);
		setTextInField(titleField, title);
		setTextInField(departamentField, departament);
		saveChangeTitle.click();
		waitUntilnotVisibility(saveChangeTitle);
	}

	public void addHasNotDependents() throws Throwable {
		waitPageIsLoad();
		hasNotDependentsLink.click();
	}

	public void addPhoneNumber(String phoneNumber) throws Throwable {
		waitPageIsLoad();
		addPhoneNumberLink.click();
		swichToIframe(editPhoneNumberiFrame);
		waitUntilIsLoaded(addPhoneNumberLink2);
		addPhoneNumberLink2.click();
		waitUntilIsLoaded(addPhoneNumberInput);
		waitUntilIsLoaded(buttonAddPhone);
		setTextInField(addPhoneNumberInput, phoneNumber);
		buttonAddPhone.click();
		waitUntilIsLoaded(deletePhoneNumber);
		saveChangeAddPhoneNumber.click();
	}

	public void addUsAddress(USAddress usAddress) throws Throwable {
		waitPageIsLoad();
		addAddressLink.click();
		swichToIframe(usAddressiFrame);
		waitUsAddresFrameIsLoad();
		completeAddressForm(usAddress);
		saveAddressButton.click();
		waitPageIsLoad();
		waitUntilIsLoaded(addressSaved);
		System.out.println(addressSaved.getText());
	}

	public void removeUsAddress(USAddress usAddress) throws Throwable {
		waitPageIsLoad();
		WebElement address = searchAddress(usAddress);
		address.click();
		swichToIframe(usAddressEditiFrame);
		waitUsAddresFrameIsLoad();
		waitUntilIsLoaded(removeAddressButton);
		removeAddressButton.click();
		waitPageIsLoad();
	}

	public void assignTask() throws Throwable {
		List<Task> tasks = Task.getTasks();
		waitPageIsLoad();
		for(Task task : tasks){
			swichToFirstFrame(driver);
			tasksTab.click();
			swichToIframe(tabiFrame);
			assignTaskButton.click();
			swichToFirstFrame(driver);
			waitUntilIsLoaded(taskName);
			taskName.click();
			taskName.sendKeys(task.getDetail());
//			taskName.sendKeys(Keys.TAB);
			waitUntilIsLoaded(taskInstuctions);
			setTextInField(taskInstuctions, task.getBasicTaskInstruction());
			swichToIframe(AdditionalInstructioniFrame);
			waitUntilIsLoaded(taskAdditionalInstructions);
			setTextInField(taskAdditionalInstructions,task.getAdditionalInstruction());
			swichToFirstFrame(driver);
			btnAssignTask.click();
		}
	}

	public void assignChecklist() throws Throwable {
		waitPageIsLoad();
		swichToFirstFrame(driver);
		tasksTab.click();
		swichToIframe(tabiFrame);
		waitUntilIsLoaded(runningChecklist);
		runningChecklist.click();
		waitUntilIsLoaded(startChecklistButton);
		startChecklistButton.click();
		swichToIframe(startChecklistiFrame);
		waitUntilIsLoaded(checkListsCombo);
		setTextInCombo(checkListsCombo, Checklist.getCheckListToAssign().getName());
		assignChecklistButton.click();
		Sleeper.sleep(4000, driver);
	}
	
	public void addSocialSecurityNumber(String ssnNumber) throws Throwable {
		waitPageIsLoad();
		addSocialSecurityNumber.click();
		swichToIframe(socialSecurtyiFrame);
		ssnInsert.click();
		setTextInField(ssnTextField, ssnNumber);
		saveSsn.click();
	}
	
	/* Check if is complete Methods*/
	public boolean isChangePrimaryEmail(String email) throws Throwable {
		Sleeper.sleep(12000, driver);
		waitPageIsLoad();
		return primaryEmailLink.getText().equals(email);
	}

	public boolean isChangeName(String name, String lastName, String middlename, String maidenName, boolean wait) throws Throwable {
		if(wait) Sleeper.sleep(18000, driver);
		waitPageIsLoad();
		
		String assertFullName = (name!=null && middlename != null) ? name+" "+middlename : name;
		assertFullName += (maidenName!=null) ? " ("+maidenName+") " : " ";
		assertFullName += lastName;
		waitUntilIsLoaded(fullNameLink);
		boolean fullNameAssert = fullNameLink.getText().equals(assertFullName);
	
		swichToFirstFrame(driver);
		String assertTitleName = name+" "+lastName;
		boolean titleNameAssert = nameLink.getText().equals(assertTitleName);
		 
		return fullNameAssert && titleNameAssert;
	}

	public boolean isChangeGender(Gender genderType) throws Throwable {
		waitPageIsLoad();
		return genderLink.getText().equals(genderType.name);
	}

	public boolean isChangeMaritalStatus(MaritalStatus maritalStat) throws Throwable {
		Sleeper.sleep(4000, driver);
		waitPageIsLoad();
		return maritalLink.getText().equals(maritalStat.status);
	}

	public boolean isChangeTitle(String title, String departament) throws Throwable {
		Sleeper.sleep(5000, driver);
		waitPageIsLoad();
		swichToFirstFrame(driver);
		boolean assertTitle =((titleLink == null && departament.equals("")) || titleLink.getText().equals(title));
		boolean assertDepartament =((departamentLink == null && departament.equals("")) || departamentLink.getText().equals(departament));
		return assertDepartament && assertTitle;
	}

	public boolean isNotDependents() {
		return hasNotDependentsLabel.getText().equals("Has no dependents");
	}

	public boolean isAddPhoneNumber(String phoneNumber) throws Throwable {
		Sleeper.sleep(10000, driver);
		waitPageIsLoad();
		waitUntilnotVisibility(loadingSpinner);
		String assertNumber = "("+phoneNumber.substring(0, 3)+") "+phoneNumber.substring(3, 6)+"-"+phoneNumber.substring(6, 10);
		return mobilePhoneNumber.getText().equals(assertNumber);
	}

	public boolean isAddUSAddress(USAddress usAddress) throws Throwable {
		waitPageIsLoad();
		return addressSaved.getText().contains(usAddress.getStreet());
	}
	
	public boolean isChangeBirthDate(String birthDate) throws Throwable {
		waitPageIsLoad();
		String[] birthDateSplit = birthDate.split("/");
		String birtDateAssert = birthDateSplit[1]+"-"+getMonth(birthDateSplit[0])+"-"+birthDateSplit[2];
		return birthDateLink.getText().equals(birtDateAssert);
	}

	public boolean isTaskAssigned() throws Throwable {
		Sleeper.sleep(5000,driver);
		waitTaskTabIsLoad();
		List<Task> tasks = Task.getTasks();
		boolean result = firstTaskLink.getText().equals(tasks.get(0).getDetail());
		swichToFirstFrame(driver);
		personalLink.click();
		return result;
	}
	
	public boolean isChecklistAssigned() throws Throwable {
		waitUntilnotVisibility(loadingSpinner);
		swichToFirstFrame(driver);
		tasksTab.click();
		swichToIframe(tabiFrame);
		runningChecklist.click();
		waitUntilIsLoaded(firstChecklist);
		return firstChecklist.getText().equals(Checklist.getCheckListToAssign().getName());
	}

	public boolean isRemoveUsAddress(USAddress usAddress){
		return searchAddress(usAddress) == null;
	}
	
	public boolean isSocialSecurityNumberAdded(String ssnNumber) throws Throwable {
		Sleeper.sleep(5000,driver);
		waitPageIsLoad();
		return addSocialSecurityNumber.getText().substring(7, 11).equals(ssnNumber.substring(5, 9));
	}
	
	/*Waits Methods*/
	@Override
	public void waitPageIsLoad() throws Throwable {
		swichToFirstFrame(driver);
		waitUntilnotVisibility(loadingSpinner);
		waitUntilIsLoaded(nameLink);
		swichToIframe(tabiFrame);
		waitUntilIsLoaded(genderLink);
		waitUntilIsLoaded(maritalLink);
		waitUntilIsLoaded(primaryEmailLink);
		waitUntilIsLoaded(fullNameLink);
		Sleeper.sleep(2000, driver);
	}
	
	public void waitTaskTabIsLoad() throws Throwable {
		swichToFirstFrame(driver);
		waitUntilnotVisibility(loadingSpinner);
		waitUntilIsLoaded(nameLink);
		swichToIframe(tabiFrame);
		waitUntilIsLoaded(firstTaskLink);
		Sleeper.sleep(2000, driver);
	}
	
	/* Private Methods */
	private void waitUsAddresFrameIsLoad() throws Throwable {
		waitUntilIsLoaded(streetAddressField);
		waitUntilIsLoaded(streetAddressOptionalField);
		waitUntilIsLoaded(cityField);
		waitUntilIsLoaded(stateCombo);
		waitUntilIsLoaded(zipCodeField);
		waitUntilIsLoaded(countyCombo);
	}
	
	private WebElement searchAddress(USAddress usAddress) {
		List<WebElement> allAddress = driver.findElements(By.className("address-name"));
		WebElement address;
		for (int index = 0; index < allAddress.size(); index++) {
			address = allAddress.get(index);
			if(isTheSameAddress(address, usAddress)){
				return address;
			}
		}
		return null;
	}
	
	private void completeAddressForm(USAddress usAddress) throws Throwable {
		setTextInField(streetAddressField, usAddress.getStreet());
		setTextInField(streetAddressOptionalField, usAddress.getSecondStreet());
		setTextInField(cityField, usAddress.getCity());
		setTextInCombo(stateCombo, usAddress.getState());
		setTextInField(zipCodeField, usAddress.getZipCode());
		setTextInCombo(countyCombo, usAddress.getCounty());
		if(usAddress.isSameAsHome()){
			//TODO ADD BOX CKICK
		}
	}

	private String getMonth(String number) {
		switch (Integer.parseInt(number)) {
		case 1:
			return "Jan";
		case 2:
			return "Feb";
		case 3:
			return "Mar";
		case 4:
			return "Apr";
		case 5:
			return "May";
		case 6:
			return "Jun";
		case 7:
			return "Jul";
		case 8:
			return "Aug";
		case 9:
			return "Sep";
		case 10:
			return "Oct";
		case 11:
			return "Nov";
		case 12:
			return "Dec";
			
		default:
			return "ERROR";
		}
	}

	private boolean isTheSameAddress(WebElement address, USAddress usAddress){
		return address.getText().contains(usAddress.getCity()) &&
				address.getText().contains(usAddress.getCounty()) &&
				address.getText().contains(usAddress.getStreet()) &&
				address.getText().contains(usAddress.getZipCode());
	}

	//TODO METHODS
	@Override
	public boolean isPageLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	public void updateUsAddress(USAddress usAddress) throws Throwable {
		// TODO Auto-generated method stub
	}
	
	public boolean isUpdateUsAddress(USAddress usAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isThisPerson(PersonData personData) throws Throwable {
		boolean changeTitle = isChangeTitle(personData.getTitleOfEmployee(), personData.getDepartamentOfEmployee());
		boolean changeFullName = isChangeName(personData.getName(), personData.getLastName(), personData.getMiddleName(), personData.getMaidenName(), false); 
		
		return changeTitle && changeFullName;
	}
}
