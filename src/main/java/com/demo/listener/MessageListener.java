package com.demo.listener;

import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	//@JmsListener(destination = "welcome", containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(String message) {
		System.out.println(message);
	}

}
