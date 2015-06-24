package insynctive.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class InsynctiveProperties {
	
	Properties properties = new Properties();

	private String runID;
	private String enviroment;

	private String loginUsername;
	private String loginPassword;
	
	private String newEmployeeName;
	private String newEmployeeLastName;
	private String newEmployeeEmail;
	private String newEmployeePassword;
	
	private String gmailPassword;
	
	private InsynctiveProperties(String propertiesFileString) throws ConfigurateException{
		
		getAllProperties(propertiesFileString);
	}
	
	public static InsynctiveProperties  getProperties(String propertiesFileString) throws ConfigurateException{
		return new InsynctiveProperties(propertiesFileString);
	}
	
	private void getAllProperties(String propertiesFileString) throws ConfigurateException{
		try{
			//Open Properties Files
			Properties runPropertie = new Properties();
			File fileID = new File("run.properties");
			FileInputStream runIDFile = new FileInputStream(fileID);
			runPropertie.load(runIDFile);
			
			FileInputStream fileInput = new FileInputStream(propertiesFileString);
			properties.load(fileInput);
			
			//Get all properties
			runID = runPropertie.getProperty("runID");
			runPropertie.setProperty("runID", String.valueOf(Integer.parseInt(runID)+1));
			
			enviroment = properties.getProperty("environment");
			
			loginUsername = properties.getProperty("loginUsername");
			loginPassword = properties.getProperty("loginPassword");
			
			newEmployeeName = properties.getProperty("newEmployeeName");
			newEmployeeLastName = properties.getProperty("newEmployeeLastName");
			String email = properties.getProperty("newEmployeeEmail");
			newEmployeeEmail = email.split("@")[0]+"+"+runID+"@"+email.split("@")[1];
			newEmployeePassword = properties.getProperty("newEmployeePassword");
			
			gmailPassword = properties.getProperty("gmailPassword");	
			
			//Save new Properties into File
			OutputStream output = new FileOutputStream(fileID);
			runPropertie.store(output, "LAST RUN");
			
		} catch(Exception ex){
			throw new ConfigurateException("Check config file => " + ex.getMessage());
		}
	}
	
	/* Getters and Setters */
	public String getRunID(){
		return runID;
	}
	
	public Properties getProperties() {
		return properties;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public String getNewEmployeeName() {
		return newEmployeeName;
	}

	public String getNewEmployeeLastName() {
		return newEmployeeLastName;
	}

	public String getNewEmployeeEmail() {
		return newEmployeeEmail;
	}

	public String getNewEmployeePassword() {
		return newEmployeePassword;
	}

	public String getGmailPassword() {
		return gmailPassword;
	}
}
