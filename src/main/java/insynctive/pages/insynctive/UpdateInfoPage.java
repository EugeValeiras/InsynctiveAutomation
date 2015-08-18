package insynctive.pages.insynctive;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import insynctive.pages.Page;
import insynctive.pages.PageInterface;
import insynctive.utils.ConfigurationException;
import insynctive.utils.InsynctiveProperties;
import insynctive.utils.Sleeper;

public class UpdateInfoPage extends Page implements PageInterface{

	String enviroment;
	InsynctiveProperties properties;
	
	@FindBy(css = "#popupCustom_CIFD-1 > iframe")
	WebElement personalInfoFrame;
	@FindBy(id = "lblTitle")
	WebElement labelTitle;
	@FindBy(css = "#ucPI_pnlWizard_footer1_btnTab1Next_CD > span")
	WebElement nextBtnTo;
	
	//PERSONAL INFO
	@FindBy(id = "ucPI_pnlWizard_tab1_tSSN_I")
	WebElement ssnField;
	@FindBy(id = "ucPI_pnlWizard_tab1_tGender_I")
	WebElement gender;
	@FindBy(id = "ucPI_pnlWizard_tab1_tMaritalStatus_I")
	WebElement maritalStatus;
	@FindBy(id = "ucPI_pnlWizard_tab1_tBirthDate_I")
	WebElement dateOfBirth;
	
	//ADDRESS
	@FindBy(id = "ucPI_pnlWizard_tab1_txtStep2")
	WebElement textStep2;
	@FindBy(css = "#lnkSort_CD > span")
	WebElement addAddressBtn;
	@FindBy(id = "popupAddAddress_PWH-1T")
	WebElement addAdressPopUp;
	@FindBy(id = "txtStreetAddress_I")
	WebElement streetAddress;
	@FindBy(id = "txtApt_I")
	WebElement apt;
	@FindBy(id = "txtCity_I")
	WebElement city;
	@FindBy(id = "txtState_I")
	WebElement state;
	@FindBy(id = "txtZIPCode_I")
	WebElement zipCode;
	@FindBy(id = "cmbCounty_I")
	WebElement country;
	@FindBy(id = "txtDescription_I")
	WebElement description;
	@FindBy(css = "#btnOk_CD > span")
	WebElement saveBtn;
	
	//DEPENDENTS
	@FindBy(id = "ucPI_pnlWizard_tab1_txtStep3")
	WebElement textStep3;
	@FindBy(id = "ucPI_pnlWizard_tab1_chkNoFamilyMember_S_D")
	WebElement doNotHaveDependents;
	@FindBy(id = "ucPI_pnlWizard_tab1_gridFamilyMembers_DXMainTable")
	WebElement tableOfrelationShip;
	
	//Emergency Contacts
	@FindBy(id = "ucPI_pnlWizard_tab1_txtInfo5")
	WebElement textStep4;
	
	//Last Step
	@FindBy(css = "#btnOK_CD > span")
	WebElement markAsComplete;
	@FindBy(css = "#cmdConfirm_CD > span")
	WebElement okBtn;
	@FindBy(css = "#lblConfirmMessage > b")
	WebElement congratulationMessage;
	
	
	public UpdateInfoPage(WebDriver driver, String enviroment) throws ConfigurationException {
		super(driver);
		this.enviroment = enviroment;
		this.PAGE_URL = "NO URL";
		this.PAGE_TITLE = "";
		PageFactory.initElements(driver, this);
		properties = InsynctiveProperties.getAllAccountsProperties();
	}
	
	public void startUpdateInfo() throws IOException, InterruptedException {
		swichToFirstFrame(driver);
		swichToIframe(personalInfoFrame);
		
		fillStep1();
		
		waitUntilSecondStepIsReady();
		fillStep2();
		
		waitUntilThirdStepIsReady();
		fillStep3();
		
		waitUnitlFourthStepIsReady();
		fillStep4();

		completeTask();

		waitUntilIsLoaded(okBtn);
		okBtn.click();
	}

	private void completeTask() throws IOException, InterruptedException {
		waitUntilIsLoaded(markAsComplete);
		markAsComplete.click();		
	}
	
	private void fillStep1() throws IOException, InterruptedException {
		setTextInField(ssnField, "111111111");
		setTextInField(dateOfBirth, "11112000");
		setTextInCombo(gender, "Female");
		setTextInCombo(maritalStatus, "Single");
		clickAButton(nextBtnTo);
	}	

	private void waitUntilSecondStepIsReady() throws IOException, InterruptedException {
		waitUntilIsLoaded(textStep2);
		waitUntilIsLoaded(nextBtnTo);
	}
	
	private void fillStep2() throws IOException, InterruptedException {
		addAddressBtn.click();
		waitUntilIsLoaded(addAdressPopUp);
		setTextInField(streetAddress, "Street");
		setTextInField(apt, "123");
		setTextInField(city, "Capital Federal");
		setTextInField(state, "NY");
		setTextInField(zipCode, "12345");
		setTextInCombo(country, "Orange");
		setTextInField(description, "Description");
		saveBtn.click();
		Sleeper.sleep(5, driver);
		clickAButton(nextBtnTo);
	}

	private void waitUntilThirdStepIsReady() throws IOException, InterruptedException {
		waitUntilIsLoaded(textStep3);
		waitUntilIsLoaded(doNotHaveDependents);
		waitUntilIsLoaded(nextBtnTo);
	}
	
	private void fillStep3() throws IOException, InterruptedException {
		if(!doNotHaveDependentsIsSelected()){
			doNotHaveDependents.click();
			Sleeper.sleep(5, driver);
			waitUntilnotVisibility(tableOfrelationShip);
		}
		
		clickAButton(nextBtnTo);
	}

	private void waitUnitlFourthStepIsReady() throws IOException, InterruptedException {
		waitUntilIsLoaded(textStep4);
		waitUntilIsLoaded(nextBtnTo);
	}

	private void fillStep4() throws IOException, InterruptedException {
		clickAButton(nextBtnTo);
	}
	
	private boolean doNotHaveDependentsIsSelected() {
		return doNotHaveDependents.getAttribute("class").equals("dxWeb_edtCheckBoxChecked dxICheckBox");
	}
	
	@Override
	public boolean isPageLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void waitPageIsLoad() throws IOException, InterruptedException {
		waitUntilIsLoaded(labelTitle);
	}
	
}
