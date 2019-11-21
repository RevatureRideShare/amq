package com.revature.controller;

// ! Controller for the RabbitMq to separate the mapping logic.
// ! Implemented by RabbitMqController.java.
public interface RabbitMqController {

  public boolean sendMessage(String messageToSend, String queueName);

  public boolean getMessages(String in);

}
