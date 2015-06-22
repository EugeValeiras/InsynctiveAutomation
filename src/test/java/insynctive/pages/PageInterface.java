package insynctive.pages;

import java.io.IOException;

public interface PageInterface {
	
	public boolean isPageLoad();
	public void waitPageIsLoad() throws IOException, InterruptedException;
	
}
