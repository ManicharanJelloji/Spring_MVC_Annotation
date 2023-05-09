package com.springmvc.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
//	@Autowired
//	private MailSender mailSender;
	
	public void sendEmail(Register register) {
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom("manicharan7002@outlook.com");
		mailMessage.setSubject("Welcome Message");
		mailMessage.setText("Welcome to spring Application");
		mailMessage.setTo(register.getEmail());
		javaMailSender.send(mailMessage);
		
		
		
	}

}
