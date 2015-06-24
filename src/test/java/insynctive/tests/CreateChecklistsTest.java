package insynctive.tests;

import insynctive.checklist.Checklist;
import insynctive.checklist.ChecklistReader;
import insynctive.pages.insynctive.CheckListsPage;
import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.utils.Debugger;
import insynctive.utils.InsynctiveProperties;

import java.lang.reflect.Method;


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

	//	@AfterClass(alwaysRun = true)
//	public void teardown() {
//		this.driver.quit();
//	}

	@BeforeClass(alwaysRun = true)
	public void tearUp() throws Exception {
		properties = InsynctiveProperties.getProperties("test.properties");
		this.sessionName = "Create Checklist";
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
		
		ChecklistReader checkListReader = new ChecklistReader(driver);
		
		for(Checklist checkList : checkListReader.getAllCheckLists()){
			Debugger.log(checkList);
			checkListPage.createTemplate(checkList);
		}
		
	}
}
