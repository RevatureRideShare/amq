package com.revature.services;

public class RabbitMqServiceImpl implements IRabbitMqService {

  private RabbitMqSenderServiceImpl sender = new RabbitMqSenderServiceImpl();

  @Override
  public boolean receiveMessage(String messageToSend, String queueName) {
    sender.send(messageToSend);
    return true;
  }
}
