package com.revature.services;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * This class provides implementation for sending messages into the AMQ micro-service queue.
 * 
 * @author ErikHaklar
 */
public class RabbitMqSenderServiceImpl {
  @Autowired
  private RabbitTemplate template;

  /**
   * The FanoutExchange is grabbed from AmqConfig.java.
   * 
   * @author ErikHaklar
   */
  @Autowired
  private FanoutExchange fanout;

  /**
   * The DirectExchange is grabbed from AmqConfig.java.
   * 
   * @author ErikHaklar
   */
  @Autowired
  private DirectExchange direct;

  /**
   * The keys for messages that can be sent. These are the destination micro-services for the
   * message.
   * 
   * @author ErikHaklar
   */
  private final String[] keys = {"UserService", "LocationService"};

  AtomicInteger index = new AtomicInteger(0);

  /**
   * Sends a message into the RabbitMQ AMQ micro-service Queue after a fixedDelay and initialDelay.
   * Used for non direct exchange messaging as there cannot be a key associated with this type of
   * messaging. Keys essentially give the message it's destination by requiring a micro-service to
   * listen for that exact key to receive the message for.
   * 
   * @author ErikHaklar
   */
  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send(String messageToSend) {
    this.template.convertAndSend(fanout.getName(), "", messageToSend);
    System.out.println(" [x] Sent '" + messageToSend + "'");
  }

  /**
   * Sends a message into the RabbitMQ AMQ micro-service Queue after a fixedDelay and initialDelay.
   * Used for direct exchange messaging as the key is a required variable that gives the message the
   * key that is required to receive it.
   * 
   * @author ErikHaklar
   */
  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send1(String messageToSend) {
    if (this.index.incrementAndGet() == 2) {
      this.index.set(0);
    }
    String key = keys[this.index.get()];

    this.template.convertAndSend(direct.getName(), key, messageToSend);
    System.out.println(" [x] Sent '" + messageToSend + "'");
  }
}
