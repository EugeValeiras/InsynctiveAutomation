package insynctive.checklist;

import insynctive.checklist.process.Process;

import java.util.ArrayList;
import java.util.List;

public class Checklist {

	private String name;
	private List<Process> processes;

	public Checklist(String name) {
		this.name = name;
		processes = new ArrayList<Process>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public  List<Process> getProcess(){
		return processes;
	}

	public void addProcess(Process proc) {
		processes.add(proc);
	}

	@Override
	public String toString() {
		return "Nombre: " + name + " | Processes: " + processes;
	}
}
