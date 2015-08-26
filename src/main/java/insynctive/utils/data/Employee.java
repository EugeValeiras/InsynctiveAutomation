package insynctive.utils.data;

public enum Employee {

	NO_JOB_EMPLOYEE("insynctiveapps+nojobemployee@gmail.com","password","Employee NoJob"),
	W2_EMPLOYEE("insynctiveapps+w2employee@gmail.com","password","Employee W2"),
	AGENT_OFFICER("insynctiveapps+agent@gmail.com","password","Employee Agent");
	
	public final String email;
	public final String password;
	public final String fullName;

	private Employee(String email, String password, String fullname){
		this.email = email;
		this.password = password;
		this.fullName = fullname;
	}
}
