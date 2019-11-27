package com.revature.amq.tut4;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to declare beans for the sender and receiver for RabbitMq. A fanout exchange
 * sends messages to all receivers (micro-services). A direct exchange sends messages to specific
 * receivers (micro-services).
 * 
 * @Profile Defines profile as either config (the class name) or "routing".
 * @author ErikHaklar
 */
@Configuration
public class Tut4Config {

  /**
   * A direct exchange broadcasts messages with a specific routing key to queues that accept it.
   */
  @Bean
  public DirectExchange direct() {
    return new DirectExchange("amq.direct");
  }

  /**
   * Here, the sender class is configured to be the Tut4Sender.java.
   */
  @Bean
  public Tut4Sender sender() {
    return new Tut4Sender();
  }

}
