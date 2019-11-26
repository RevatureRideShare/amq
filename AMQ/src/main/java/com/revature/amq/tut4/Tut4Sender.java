package com.revature.amq.tut4;

import static com.revature.util.LoggerUtil.info;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class provides implementation for sending messages into the AMQ micro-service queue.
 * 
 * @author ErikHaklar
 */
@Service
public class Tut4Sender {

  @Autowired
  public DirectExchange direct() {
    return new DirectExchange("amq.direct");
  }

  AtomicInteger index = new AtomicInteger(0);

  AtomicInteger count = new AtomicInteger(0);

  /**
   * The keys for messages that can be sent. These are the destination micro-services for the
   * message.
   */
  // private final String[] keys = {"orange", "black", "green"};

  /**
   * Sends a message into the RabbitMQ AMQ micro-service Queue after a fixedDelay and initialDelay.
   * Used for direct exchange messaging as the key is a required variable that gives the message the
   * key that is required to receive it.
   */
  // @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send(String message, RabbitTemplate template) {
    // StringBuilder builder = new StringBuilder("Hello to ");
    // if (this.index.incrementAndGet() == 3) {
    // this.index.set(0);
    // }
    // String key = keys[this.index.get()];
    // builder.append(key).append(' ');
    // builder.append(this.count.incrementAndGet());
    // String message = builder.toString();
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

    template.convertAndSend(direct().getName(), splitMessage[0], splitMessage[1]);
    info(" [x] Sent '" + splitMessage[1] + "'");
  }

}
