import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class SendTempEmail {
	public static final String from = "v.singhkvs327@gmail.com";
	public static final String sub = "OTP for Verification";
	public static final String user = "v.singhkvs327";
	public static final String pass = "ejeyepfnwnpcaflx";
	public static String OTP = Integer.toString((int) (Math.random()*1000000));;
	public boolean sendmail(String to) {
		boolean flag = false;
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
			message.setText("Dear User,\nYour OTP for verification is: " + OTP + "\n\nThank You");
			Transport.send(message);
			flag = true;
		}
		catch(Exception e) {
			
		}
		return flag;
	}
	public String retOTP() {
		return OTP;
	}
}
