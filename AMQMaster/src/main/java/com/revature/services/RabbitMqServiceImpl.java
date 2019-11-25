package com.revature.services;

/**
 * Service class to call implementations for AMQ message handling. Note: RabbitMqReciverServiceImpl
 * has an automatic listner for messages put in the AMQ micro-service queue. Recall that when a
 * message is sent to the AMQ micro-service (which is done through a PostMapping), the message is
 * then sent into the AMQ micro-service queue. Once it is inside of that queue, the
 * RabbitMqReceiverServiceImpl will automatically grab it, and send it off to it's destination. The
 * benefit of this is that messages are queued and handled asynchronously.
 * 
 * @author ErikHaklar
 */
public class RabbitMqServiceImpl implements IRabbitMqService {

  private RabbitMqSenderServiceImpl sender = new RabbitMqSenderServiceImpl();

  /**
   * Takes the received message from the PostMapping and sends it into the AMQ micro-service queue.
   * 
   * @author ErikHaklar
   */
  @Override
  public boolean receiveMessage(String messageToSend, String queueName) {
    sender.send1(messageToSend);
    return true;
  }
}
