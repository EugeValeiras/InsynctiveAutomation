package insynctive.utils;

import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.MessagingException;

public class InsynctiveProperties {
	
	Properties properties = new Properties();
	
	private String enviroment;

	private String loginUsername;
	private String loginPassword;
	
	private String newEmployeeName;
	private String newEmployeeLastName;
	private String newEmployeeEmail;
	private String newEmployeePassword;
	
	private String gmailEmail;
	private String gmailPassword;
	
	private InsynctiveProperties(String propertiesFileString) throws ConfigurateException{
		getAllProperties(propertiesFileString);
	}
	
	public static InsynctiveProperties  getProperties(String propertiesFileString) throws ConfigurateException{
		return new InsynctiveProperties(propertiesFileString);
	}
	
	private void getAllProperties(String propertiesFileString) throws ConfigurateException{
		try{
			properties.load(new FileInputStream(propertiesFileString));

			enviroment = properties.getProperty("enviroment");
			
			loginUsername = properties.getProperty("loginUsername");
			loginPassword = properties.getProperty("loginPassword");
			
			newEmployeeName = properties.getProperty("newEmployeeName");
			newEmployeeLastName = properties.getProperty("newEmployeeLastName");
			newEmployeeEmail = properties.getProperty("newEmployeeEmail");
			newEmployeePassword = properties.getProperty("newEmployeePassword");
			
			gmailEmail = properties.getProperty("gmailEmail");
			gmailPassword = properties.getProperty("gmailPassword");	
		} catch(Exception ex){
			throw new ConfigurateException("Check config file => " + ex.getMessage());
		}
	}
	
	/* Getters and Setters */
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getNewEmployeeName() {
		return newEmployeeName;
	}

	public void setNewEmployeeName(String newEmployeeName) {
		this.newEmployeeName = newEmployeeName;
	}

	public String getNewEmployeeLastName() {
		return newEmployeeLastName;
	}

	public void setNewEmployeeLastName(String newEmployeeLastName) {
		this.newEmployeeLastName = newEmployeeLastName;
	}

	public String getNewEmployeeEmail() {
		return newEmployeeEmail;
	}

	public void setNewEmployeeEmail(String newEmployeeEmail) {
		this.newEmployeeEmail = newEmployeeEmail;
	}

	public String getNewEmployeePassword() {
		return newEmployeePassword;
	}

	public void setNewEmployeePassword(String newEmployeePassword) {
		this.newEmployeePassword = newEmployeePassword;
	}

	public String getGmailEmail() {
		return gmailEmail;
	}

	public void setGmailEmail(String gmailEmail) {
		this.gmailEmail = gmailEmail;
	}

	public String getGmailPassword() {
		return gmailPassword;
	}

	public void setGmailPassword(String gmailPassword) {
		this.gmailPassword = gmailPassword;
	}
}
