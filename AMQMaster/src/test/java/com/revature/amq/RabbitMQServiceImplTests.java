package com.revature.amq;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Before;

import com.revature.services.RabbitMqServiceImpl;

//! Class to run JUnit tests
@SpringBootTest
class RabbitMQServiceImplTests {
	
	//! Grabs the default template rabbit has that contains boilerplate RabbitMQ client classes.
	@Mock
	private RabbitTemplate template;
	
	//! A static instance of the RabbitMQServiceImpl.java class to interact with for tests.
	private static RabbitMqServiceImpl mqServiceImpl = new RabbitMqServiceImpl();

	
	private String messageToSend = "messagesQueue0!message";
	
	private String queueName = "messagesQueue0";
	
	
	@Before
	public void setup()
	{
		
	}
	
	@Test
	void contextLoads() {
	}
	
	//! Tests if a message can be sent along to it's destination.
	@Test
	void sendMessage()
	{
		assertTrue(mqServiceImpl.sendMessage(messageToSend, queueName));
		Mockito.verify(template).convertAndSend(messageToSend, queueName);
	}

	//! Tests if a message can be received by the AMQ.
	@Test
	void receiveMessage()
	{
		assertEquals(true, mqServiceImpl.getMessages("UserService!message"));
	}
}
