package insynctive.tests;

import static junit.framework.Assert.assertTrue;
import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.pages.insynctive.LoginPage;
import insynctive.pages.insynctive.PersonFilePage;
import insynctive.utils.Debugger;
import insynctive.utils.EmergencyContact;
import insynctive.utils.PersonData;
import insynctive.utils.data.TestEnvironment;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class PersonFileTest extends TestMachine {

	PersonData person;
	
	@Override
	@BeforeClass
	public void tearUp() throws Exception {
		super.tearUp();
		person = new PersonData(properties.getRunID());
		this.sessionName = "Person File Test";
	}
	
	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] { TestEnvironment.CHROME }
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void loginTest(TestEnvironment testEnvironment)
			throws Exception {
		startTest(testEnvironment);

		try{
			LoginPage loginPage = login();
			boolean result = loginPage.isLoggedIn();
			setResult(result, "Login Test");
			assertTrue(result);
		} catch(Exception ex){
			failTest("Login",  ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void openPersonFile(TestEnvironment testEnvironment) throws Exception {
		try{
			HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
			homePage.openPersonFile(person.getSearchEmail());
			
			boolean result = homePage.isPersonFileOpened();
			Debugger.log("openPersonFile => "+result, isSaucelabs);
			setResult(result, "Open Person File");
			assertTrue(result);
		} catch(Exception ex){
			failTest("Open Person File", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	

	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changePrimaryEmail(TestEnvironment testEnvironment) throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			personFilePage.changePrimaryEmail(person);
			
			boolean result = personFilePage.isChangePrimaryEmail(person.getEmail());
			Debugger.log("changePrimaryEmail => "+result, isSaucelabs);
			setResult(result, "Change Primary Email");
			assertTrue(result);
		} catch(Exception ex){
			failTest("Change Primary Email", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeMaritalStatus(TestEnvironment testEnvironment)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeMaritalStatus(person.getMaritalStatus());
			
			boolean result = personFilePage.isChangeMaritalStatus(person.getMaritalStatus());
			Debugger.log("changeMaritalStatus => "+result, isSaucelabs);
			setResult(result, "Change Marital Status");
			assertTrue(result);
		} catch (Exception ex){
			failTest("Change Marital Status", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeName(TestEnvironment testEnvironment)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeName(person.getName(), person.getLastName(), person.getMiddleName(), person.getMaidenName());
			
			boolean result = personFilePage.isChangeName(person.getName(), person.getLastName(), person.getMiddleName(), person.getMaidenName(), true);
			Debugger.log("changeName => "+result, isSaucelabs);
			setResult(result, "change name 1");
			assertTrue(result);
		} catch (Exception ex){
			failTest("change name 1", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeName2(TestEnvironment testEnvironment)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeNameIntoTitle(person.getName(), person.getLastName(), person.getMiddleName(), person.getMaidenName());
			
			boolean result = personFilePage.isChangeName(person.getName(), person.getLastName(), person.getMiddleName(), person.getMaidenName(), true);
			Debugger.log("changeName2 =>"+result, isSaucelabs);
			setResult(result, "change name 2");
			assertTrue(result);
		} catch(Exception ex){
			failTest("change name 2", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeGender(TestEnvironment testEnvironment)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeGender(person.getGender());
			
			boolean result = personFilePage.isChangeGender(person.getGender());
			Debugger.log("changeGender => "+result, isSaucelabs);
			setResult(result, "Change Gender");
			assertTrue(result);
		} catch (Exception ex){
			failTest("Change Gender", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeBirthDate(TestEnvironment testEnvironment)
			throws Exception {
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeBirthDate(person.getBirthDate());
			
			boolean result = personFilePage.isChangeBirthDate(person.getBirthDate());
			Debugger.log("changeBirthDate => "+result, isSaucelabs);
			setResult(result, "Change Birth Date");
			assertTrue(result);
		} catch(Exception ex){
			failTest("Change Birth Date", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addTitle(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.changeTitle(person.getTitleOfEmployee(), person.getDepartamentOfEmployee());
			
			boolean result = personFilePage.isChangeTitle(person.getTitleOfEmployee(), person.getDepartamentOfEmployee());
			Debugger.log("addTitle => "+result, isSaucelabs);
			setResult(result, "Add Title");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Add Title", ex, isSaucelabs);
			assertTrue(false);
		}
	}

	/**
	 * @Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void hasNotDependents(TestEnvironment testEnvironment) throws IOException, InterruptedException, ConfigurationException{
		setResult(false, "Add Has Not Dependents");
		PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
		
		personFilePage.addHasNotDependents();
		
		boolean result = personFilePage.isNotDependents();
		Debugger.log("hasNotDependents => "+result, isSaucelabs);
		setResult(result, "Add Has Not Dependents");
		assertTrue(result);
	} */

	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addPhoneNumber(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.addPhoneNumber(person.getPrimaryPhone());
			
			boolean result = personFilePage.isAddPhoneNumber(person.getPrimaryPhone());
			Debugger.log("addPhoneNumber =>"+result, isSaucelabs);
			setResult(result, "Add Phone Number");
			assertTrue(result);
		}catch (Exception ex){
			failTest("Add Phone Number", ex, isSaucelabs);assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addUSAddress(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.addUsAddress(person.getUSAddress());
			
			boolean result = personFilePage.isAddUSAddress(person.getUSAddress());
			Debugger.log("addUSAddress => "+result, isSaucelabs);
			setResult(result, "add US Address");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("add US Address", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="addUSAddress")
	public void removeUSAddress(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			personFilePage.removeUsAddress(person.getUSAddress());

			boolean result = personFilePage.isRemoveUsAddress(person.getUSAddress());
			Debugger.log("removeUSAddress => "+result, isSaucelabs);
			setResult(result, "remove US Address");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("remove US Address", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	/** @Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void updateUSAddress(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			personFilePage.updateUsAddress(person.getUSAddress());

			boolean result = personFilePage.isUpdateUsAddress(person.getUSAddress());
			Debugger.log("removeUSAddress => "+result, isSaucelabs);
			setResult(result, "Update USAddress");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Update USAddress", ex, isSaucelabs);
			assertTrue(false);
		}
	} */
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void asssignTask(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.assignTask();
			
			boolean result = personFilePage.isTaskAssigned();
			Debugger.log("asssignTask => "+result, isSaucelabs);
			setResult(result, "Assign Task");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Assign Task", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void startChecklist(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.assignChecklist();
			
			boolean result = personFilePage.isChecklistAssigned();
			Debugger.log("asssignTask => "+result, isSaucelabs);
			setResult(result, "Start Checklist");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Start Checklist", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addSocialSecurityNumber(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			
			personFilePage.addSocialSecurityNumber(person.getSsn());
			
			boolean result = personFilePage.isSocialSecurityNumberAdded(person.getSsn());
			Debugger.log("Add Social Security Number => "+result, isSaucelabs);
			setResult(result, "Add Social Security Number");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Add Social Security Number", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void addEmergencyContact(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			EmergencyContact emg = person.getEmergencyContact();
			personFilePage.addEmergencyContact(emg.getName(), emg.getRelationship(), emg.getPhone(), emg.getEmail());
			
			boolean result = personFilePage.isEmergencyContactAdded(emg);
			Debugger.log("Add Emergency Contact => "+result, isSaucelabs);
			setResult(result, "Add Emergency Contact");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Add Emergency Contact", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void changeEmergencyContact(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			EmergencyContact emg = person.getEmergencyContact();
			personFilePage.editEmergencyContact(emg.getName()+"Test", emg.getRelationship(), emg.getPhone(), emg.getEmail());
			
			String name = emg.getName();
			emg.setName(emg.getName()+"Test");
			
			boolean result = personFilePage.isEmergencyContactAdded(emg);
			emg.setName(name); //return the default name
			
			Debugger.log("Change Emergency Contact => "+result, isSaucelabs);
			setResult(result, "Change Emergency Contact");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Change Emergency Contact", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="openPersonFile")
	public void removeEmergencyContact(TestEnvironment testEnvironment) throws Exception{
		try{
			PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
			int count = personFilePage.getNumberOfEmergencyContacts();
			personFilePage.removeLastEmergencyContact();
		
			boolean result = personFilePage.isEmergencyContactRemoved(count);
			Debugger.log("Remove Emergency Contact => "+result, isSaucelabs);
			setResult(result, "Add Emergency Contact");
			assertTrue(result);
		}catch (Exception ex){ 
			failTest("Remove Emergency Contact", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	

}




