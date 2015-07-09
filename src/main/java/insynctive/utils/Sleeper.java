package insynctive.utils;

import org.openqa.selenium.WebDriver;

public class Sleeper {

	public static void sleep(int time, WebDriver driver){
		try{
			if(InsynctiveProperties.IsSauceLabs()){
				driver.wait(time); //This is for SauceLabs
			} else {
				Thread.sleep(time); //This is for LOCAL
			}
		} catch(Exception cE) {
			//DONT WAIT
		}
	}
}
