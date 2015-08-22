package insynctive.utils;

import insynctive.checklist.Checklist;
import insynctive.market.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class InsynctiveProperties {

	//INSTANCE
	private static InsynctiveProperties isInstance;
	
	// PROPERTIES PATH
	private String DEFAULT_ACCOUNTS_PROPERTIES = "accounts.properties";
	private String DEFAULT_RUNID_PROPERTIES = "run.properties";
	private String DEFAULT_INSTALL_APPS_JSON = "installapps.json";
	private String DEFAULT_CREATE_CHECKLISTS_JSON = "checklists_create.json";

	// RUN ID
	private String runID;

	// ACCOUNTS PROPERTIES
	private String enviroment;
	private String saucelabs;
	
	private String loginUsername;
	private String loginPassword;

	private String newEmployeeName;
	private String newEmployeeLastName;
	private String newEmployeeEmail;
	private String newEmployeePassword;
	private String newEmployeeTitle;
	private String newEmployeeDepartment;

	private String gmailPassword;

	// AppList
	private List<App> apps;

	// ChecklistList
	private List<Checklist> checklists;

	public InsynctiveProperties() throws ConfigurationException {
		if (isInstance == null){
			getRunIDAndAutoIncrement();
			isInstance = this;
		}
	}

	public static InsynctiveProperties getAllProperties(WebDriver driver)
			throws ConfigurationException {
		InsynctiveProperties insynctiveProp;
		insynctiveProp = (isInstance == null) ? new InsynctiveProperties() : isInstance;
			insynctiveProp = new InsynctiveProperties();
		try {
			insynctiveProp.addAccountsProperties();
			insynctiveProp.addApps();
			insynctiveProp.addCheckLists(driver);
		} catch (Exception ex) {
			throw new ConfigurationException("Check Config => "
					+ ex.getMessage());
		}
		return insynctiveProp;
	}

	public static InsynctiveProperties getAllAccountsProperties()
			throws ConfigurationException {
		InsynctiveProperties insynctiveProp;
		insynctiveProp = (isInstance == null) ? new InsynctiveProperties() : isInstance;
		insynctiveProp.addAccountsProperties();
		return insynctiveProp;
	}

	public static InsynctiveProperties getAllApps() throws Exception {
		InsynctiveProperties insynctiveProp;
		insynctiveProp = (isInstance == null) ? new InsynctiveProperties() : isInstance;
		insynctiveProp.addApps();
		return insynctiveProp;
	}

	public static InsynctiveProperties getAllChecklist(WebDriver driver)
			throws Exception {
		InsynctiveProperties insynctiveProp;
		insynctiveProp = (isInstance == null) ? new InsynctiveProperties() : isInstance;
		insynctiveProp.addCheckLists(driver);
		return insynctiveProp;
	}
	
	public List<App> addApps() throws Exception {
		AppsReader appReader = new AppsReader(DEFAULT_INSTALL_APPS_JSON);
		apps = appReader.getApps();
		return apps;
	}

	public List<Checklist> addCheckLists(WebDriver driver) throws Exception {
		ChecklistReader checklistReader = new ChecklistReader(driver,
				DEFAULT_CREATE_CHECKLISTS_JSON);
		checklists = checklistReader.getAllCheckLists();
		return checklists;
	}

	public void addAccountsProperties() throws ConfigurationException {
		try {
			// Open Properties Files
			Properties accountsProperties = new Properties();
			FileInputStream fileInput = new FileInputStream(
					DEFAULT_ACCOUNTS_PROPERTIES);
			accountsProperties.load(fileInput);

			// Get all properties
			enviroment = accountsProperties.getProperty("environment");

			loginUsername = accountsProperties.getProperty("loginUsername");
			loginPassword = accountsProperties.getProperty("loginPassword");

			newEmployeeName = accountsProperties.getProperty("newEmployeeName")+getRunID();
			newEmployeeLastName = accountsProperties.getProperty("newEmployeeLastName");
			String email = accountsProperties.getProperty("newEmployeeEmail");
			newEmployeeEmail = email.split("@")[0] + "+" + runID + "@"+ email.split("@")[1];
			newEmployeePassword = accountsProperties.getProperty("newEmployeePassword");
			setNewEmployeeDepartment(accountsProperties.getProperty("newEmployeeDepartment"));
			setNewEmployeeTitle(accountsProperties.getProperty("newEmployeeTitle"));
			
			
			gmailPassword = accountsProperties.getProperty("gmailPassword");

		} catch (Exception ex) {
			throw new ConfigurationException("Check config file => "+ ex.getMessage());
		}
	}

	public void getRunIDAndAutoIncrement() throws ConfigurationException {
		try {
			// Open Properties Files
			Properties runPropertie = new Properties();
			File fileID = new File(DEFAULT_RUNID_PROPERTIES);
			FileInputStream runIDFile = new FileInputStream(fileID);
			runPropertie.load(runIDFile);

			// Get runID
			runID = runPropertie.getProperty("runID");
			runPropertie.setProperty("runID",
					String.valueOf(Integer.parseInt(runID) + 1));

			// Save new Properties into File
			OutputStream output = new FileOutputStream(fileID);
			runPropertie.store(output, "LAST RUN");
		} catch (Exception ex) {
			throw new ConfigurationException("Check config file => "+ ex.getMessage());
		}
	}
	
	public static boolean IsSauceLabs() throws ConfigurationException {try {
		InsynctiveProperties insynctiveProp;
		insynctiveProp = (isInstance == null) ? new InsynctiveProperties() : isInstance;
		
		// Open Properties Files
		Properties accountsProperties = new Properties();
		FileInputStream fileInput = new FileInputStream(
				insynctiveProp.DEFAULT_ACCOUNTS_PROPERTIES);
		accountsProperties.load(fileInput);

		// Get all properties
		insynctiveProp.saucelabs = accountsProperties.getProperty("saucelabs");
		return Boolean.parseBoolean(insynctiveProp.saucelabs);

	} catch (Exception ex) {
		throw new ConfigurationException("Check config file => "+ ex.getMessage());
	}}

	public void addAppToList(App app) {
		apps.add(app);
	}

	public void addChecklistToList(Checklist checklist) {
		checklists.add(checklist);
	}

	/* Getters and Setters */
	public String getRunID() {
		return runID;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public String getNewEmployeeName() {
		return newEmployeeName;
	}

	public String getNewEmployeeLastName() {
		return newEmployeeLastName;
	}

	public String getNewEmployeeEmail() {
		return newEmployeeEmail;
	}

	public String getNewEmployeePassword() {
		return newEmployeePassword;
	}

	public String getGmailPassword() {
		return gmailPassword;
	}

	public List<App> getApps() {
		return apps;
	}

	public List<Checklist> getCheckLists() {
		return checklists;
	}

	public String getRunPropertiesStringFile() {
		return this.DEFAULT_RUNID_PROPERTIES;
	}

	public String getAccountsPropertiesStringFile() {
		return this.DEFAULT_ACCOUNTS_PROPERTIES;
	}

	public String getInstallAppsStringFile() {
		return this.DEFAULT_INSTALL_APPS_JSON;
	}

	public String getCreateCheckListsStringFile() {
		return this.DEFAULT_CREATE_CHECKLISTS_JSON;
	}

	public void setRunPropertiesStringFile(String runPropertiesStringFile) {
		this.DEFAULT_RUNID_PROPERTIES = runPropertiesStringFile;
	}

	public void setAccountPropertiesStringFile(
			String accountPropertiesStringFile) {
		this.DEFAULT_ACCOUNTS_PROPERTIES = accountPropertiesStringFile;
	}

	public void setInstallAppsStringFile(String installAppsStringFile) {
		this.DEFAULT_INSTALL_APPS_JSON = installAppsStringFile;
	}

	public void setCreateChecklistsStringFile(String crateChecklistsStringFile) {
		this.DEFAULT_CREATE_CHECKLISTS_JSON = crateChecklistsStringFile;
	}

	public String getNewEmployeeTitle() {
		return newEmployeeTitle;
	}

	public void setNewEmployeeTitle(String newEmployeeTitle) {
		this.newEmployeeTitle = newEmployeeTitle;
	}

	public String getNewEmployeeDepartment() {
		return newEmployeeDepartment;
	}

	public void setNewEmployeeDepartment(String newEmployeeDepartment) {
		this.newEmployeeDepartment = newEmployeeDepartment;
	}
}
