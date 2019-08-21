package com.demo.messaging;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.listener.RabbitMQListener;

@Component
public class RabbitMQSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private RabbitMQListener rabbitMQListener;

	public RabbitMQSender(RabbitMQListener rabbitMQListener) {
		this.rabbitMQListener = rabbitMQListener;
	}

	public void sendMessage(String message) throws InterruptedException {
		rabbitTemplate.convertAndSend("spring.trx.hello", message);
		rabbitMQListener.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}

}
