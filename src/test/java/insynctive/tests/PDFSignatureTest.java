package insynctive.tests;

import insynctive.pages.insynctive.CheckListsPage;
import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.pages.insynctive.MyTasksPage;
import insynctive.utils.InsynctiveProperties;
import insynctive.utils.Sleeper;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;

public class PDFSignatureTest extends TestSauceLabs implements SauceOnDemandSessionIdProvider,
SauceOnDemandAuthenticationProvider{
	
	@BeforeClass(alwaysRun = true)
	public void tearUp() throws Exception {
		properties = InsynctiveProperties.getAllProperties(driver);
		this.sessionName = "PDF Signature";
	}

	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] {
				"firefox", "38", "Windows 8.1" }
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void PDF(String browser, String version, String os)
			throws Exception {

		startTest(browser, version, os);
		
		login();
		new HomeForAgentsPage(driver, properties.getEnviroment()).waitPageIsLoad();
		
				CheckListsPage checkListPage = new CheckListsPage(driver, properties.getEnviroment());
				checkListPage.loadPage();
				checkListPage.startChecklist("PDF", "Eugenio Valeiras");
				
				Sleeper.sleep(3000, driver);
				MyTasksPage myTasksPage = new MyTasksPage(driver, properties.getEnviroment());
				myTasksPage.loadPage();
				myTasksPage.openJustNowTask();
				
				Assert.assertTrue(myTasksPage.SingPDF());
		}
		
	}
	

