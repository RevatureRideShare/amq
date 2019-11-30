package com.revature.amq.tut4;

import static com.revature.util.LoggerUtil.info;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class provides implementation for sending messages into the AMQ micro-service queue. Note:
 * Where messages are sent from within this AMQ is determined by the destination defined in the body
 * of the message. Messages should begin with their destination followed by a ! Example:
 * UserService!Hello World.
 * 
 * @author ErikHaklar
 */
@Service
public class Tut4Sender {

  /**
   * The RabbitTemplate is a class provided by Spring for ease of use. It contains boilerplate code
   * for RabbitMQ client classes.
   */
  private RabbitTemplate template;

  /**
   * Grabs the RabbitTemplate from Spring's library at:
   * org.springframework.amqp.rabbit.core.RabbitTemplate.
   */
  @Autowired
  public void setTemplate(RabbitTemplate template) {
    this.template = template;
  }

  public DirectExchange direct;

  @Autowired
  public void setDirect(DirectExchange direct) {
    this.direct = direct;
  }

  /**
   * Used for direct exchange messaging into the RabbitMQ queue where the key is a required variable
   * that gives the message the key that is required to receive it. In other words, the message is
   * given a key for it's micro-service destination.
   */
  public void send(String message) {
    /**
     * Removes the string that postman attaches when testing the message.
     */
    String postmanString = "\"messageToSend\":\"";
    String postmanString1 = "\"";
    String postmanStringRemoved = message.replace(postmanString, "");
    String postmanStringRemoved1 = postmanStringRemoved.replace(postmanString1, "");

    /**
     * Gets the key for the destination of the message from within the message body itself.
     */
    String[] splitMessage;
    splitMessage = postmanStringRemoved1.split("!", 2);
    info("The messages key is: " + splitMessage[0]);
    info("The message getting sent is: " + splitMessage[1]);

    template.convertAndSend(direct.getName(), splitMessage[0], splitMessage[1]);
    info(" [x] Sent '" + splitMessage[1] + "'");
  }

}
