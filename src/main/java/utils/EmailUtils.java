package utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	public static void sendTestReport(String reportPath) {

		final String senderMail = "butterflybucket98@gmail.com";
		final String appPassword = "jzdqttxekwiwjqyd";
		final String recipientEmail = "butterflybucket98@gmail.com";

		// SMTP server properties
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");

		// create session with authentication
		Session session = Session.getInstance(prop, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderMail, appPassword);
			}
		});
		session.setDebug(true);

		try {
			// create email message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderMail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Test Email From Automation");
			// message.setText("Hello \n This is a test email from Java \n Regards,\n QA
			// Team");

			// Email body part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hello \n \n This is a test email from Java \n Regards,\n QA Team");

			// Attachment part
			MimeBodyPart attachmentPart = new MimeBodyPart();
			//String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			System.out.println("Attachement path is:" + reportPath);
			attachmentPart.attachFile(new File(reportPath));

			// Combine body and attachment parts
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);

			// send email
			Transport.send(message);
			System.out.println("Email sent Successfully***");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
