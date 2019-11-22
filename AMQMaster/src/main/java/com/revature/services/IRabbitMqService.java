package com.revature.services;

// ! To handle receiving messages for the AMQ micro-service and then send them to their
// ! respective destinations as defined within that message's body.
// ! Implemented by RabbitMQServiceImpl.java.
public interface IRabbitMqService {

  // ! For sending messages to be read by another micro-service.
  // ! messageToSend requires the message body that will be sent, supplied by the message received.
  // ! Requires it's destination to be written first followed by a !.
  // ! The destination will be grabbed by splitting the message by where the ! is located.
  // ! Example message: "UserService!YourMessageHere".
  // ! queueName requires the name of the queue the message should be sent to.
  // ! The queueName will receive the destination supplied in the received message for handling.
  // ! Returns true if successful.
  public boolean receiveMessage(String messageToSend, String queueName);
}