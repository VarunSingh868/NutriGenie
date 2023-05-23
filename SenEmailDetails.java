import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class SenEmailDetails {
	public static final String from = "v.singhkvs327@gmail.com";
	public static final String sub = "Diet Plan:";
	public static final String user = "v.singhkvs327";
	public static final String pass = "ejeyepfnwnpcaflx";
	public void sendUserDetails(String to, String str) {
		Properties pr = new Properties();
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.starttls.enable", "true");
		pr.put("mail.smtp.tsl.trust", "smtp.gmail.com");
		pr.put("mail.smtp.socketFactory.port", "587");
		pr.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(pr, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,pass);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject(sub);
			message.setText(str);
			Transport.send(message);
		}
		catch(Exception e) {
			
		}
	}
}
