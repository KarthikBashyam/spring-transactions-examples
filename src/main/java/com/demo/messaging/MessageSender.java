package com.demo.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Transactional
	public void sendMessage(String message) {

		jmsTemplate.convertAndSend("welcome", message);

	}

}
