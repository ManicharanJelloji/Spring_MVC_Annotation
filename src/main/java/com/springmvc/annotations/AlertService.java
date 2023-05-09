package com.springmvc.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendUserAlert(Register register) {
		jmsTemplate.convertAndSend(register);
	}
	
	public Register getRegisterAlert() {
		return (Register)jmsTemplate.receiveAndConvert();
	}
}
