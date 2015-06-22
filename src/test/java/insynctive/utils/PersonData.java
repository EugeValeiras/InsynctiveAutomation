package insynctive.utils;

public class PersonData {

	private String name;
	private String lastName;
	private String email;
	private String emailInvitationSubject = "Invitation to signup to Alpha 6 HR Portal";
	
	public PersonData(String name, String lastname, String email){
		this.name = name;
		this.lastName = lastname;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailInvitationSubject() {
		return emailInvitationSubject;
	}

	public void setEmailInvitationSubject(String emailInvitationSubject) {
		this.emailInvitationSubject = emailInvitationSubject;
	}
}
