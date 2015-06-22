package insynctive.checklist.process;

import java.io.IOException;

public class I9 implements Process {

	String version;
	String cssPath = "#searchAppsResult > table > tbody > tr:nth-child(3) > td:nth-child(2) > table > tbody > tr > td > a";	
	
	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Process: " + this.getClass().getSimpleName() + " Version: " + version;
	}

	@Override
	public void createTask() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
	}
}
