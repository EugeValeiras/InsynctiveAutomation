package insynctive.checklist;

import insynctive.checklist.process.Process;

import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

public class ChecklistReader {

	WebDriver driver;
	JSONParser parser = new JSONParser();
	JSONArray jsonCheckLists;

	public ChecklistReader(WebDriver driver) {
		this.driver = driver;
		try {
			jsonCheckLists = (JSONArray) parser.parse(new FileReader("checklists_create.conf"));
		} catch (Exception ex) {
			// TODO THROW EXCEPTION
		}
	}

	public List<Checklist> getAllCheckLists() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<Checklist> checkListListToReturn = new ArrayList<Checklist>();

		for (Object checklistObject : jsonCheckLists) {
			List<Process> checkListListProcesses = new ArrayList<Process>();

			/* CheckList */
			JSONObject jsonChecklist = (JSONObject) checklistObject;
			JSONArray jsonChecklistProcesses = (JSONArray) jsonChecklist.get("Processes");

			String checklistName = (String) jsonChecklist.get("Checklist_Name");
			Checklist newChecklist = new Checklist(checklistName);

			/* Processes */
			for (Object precessObject : jsonChecklistProcesses) {

				JSONObject jsonPrecess = (JSONObject) precessObject;
				String checklistProcessVersion = (String) jsonPrecess.get("Process_Version");
				String checklistProcessName = (String) jsonPrecess.get("Process_Name");

				Class processClass = null;
				try {
					processClass = Class.forName("insynctive.checklist.process."+ checklistProcessName);
				} catch (Exception ex) {
					System.out.println("Exception creating object: "+ ex.getMessage());
				}
				Process process = (Process) processClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
				process.setVersion(checklistProcessVersion);
				newChecklist.addProcess(process);

			}
			checkListListToReturn.add(newChecklist);
		}
		return checkListListToReturn;
	}
}
