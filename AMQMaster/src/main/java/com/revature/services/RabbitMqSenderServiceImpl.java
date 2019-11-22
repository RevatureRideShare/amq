package com.revature.services;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class RabbitMqSenderServiceImpl {
  @Autowired
  private RabbitTemplate template;

  @Autowired
  private Queue queue;

  /**
   * Sends a message into the RabbitMQ Queue after a fixedDelay and initialDelay.
   * 
   * @author ErikHaklar
   */
  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send(String messageToSend) {
    this.template.convertAndSend(queue.getName(), messageToSend);
    System.out.println(" [x] Sent '" + messageToSend + "'");
  }
}
