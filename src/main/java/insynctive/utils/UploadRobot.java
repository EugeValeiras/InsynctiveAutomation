package insynctive.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class UploadRobot {

	public static void uploadPDF(WebDriver driver) throws AWTException{
		String url = "http://exa.freeiz.com/i/dd.pdf";
		StringSelection stringSelection = new StringSelection(url);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);		
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);

		Sleeper.sleep(1000, driver);
		r.keyPress(KeyEvent.VK_ENTER);    // confirm by pressing Enter in the end
		r.keyRelease(KeyEvent.VK_ENTER);
	}
}
