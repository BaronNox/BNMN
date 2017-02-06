package net.baronnox.test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {

	public static void main(String[] args) {
		// String to = "jonas.stowasser@me.com"; //
		String to = "cobrafrog@googlemail.com";
		String from = "bn.mailnotifier@gmail.com";

		final String usrName = "bn.mailnotifier@gmail.com";
		final String pw = "?_{QCeN6P)r#L&K";

		String host = "smtp.gmail.com";

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", "587");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usrName, pw);
			}
		});

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject("Testing Subject");
			msg.setText("Why u eat so much chicken?");

			for (int i = 0; i < 5; i++) {
				Transport.send(msg);
				System.out.println("Message sent successfully...");
			}

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
