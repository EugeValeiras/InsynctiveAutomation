package insynctive.tests;

import static junit.framework.Assert.assertTrue;
import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.pages.insynctive.LoginPage;
import insynctive.pages.insynctive.ResetPasswordPage;
import insynctive.utils.ConfigurationException;
import insynctive.utils.Debugger;
import insynctive.utils.InsynctiveProperties;
import insynctive.utils.MailManager;
import insynctive.utils.PersonData;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class CreatePersonTest extends TestSauceLabs implements SauceOnDemandSessionIdProvider,
		SauceOnDemandAuthenticationProvider {

	PersonData person;
	boolean isSaucelabs;
	
	@AfterClass(alwaysRun = true)
	public void teardown() throws ConfigurationException {
		if(InsynctiveProperties.IsSauceLabs()){
			this.driver.quit();
		}
	}

	@BeforeClass(alwaysRun = true)
	public void tearUp() throws Exception {
		properties = InsynctiveProperties.getAllAccountsProperties();
		this.sessionName = "Create Person";
		isSaucelabs = InsynctiveProperties.IsSauceLabs();
	}

	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] {
				"internet explorer", "11", "Windows 8.1" }
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void loginTest(String browser, String version, String os)
			throws Exception {
		startTest(browser, version, os);
		try{
			LoginPage loginPage = login();
		
			boolean result = loginPage.isLoggedIn();
			
			Debugger.log("loginTest => "+result, isSaucelabs);
			Assert.assertTrue(result);
		}catch (Exception ex){ 
			failTest("Login", isSaucelabs);
			assertTrue(false);
		}
	}

	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void createPersonTest(String browser, String version, String os)
			throws Exception {
		try{
			HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
			person = new PersonData(properties.getNewEmployeeName(), properties.getNewEmployeeLastName(), properties.getNewEmployeeEmail());
			
			homePage.createPerson(person);
			
			boolean result = homePage.checkIfPersonIsCreated(person);
			
			Debugger.log("createPerson => "+result, isSaucelabs);
			Assert.assertTrue(result);
		}catch (Exception ex){ 
			failTest("Create Person", isSaucelabs);
			assertTrue(false);
		}
	}
	
//	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="createPersonTest")
//	public void firstLogin(String browser, String version, String os)
//			throws Exception {
//		
//		String firstLoginToken = MailManager.getAuthLink(properties.getNewEmployeeEmail(), properties.getGmailPassword(), "Invitation to signup to Alpha 6 HR Portal");
//		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver, properties.getEnviroment(), firstLoginToken);
//		
//		resetPasswordPage.loadPage();
//		resetPasswordPage.changePassword(properties.getNewEmployeePassword());
//		
//		Assert.assertTrue(resetPasswordPage.checkIfEmployeePasswordWasChange());
//	}
}
