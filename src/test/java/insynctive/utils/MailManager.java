package insynctive.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;

public class MailManager {

	public static String getAuthLink(String username, String password,
			String subject) throws Exception {

		String registationToken;

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		try {
			store.connect("imap.gmail.com", username, password);
		} catch (Exception ex) {
			throw new Exception("Fail in connection"+ ex.getMessage());
		}

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);

		Message[] messages = null;
		boolean isMailFound = false;
		Message confirmationMAil = null;

		// Search for mail
		for (int i = 0; i < 10; i++) {
			messages = folder.search(new SubjectTerm(subject),
					folder.getMessages());
			// Wait for 10 seconds
			if (messages.length == 0) {
				Thread.sleep(15000);
			}
		}

		// Search for unread mail from God
		// This is to avoid using the mail for which
		// Registration is already done
		for (Message mail : messages) {
			if (!mail.isSet(Flags.Flag.SEEN)) {
				confirmationMAil = mail;
				isMailFound = true;
				break;
			}
		}

		// Test fails if no unread mail was found from God
		if (!isMailFound) {
			throw new Exception("Could not find email");

			// Read the content of mail and launch registration URL
		} else {
			String line;
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					confirmationMAil.getInputStream()));
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			registationToken = buffer.toString().split("logintoken=")[1]
					.split("\"")[0];

			return registationToken;
		}

	}

}
