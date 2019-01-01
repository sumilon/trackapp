package com.xm.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.xm.model.EmailObj;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	public String sendMAil(EmailObj obj) throws MessagingException {

		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(obj.getSenderName());
			helper.setText(obj.getMessage());
			helper.setSubject(obj.getSubject());
			sender.send(message);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}

	}
}
