package insynctive.tests;

import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.pages.insynctive.LoginPage;
import insynctive.pages.insynctive.PersonFilePage;
import insynctive.pages.insynctive.PersonFilePage.gender;
import insynctive.pages.insynctive.PersonFilePage.maritalStatus;
import insynctive.utils.ConfigurationException;
import insynctive.utils.InsynctiveProperties;

import java.io.IOException;
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
public class PersonFileTest extends TestSauceLabs implements SauceOnDemandSessionIdProvider,
SauceOnDemandAuthenticationProvider {

	@AfterClass(alwaysRun = true)
	public void teardown() throws ConfigurationException {
		if(InsynctiveProperties.IsSauceLabs()){
			this.driver.quit();
		}
		
	}

	@BeforeClass(alwaysRun = true)
	public void tearUp() throws Exception {
		properties = InsynctiveProperties.getAllAccountsProperties();
		this.sessionName = "Person File Test";
	}

	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] {
				"chrome", "43", "Windows 7" }
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
	public void changePrimaryEmail(String browser, String version, String os)
			throws Exception {
		HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
		homePage.openPersonFile("evaleiras@insynctive.com");

		PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
		
		personFilePage.changePrimaryEmail("eugenio.valeiras+1@gmail.com");
		
		Assert.assertTrue(personFilePage.isChangePrimaryEmail("eugenio.valeiras+1@gmail.com"));
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void changeMaritalStatus(String browser, String version, String os)
			throws Exception {
		HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
		homePage.openPersonFile("evaleiras@insynctive.com");
		
		PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
		
		personFilePage.changeMaritalStatus(maritalStatus.MARRIED);
		
		Assert.assertTrue(personFilePage.isChangeMaritalStatus(maritalStatus.MARRIED));
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void changeName(String browser, String version, String os)
			throws Exception {
		HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
		homePage.openPersonFile("evaleiras@insynctive.com");
		
		PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
		
		personFilePage.changeName("EugenioTest", "Valeiras", "Jesus Jose", "");
		
		Assert.assertTrue(personFilePage.isChangeName("Eugenioooo", "Valeiras", "Jesus Jose", ""));
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void changeGender(String browser, String version, String os)
			throws Exception {
		HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
		homePage.openPersonFile("evaleiras@insynctive.com");
		
		PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
		
		personFilePage.changeGender(gender.UNKNOWN);
		
		Assert.assertTrue(personFilePage.isChangeGender(gender.UNKNOWN));
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void changeBirthDate(String browser, String version, String os)
			throws Exception {
		HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
		homePage.openPersonFile("evaleiras@insynctive.com");
		
		PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
		
		personFilePage.changeBirthDate("03/15/1992");
	}
	
	@Test(dataProvider = "hardCodedBrowsers", dependsOnMethods="loginTest")
	public void addTitle(String browser, String version, String os) throws IOException, InterruptedException{
		HomeForAgentsPage homePage = new HomeForAgentsPage(driver, properties.getEnviroment());
		homePage.openPersonFile("evaleiras@insynctive.com");
	
		PersonFilePage personFilePage = new PersonFilePage(driver, properties.getEnviroment());
		
		personFilePage.changeTitle("QA", "Departament");
		
		Assert.assertTrue(personFilePage.isChangeTitle("QA", "Departament"));
	}
}
