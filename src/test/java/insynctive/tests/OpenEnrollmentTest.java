package insynctive.tests;

import insynctive.checklist.Checklist;
import insynctive.pages.insynctive.CheckListsPage;
import insynctive.pages.insynctive.OpenEnrollmentPage;
import insynctive.utils.Debugger;
import insynctive.utils.InsynctiveProperties;

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
public class OpenEnrollmentTest extends TestSauceLabs implements SauceOnDemandSessionIdProvider,
SauceOnDemandAuthenticationProvider {

	//	@AfterClass(alwaysRun = true)
//	public void teardown() {
//		this.driver.quit();
//	}

	@BeforeClass(alwaysRun = true)
	public void tearUp() throws Exception {
		properties = InsynctiveProperties.getAllAccountsProperties();
		properties.addCheckLists(driver);
		this.sessionName = "Open Enrollment";
	}

	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] {
				"internet explorer", "11", "Windows 8.1" }
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void updatePersonalInformation(String browser, String version, String os)
			throws Exception {
		
		startTest(browser, version, os);
		login("InsynctiveTestNG+206@gmail.com", "password");
		
		OpenEnrollmentPage openEnrollmentPage = new OpenEnrollmentPage(driver, properties.getEnviroment());
		openEnrollmentPage.waitPageIsLoad();

		Assert.assertTrue(openEnrollmentPage.startUpdateInfo());
	}
	
	
}
