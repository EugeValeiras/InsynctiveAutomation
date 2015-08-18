package insynctive.tests;

import insynctive.checklist.Checklist;
import insynctive.pages.insynctive.CheckListsPage;
import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.utils.ConfigurationException;
import insynctive.utils.Debugger;
import insynctive.utils.InsynctiveProperties;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class CreateChecklistsTest extends TestSauceLabs implements SauceOnDemandSessionIdProvider,
SauceOnDemandAuthenticationProvider {

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
		properties.addCheckLists(driver);
		this.sessionName = "Create Checklist";
		isSaucelabs = InsynctiveProperties.IsSauceLabs();
	}

	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] {
				"internet explorer", "11", "Windows 8.1" }
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void createChecklistTest(String browser, String version, String os)
			throws Exception {
		
		startTest(browser, version, os);
		
		login();
		new HomeForAgentsPage(driver, properties.getEnviroment()).waitPageIsLoad();
		
		CheckListsPage checkListPage = new CheckListsPage(driver, properties.getEnviroment());
		checkListPage.loadPage();
		
		for(Checklist checkList : properties.getCheckLists()){
			checkListPage.createTemplate(checkList);
		}
		
	}
}
