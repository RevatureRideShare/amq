package com.revature.amq.tut4;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

  /**
   * Configures the receiver with direct exchanges. Once we disconnect the consumer the queue should
   * be automatically deleted. To do this with the Spring AMQP client, we defined a AnonymousQueue,
   * which creates a non-durable, exclusive, auto-delete queue with a auto generated name. The
   * receiver class is set to be the RabbitMqReceiverServiceImpl.
   */
  @Bean
  public Queue autoDeleteQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Queue autoDeleteQueue2() {
    return new AnonymousQueue();
  }

  @Bean
  public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
    return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
  }

  @Bean
  public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1) {
    return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("black");
  }

  @Bean
  public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue2) {
    return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
  }

  @Bean
  public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue2) {
    return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("black");
  }

}
