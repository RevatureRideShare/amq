package com.revature.controller;

/**
 * Controller for the RabbitMq to separate the mapping logic. Implemented by
 * RabbitMqController.java.
 * 
 * @author ErikHaklar
 */
public interface IRabbitMqController {

  public boolean receiveMessage(String messageToSend, String queueName);

}
