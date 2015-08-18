package insynctive.utils;

import insynctive.checklist.process.Process;
import insynctive.utils.PersonData.Gender;
import insynctive.utils.PersonData.MaritalStatus;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

public class Task {

	private static String DEFAULT_FILE = "personFileData.json";
	private String detail;
	private String basicTaskInstruction;
	private String additionalInstruction;
	static JSONParser parser = new JSONParser();

	
	public Task() throws ConfigurationException {
	}
	
	public static List<Task> getTasks() throws ConfigurationException{
		return addData(DEFAULT_FILE);
	}
	
	private static List<Task> addData(String path) throws ConfigurationException {
		try {
			JSONObject fileObject = (JSONObject) parser.parse(new FileReader(path));
			JSONArray jsonTasks = (JSONArray) fileObject.get("Tasks");
			List<Task> tasks = new ArrayList<Task>();
			for (Object taskObject : jsonTasks) {
				JSONObject jsonTask = (JSONObject) taskObject;
				Task task = new Task();
				task.detail = (String)jsonTask.get("detail");
				task.basicTaskInstruction = (String)jsonTask.get("basicTaskInstruction");
				task.additionalInstruction = (String)jsonTask.get("additionalInstruction");
				tasks.add(task);
			}
			return tasks;
			
		} catch(Exception ex) {
			throw new ConfigurationException("Fail reading (String)person configuration ====> "+ ex.getMessage());
		}
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getBasicTaskInstruction() {
		return basicTaskInstruction;
	}
	public void setBasicTaskInstruction(String basicTaskInstruction) {
		this.basicTaskInstruction = basicTaskInstruction;
	}
	public String getAdditionalInstruction() {
		return additionalInstruction;
	}
	public void setAdditionalInstruction(String additionalInstruction) {
		this.additionalInstruction = additionalInstruction;
	}
}
