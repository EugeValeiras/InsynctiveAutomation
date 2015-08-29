package insynctive.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadRobot {

	public static void uploadPDF(WebDriver driver) throws AWTException{
		driver.findElement(By.id("up-drop-zone-input")).click();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_H);        // H
		r.keyRelease(KeyEvent.VK_H);
		r.keyPress(KeyEvent.VK_T);        // T
		r.keyRelease(KeyEvent.VK_T);
		r.keyPress(KeyEvent.VK_T);        // T
		r.keyRelease(KeyEvent.VK_T);
		r.keyPress(KeyEvent.VK_P);        // P
		r.keyRelease(KeyEvent.VK_P);
		r.keyPress(KeyEvent.VK_COLON);    // : (colon)
		r.keyRelease(KeyEvent.VK_COLON);
		r.keyPress(KeyEvent.VK_SLASH);    // / (slash)
		r.keyRelease(KeyEvent.VK_SLASH);
		r.keyPress(KeyEvent.VK_SLASH);    // / (slash)
		r.keyRelease(KeyEvent.VK_SLASH);
		r.keyPress(KeyEvent.VK_E);        // E
		r.keyRelease(KeyEvent.VK_E);
		r.keyPress(KeyEvent.VK_X);        // X
		r.keyRelease(KeyEvent.VK_X);
		r.keyPress(KeyEvent.VK_A);        // A
		r.keyRelease(KeyEvent.VK_A);
		r.keyPress(KeyEvent.VK_PERIOD);   // . (period)
		r.keyRelease(KeyEvent.VK_PERIOD);
		r.keyPress(KeyEvent.VK_F);        // F
		r.keyRelease(KeyEvent.VK_F);
		r.keyPress(KeyEvent.VK_R);        // R
		r.keyRelease(KeyEvent.VK_R);
		r.keyPress(KeyEvent.VK_E);        // E
		r.keyRelease(KeyEvent.VK_E);
		r.keyPress(KeyEvent.VK_E);        // E
		r.keyRelease(KeyEvent.VK_E);
		r.keyPress(KeyEvent.VK_I);        // I
		r.keyRelease(KeyEvent.VK_I);
		r.keyPress(KeyEvent.VK_Z);        // Z
		r.keyRelease(KeyEvent.VK_Z);
		r.keyPress(KeyEvent.VK_PERIOD);  // . (period)
		r.keyRelease(KeyEvent.VK_PERIOD);
		r.keyPress(KeyEvent.VK_C);        // C
		r.keyRelease(KeyEvent.VK_C);
		r.keyPress(KeyEvent.VK_O);        // O
		r.keyRelease(KeyEvent.VK_O);
		r.keyPress(KeyEvent.VK_M);        // M
		r.keyRelease(KeyEvent.VK_M);
		r.keyPress(KeyEvent.VK_SLASH);    // / (slash)
		r.keyRelease(KeyEvent.VK_SLASH);
		r.keyPress(KeyEvent.VK_I);        // I
		r.keyRelease(KeyEvent.VK_I);
		r.keyPress(KeyEvent.VK_SLASH);    // / (slash)
		r.keyRelease(KeyEvent.VK_SLASH);
		r.keyPress(KeyEvent.VK_D);        // D
		r.keyRelease(KeyEvent.VK_D);
		r.keyPress(KeyEvent.VK_D);        // D
		r.keyRelease(KeyEvent.VK_D);
		r.keyPress(KeyEvent.VK_PERIOD);        // . (period)
		r.keyRelease(KeyEvent.VK_PERIOD);
		r.keyPress(KeyEvent.VK_P);        // P
		r.keyRelease(KeyEvent.VK_P);
		r.keyPress(KeyEvent.VK_D);        // D
		r.keyRelease(KeyEvent.VK_D);
		r.keyPress(KeyEvent.VK_F);        // F
		r.keyRelease(KeyEvent.VK_F);

		// etc. for the whole file path

		r.keyPress(KeyEvent.VK_ENTER);    // confirm by pressing Enter in the end
		r.keyRelease(KeyEvent.VK_ENTER);
	}
}
