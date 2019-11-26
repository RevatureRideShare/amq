package com.revature.amq.controller;

/**
 * Controller for the RabbitMq to separate the mapping logic. Implemented by
 * RabbitMqController.java.
 * 
 * @author ErikHaklar
 */
public interface IRabbitMqController {

  public void receiveMessage(String messageToSend);

}
