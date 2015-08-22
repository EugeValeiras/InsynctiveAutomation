package insynctive.tests;

import insynctive.market.App;
import insynctive.pages.insynctive.MarketPage;
import insynctive.utils.ConfigurationException;
import insynctive.utils.InsynctiveProperties;
import insynctive.utils.TestEnvironment;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InstallAppTest extends TestMachine {

	boolean isSaucelabs;
	
	@AfterClass(alwaysRun = true)
	public void teardown() throws ConfigurationException {
		if(InsynctiveProperties.IsSauceLabs()){
			this.driver.quit();
		}
	}

	@BeforeClass(alwaysRun = true)
	public void tearUp() throws Exception {
		properties = InsynctiveProperties.getAllProperties(driver);
		this.sessionName = "Install Apps";
		isSaucelabs = InsynctiveProperties.IsSauceLabs();
	}
	
	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] { TestEnvironment.CHROME }
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void marketInstallTest(TestEnvironment testEnvironment)
			throws Exception {
		
		startTest(testEnvironment);
		
		MarketPage marketPage = new MarketPage(driver);
		
		for(App app : properties.getApps()){
			marketPage.installApp(app, 
					properties.getEnviroment(), 
					properties.getLoginUsername(), 
					properties.getLoginPassword());
			
			Assert.assertTrue(marketPage.isAppInstallSuccessfully());
		}
		
	}
}
