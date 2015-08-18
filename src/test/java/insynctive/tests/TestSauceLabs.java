package insynctive.tests;

import insynctive.pages.insynctive.LoginPage;
import insynctive.utils.ConfigurationException;
import insynctive.utils.Debugger;
import insynctive.utils.InsynctiveProperties;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;

public abstract class TestSauceLabs implements SauceOnDemandSessionIdProvider,
		SauceOnDemandAuthenticationProvider {

	public String sessionName = "Insynctive Session";
	public WebDriver driver;
	public InsynctiveProperties properties;
	JSONArray tags = new JSONArray();
	boolean generalStatus = true;
	
	
	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			"EugenioValeiras", "84dbbeca-6ecc-44b8-8f19-618944ea59e1");
	public ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	public ThreadLocal<String> sessionId = new ThreadLocal<String>();
	private String jobID;

	public WebDriver getWebDriver() {
		System.out.println("WebDriver" + webDriver.get());
		return webDriver.get();
	}

	public String getSessionId() {
		return sessionId.get();
	}

	@Override
	public SauceOnDemandAuthentication getAuthentication() {
		return authentication;
	}

	public WebDriver createDriver(String browser, String version, String os)
			throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
		if (version != null) {
			capabilities.setCapability(CapabilityType.VERSION, version);
		}
		capabilities.setCapability(CapabilityType.PLATFORM, os);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		capabilities.setCapability("name", sessionName);
		webDriver.set(new RemoteWebDriver(new URL("http://"
				+ authentication.getUsername() + ":"
				+ authentication.getAccessKey()
				+ "@ondemand.saucelabs.com:80/wd/hub"), capabilities));
		jobID = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
		sessionId.set(jobID);
		return webDriver.get();
	}

	public void startTest(String browser, String version, String os)
			throws MalformedURLException, ConfigurationException {
		if (InsynctiveProperties.IsSauceLabs()) {
			driver = createDriver(browser, version, os);
		} else {
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
			driver = new FirefoxDriver(firefoxProfile);
			driver.manage().window().maximize();
		}
	}

	public LoginPage login() throws Exception {
		LoginPage loginPage = new LoginPage(driver, properties.getEnviroment());
		loginPage.loadPage();
		loginPage.login(properties.getLoginUsername(),
				properties.getLoginPassword());
		return loginPage;
	}

	public LoginPage login(String username, String password) throws Exception {
		LoginPage loginPage = new LoginPage(driver, properties.getEnviroment());
		loginPage.loadPage();
		loginPage.login(username, password);
		return loginPage;
	}

	public void failTest(String testName,boolean isSaucelabs) throws Exception{
		Debugger.log(testName+ " => " + false);
		setResult(false, testName);
	}
	
	@SuppressWarnings("unchecked")
	public void setResult(boolean status, String nameOfTest) throws ConfigurationException, ParseException {

		if (status == false){
			generalStatus = status;
			tags.add(nameOfTest+"["+status+"]");
		}
		
		if(InsynctiveProperties.IsSauceLabs()){
			SauceREST client = new SauceREST("EugenioValeiras",
					"84dbbeca-6ecc-44b8-8f19-618944ea59e1");
			
			Map<String, Object> updates = new HashMap<String, Object>();
			updates.put("name", nameOfTest);
			updates.put("passed",status);			
			client.updateJobInfo(jobID, updates);
		}
	}

	public void setFinalResult() throws ConfigurationException {
		
		if(InsynctiveProperties.IsSauceLabs()){
			SauceREST client = new SauceREST("EugenioValeiras",
					"84dbbeca-6ecc-44b8-8f19-618944ea59e1");
			
			Map<String, Object> updates = new HashMap<String, Object>();
			updates.put("name", sessionName);
			updates.put("passed",generalStatus);			
			updates.put("tags", tags);
			client.updateJobInfo(jobID, updates);
		}
	}
}
