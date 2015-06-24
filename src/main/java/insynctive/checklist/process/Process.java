package insynctive.checklist.process;

import java.io.IOException;

public interface Process {

	void setVersion(String version);
	public void createTask() throws IOException, InterruptedException;
}
