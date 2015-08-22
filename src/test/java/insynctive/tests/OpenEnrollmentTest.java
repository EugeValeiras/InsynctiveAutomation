package insynctive.tests;

import insynctive.pages.insynctive.OpenEnrollmentPage;
import insynctive.utils.InsynctiveProperties;
import insynctive.utils.TestEnvironment;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class OpenEnrollmentTest extends TestMachine {

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
		return new Object[][] { new Object[] { TestEnvironment.CHROME }
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void updatePersonalInformation(TestEnvironment testEnvironment) throws Throwable {
		
		startTest(testEnvironment);
		login("InsynctiveTestNG+206@gmail.com", "password");
		
		OpenEnrollmentPage openEnrollmentPage = new OpenEnrollmentPage(driver, properties.getEnviroment());
		openEnrollmentPage.waitPageIsLoad();

		Assert.assertTrue(openEnrollmentPage.startUpdateInfo());
	}
	
	
}
