package com.demo.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessgae(String message) {

		jmsTemplate.convertAndSend("welcome", message);

	}

}
