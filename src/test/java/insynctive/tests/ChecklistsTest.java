package insynctive.tests;

import static junit.framework.Assert.assertTrue;
import insynctive.pages.insynctive.CheckListsPage;
import insynctive.pages.insynctive.HomeForAgentsPage;
import insynctive.pages.insynctive.LoginPage;
import insynctive.pages.insynctive.PersonFilePage;
import insynctive.pages.insynctive.exception.ConfigurationException;
import insynctive.utils.Checklist;
import insynctive.utils.Debugger;
import insynctive.utils.PersonData;
import insynctive.utils.WhenStart;
import insynctive.utils.data.Employee;
import insynctive.utils.data.TestEnvironment;
import insynctive.utils.process.AssignTask;
import insynctive.utils.process.I9;
import insynctive.utils.process.W4;
import insynctive.utils.reader.InsynctivePropertiesReader;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucelabs.testng.SauceOnDemandTestListener;

@Listeners({ SauceOnDemandTestListener.class })
public class ChecklistsTest extends TestMachine {

	PersonData person;

	@Override
	@BeforeClass
	public void tearUp() throws Exception {
		super.tearUp();
		this.sessionName = "Checklist Sanity Tests";
	}
	
	@DataProvider(name = "hardCodedBrowsers", parallel = false)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] { new Object[] { TestEnvironment.FIREFOX }
//				{TestEnvironment.CHROME}, 
//				{TestEnvironment.IE}
		};
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void startCheckListI9(TestEnvironment testEnvironment) throws Throwable {
		startTest(testEnvironment);
		
		Checklist checklist = new Checklist("I9 Template");
		Employee employee = Employee.W2_EMPLOYEE;
		checklist.addProcess(new I9(WhenStart.ASAP, employee, driver));
		
		try{
			HomeForAgentsPage homeForAgent = createAndAssignTask(checklist, employee);
			
			boolean result = homeForAgent.isTaskAssign(checklist);
			homeForAgent.openTask(checklist);
			checklist.getProcess().get(0).completeSteps();
			
			setResult(result, "Start I9 Checklist");
			Debugger.log("asssignI9Task => "+result, isSaucelabs);
			assertTrue(result);
		} catch (Exception ex){
			failTest("Start I9 Checklist", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
	@Test(dataProvider = "hardCodedBrowsers")
	public void startCheckListW4(TestEnvironment testEnvironment) throws Throwable {
		startTest(testEnvironment);
		
		Checklist checklist = new Checklist("W4 Template");
		checklist.addProcess(new W4(WhenStart.ASAP, false, driver));
		Employee employee = Employee.W2_EMPLOYEE;
		
		try{
			HomeForAgentsPage homeForAgent = 
					createAndAssignTask(checklist, employee);
			
			boolean result = homeForAgent.isTaskAssign(checklist);

			setResult(result, "Start W4 Checklist");
			Debugger.log("asssignW4Task => "+result, isSaucelabs);
			assertTrue(result);
		} catch (Exception ex){
			failTest("Start W4 Checklist", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	
//	@Test(dataProvider = "hardCodedBrowsers")
	public void startCheckListPDF(TestEnvironment testEnvironment) throws Throwable {
		startTest(testEnvironment);
		
		Checklist checklist = new Checklist("W4 Template");
		checklist.addProcess(new W4(WhenStart.ASAP, false, driver));
		Employee employee = Employee.W2_EMPLOYEE;
		
		try{
			HomeForAgentsPage homeForAgent = 
					createAndAssignTask(checklist, employee);
			
			boolean result = homeForAgent.isTaskAssign(checklist);

			setResult(result, "Start W4 Checklist");
			Debugger.log("asssignW4Task => "+result, isSaucelabs);
			assertTrue(result);
		} catch (Exception ex){
			failTest("Start W4 Checklist", ex, isSaucelabs);
			assertTrue(false);
		}
	}
	 
	@Test(dataProvider = "hardCodedBrowsers")
	public void startChecklistAssignTask(TestEnvironment testEnvironment) throws Throwable {
		startTest(testEnvironment);
		
		Checklist checklist = new Checklist("Assignt Task Template");
		checklist.addProcess(new AssignTask(WhenStart.ASAP, Employee.AGENT_OFFICER, driver));
		Employee employee = Employee.W2_EMPLOYEE;
		
		try{
			HomeForAgentsPage homeForAgent = 
					createAndAssignTask(checklist, employee);
			
			boolean result = homeForAgent.isTaskAssign(checklist);

			setResult(result, "Start Assign Task Checklist");
			Debugger.log("asssignW4Task => "+result, isSaucelabs);
			assertTrue(result);
		} catch (Exception ex){
			failTest("Start Assign Task Checklist", ex, isSaucelabs);
			assertTrue(false);
		}
	}

	//PRIVATE
	private HomeForAgentsPage createAndAssignTask(Checklist checklist,
			Employee employee) throws Exception, Throwable {
		login();
		HomeForAgentsPage homeForAgent = new HomeForAgentsPage(driver, properties.getEnviroment());
		CheckListsPage checkListsPage = new CheckListsPage(driver, properties.getEnviroment());

		homeForAgent.waitPageIsLoad();
		
		checkListsPage.loadPage(); 
		checkListsPage.assignChecklist(checklist,employee,true);
		
		checkListsPage.logout();
		loginAsEmployee(employee.personData.getEmail(), employee.password);
		homeForAgent.goToTasks();
		return homeForAgent;
	}
}