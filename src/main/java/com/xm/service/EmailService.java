package com.xm.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.xm.model.EmailObj;

@Service
public class EmailService {

	@Autowired
	public JavaMailSender emailSender;

	public String sendEmail(EmailObj emailObj) {

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailObj.getEmail());
			message.setSubject(emailObj.getSubject());
			message.setText(emailObj.getMessage());
			emailSender.send(message);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("ngapteam@gmail.com");
		mailSender.setPassword("unimoni@123");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}
}
