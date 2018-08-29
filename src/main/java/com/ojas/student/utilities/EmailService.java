package com.ojas.student.utilities;

import java.util.Properties;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailService implements MailSender {
	
	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailProperties();
		
		// sending message
		sender.send(simpleMessage);
	}

	private JavaMailSenderImpl mailProperties() {

		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com");
		sender.setUsername("unlock.delete@gmail.com");
		sender.setPassword("123123654");

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "456");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		sender.setJavaMailProperties(prop);
	
		return sender;

	}

	@Override
	public void send(SimpleMailMessage... simpleMessages) throws MailException {

	}
}