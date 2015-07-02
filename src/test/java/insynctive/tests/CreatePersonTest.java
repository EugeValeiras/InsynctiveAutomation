package insynctive.tests;

import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.pages.insynctive.LoginPage;
import insynctive.pages.insynctive.ResetPasswordPage;
import insynctive.utils.InsynctiveProperties;
import insynctive.utils.MailManager;
import insynctive.utils.PersonData;

import java.lang.reflect.Method;

import junit.framework.Assert;

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
	
//	@AfterClass(alwaysRun = true)
//	public void teardown() {
//		this.driver.quit();
//	}

	@BeforeClass(alwaysRun = true)
	public void tearUp() throws Exception {
		properties = InsynctiveProperties.getAllAccountsProperties();
		this.sessionName = "Create Person";
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
		
		LoginPage loginPage = login();
		Assert.assertTrue(loginPage.isLoggedIn());
	}

	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void createPersonTest(String browser, String version, String os)
			throws Exception {

		HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
		person = new PersonData(properties.getNewEmployeeName(), properties.getNewEmployeeLastName(), properties.getNewEmployeeEmail());
		
		homePage.createPerson(person);
		
		Assert.assertTrue(homePage.checkIfPersonIsCreated(person));
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="createPersonTest")
	public void firstLogin(String browser, String version, String os)
			throws Exception {
		
		String firstLoginToken = MailManager.getAuthLink(properties.getNewEmployeeEmail(), properties.getGmailPassword(), "Invitation to signup to Alpha 6 HR Portal");
		ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver, properties.getEnviroment(), firstLoginToken);
		
		resetPasswordPage.loadPage();
		resetPasswordPage.changePassword(properties.getNewEmployeePassword());
		
		Assert.assertTrue(resetPasswordPage.checkIfEmployeePasswordWasChange());
	}
}
