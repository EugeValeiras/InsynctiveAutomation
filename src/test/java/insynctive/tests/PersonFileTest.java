package insynctive.tests;

import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.pages.insynctive.LoginPage;
import insynctive.pages.insynctive.PersonFilePage;
import insynctive.utils.ConfigurationException;
import insynctive.utils.Debugger;
import insynctive.utils.InsynctiveProperties;
import insynctive.utils.PersonData;

import java.io.IOException;
import java.lang.reflect.Method;

import static junit.framework.Assert.assertTrue;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class PersonFileTest extends TestSauceLabs implements SauceOnDemandSessionIdProvider,
SauceOnDemandAuthenticationProvider {

	PersonData person;
	boolean isSaucelabs;
	
	@AfterClass(alwaysRun = true)
	public void teardown() throws ConfigurationException{
		if(InsynctiveProperties.IsSauceLabs()){
			setFinalResult();
			this.driver.quit();
		}
	}

	@BeforeClass(alwaysRun = true)
	public void tearUp() throws Exception {
		properties = InsynctiveProperties.getAllAccountsProperties();
		this.sessionName = "Person File Test";
		person = new PersonData(properties.getRunID());
		isSaucelabs = InsynctiveProperties.IsSauceLabs();
	}

	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] {
//				"Internet Explorer", "11", "Windows 7" }
//				"Chrome", "44", "Windows 7" }
				"firefox", "39", "Windows 7" }
//				"iPad", "8.4", "OSX" }
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void loginTest(String browser, String version, String os)
			throws Exception {
		startTest(browser, version, os);

		try{
			LoginPage loginPage = login();
			boolean result = loginPage.isLoggedIn();
			setResult(result, "Login Test");
			assertTrue(loginPage.isLoggedIn());
		} catch(Exception ex){
			failTest("Login", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void openPersonFile(String browser, String version, String os) throws IOException, Throwable{
		try{
			HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
			homePage.openPersonFile(person.getSearchEmail());
			
			boolean result = homePage.isPersonFileOpened();
			Debugger.log("openPersonFile => "+result, isSaucelabs);
			setResult(result, "Open Person File");
			assertTrue(result);
		} catch(Exception ex){
			failTest("Open Person File", isSaucelabs);
			assertTrue(false);
		}
	}

	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changePrimaryEmail(String browser, String version, String os)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			personFilePage.changePrimaryEmail(person);
			
			boolean result = personFilePage.isChangePrimaryEmail(person.getEmail());
			Debugger.log("changePrimaryEmail => "+result, isSaucelabs);
			setResult(result, "Change Primary Email");
			assertTrue(result);
		} catch(Exception ex){
			failTest("Change Primary Email", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeMaritalStatus(String browser, String version, String os)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeMaritalStatus(person.getMaritalStatus());
			
			boolean result = personFilePage.isChangeMaritalStatus(person.getMaritalStatus());
			Debugger.log("changeMaritalStatus => "+result, isSaucelabs);
			setResult(result, "Change Marital Status");
			assertTrue(result);
		} catch (Exception ex){
			failTest("Change Marital Status", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeName(String browser, String version, String os)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeName(person.getName(), person.getLastName(), person.getMiddleName(), person.getMaidenName());
			
			boolean result = personFilePage.isChangeName(person.getName(), person.getLastName(), person.getMiddleName(), person.getMaidenName());
			Debugger.log("changeName => "+result, isSaucelabs);
			setResult(result, "change name 1");
			assertTrue(result);
		} catch (Exception ex){
			failTest("change name 1", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeName2(String browser, String version, String os)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeNameIntoTitle(person.getName(), person.getLastName(), person.getMiddleName(), person.getMaidenName());
			
			boolean result = personFilePage.isChangeName(person.getName(), person.getLastName(), person.getMiddleName(), person.getMaidenName());
			Debugger.log("changeName2 =>"+result, isSaucelabs);
			setResult(result, "change name 2");
			assertTrue(result);
		} catch(Exception ex){
			failTest("change name 2", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeGender(String browser, String version, String os)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeGender(person.getGender());
			
			boolean result = personFilePage.isChangeGender(person.getGender());
			Debugger.log("changeGender => "+result, isSaucelabs);
			setResult(result, "Change Gender");
			assertTrue(result);
		} catch (Exception ex){
			failTest("Change Gender", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeBirthDate(String browser, String version, String os)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeBirthDate(person.getBirthDate());
			
			boolean result = personFilePage.isChangeBirthDate(person.getBirthDate());
			Debugger.log("changeBirthDate => "+result, isSaucelabs);
			setResult(result, "Change Birth Date");
			assertTrue(result);
		} catch(Exception ex){
			failTest("Change Birth Date", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addTitle(String browser, String version, String os) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeTitle(person.getTitleOfEmployee(), person.getDepartamentOfEmployee());
			
			boolean result = personFilePage.isChangeTitle(person.getTitleOfEmployee(), person.getDepartamentOfEmployee());
			Debugger.log("addTitle => "+result, isSaucelabs);
			setResult(result, "Add Title");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Add Title", isSaucelabs);
			assertTrue(false);
		}
	}

	/**
	 * @Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void hasNotDependents(String browser, String version, String os) throws IOException, InterruptedException, ConfigurationException{
		setResult(false, "Add Has Not Dependents");
		PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
		
		personFilePage.addHasNotDependents();
		
		boolean result = personFilePage.isNotDependents();
		Debugger.log("hasNotDependents => "+result, isSaucelabs);
		setResult(result, "Add Has Not Dependents");
		assertTrue(result);
	} */

	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addPhoneNumber(String browser, String version, String os) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.addPhoneNumber(person.getPrimaryPhone());
			
			boolean result = personFilePage.isAddPhoneNumber(person.getPrimaryPhone());
			Debugger.log("addPhoneNumber =>"+result, isSaucelabs);
			setResult(result, "Add Phone Number");
			assertTrue(result);
		}catch (Exception ex){
			failTest("Add Phone Number", isSaucelabs);assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addUSAddress(String browser, String version, String os) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.addUsAddress(person.getUSAddress());
			
			boolean result = personFilePage.isAddUSAddress(person.getUSAddress());
			Debugger.log("addUSAddress => "+result, isSaucelabs);
			setResult(result, "add US Address");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("add US Address", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void removeUSAddress(String browser, String version, String os) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			personFilePage.removeUsAddress(person.getUSAddress());

			boolean result = personFilePage.isRemoveUsAddress(person.getUSAddress());
			Debugger.log("removeUSAddress => "+result, isSaucelabs);
			setResult(result, "remove US Address");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("remove US Address", isSaucelabs);
			assertTrue(false);
		}
	}
	
	/** @Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void updateUSAddress(String browser, String version, String os) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			personFilePage.updateUsAddress(person.getUSAddress());

			boolean result = personFilePage.isUpdateUsAddress(person.getUSAddress());
			Debugger.log("removeUSAddress => "+result, isSaucelabs);
			setResult(result, "Update USAddress");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Update USAddress", isSaucelabs);
			assertTrue(false);
		}
	} */
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void asssignTask(String browser, String version, String os) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.assignTask();
			
			boolean result = personFilePage.isTaskAssigned();
			Debugger.log("asssignTask => "+result, isSaucelabs);
			setResult(result, "Assign Task");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Assign Task", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void startChecklist(String browser, String version, String os) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.assignChecklist();
			
			boolean result = personFilePage.isChecklistAssigned();
			Debugger.log("asssignTask => "+result, isSaucelabs);
			setResult(result, "Start Checklist");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Start Checklist", isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addSocialSecurityNumber(String browser, String version, String os) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.addSocialSecurityNumber(person.getSsn());
			
			boolean result = personFilePage.isSocialSecurityNumberAdded(person.getSsn());
			Debugger.log("asssignTask => "+result, isSaucelabs);
			setResult(result, "Add Social Security Number");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Add Social Security Number", isSaucelabs);
			assertTrue(false);
		}
	}

}




