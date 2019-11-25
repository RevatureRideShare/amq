package com.revature.config;

import com.revature.services.RabbitMqReceiverServiceImpl;
import com.revature.services.RabbitMqSenderServiceImpl;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration class to declare beans for the sender and receiver for RabbitMq. A fanout exchange
 * sends messages to all receivers (micro-services). A direct exchange sends messages to specific
 * receivers (micro-services).
 * 
 * @Profile Defines profile as either config (the class name) or "hello-world".
 * @author ErikHaklar
 */
@Profile({"config", "hello-world"})
@Configuration
public class AmqConfig {

  public static void main(String[] args) {
    System.out.println("Inside main method of AmqConfig.");
  }

  /**
   * A fanout broadcasts all the messages it receives to all of the queues that it knows.
   * 
   * @author ErikHaklar
   */
  @Bean
  public FanoutExchange fanout() {
    return new FanoutExchange("amq.fanout");
  }

  /**
   * A direct exchange broadcasts messages with a specific routing key to queues that accept it.
   * 
   * @author ErikHaklar
   */
  @Bean
  public DirectExchange direct() {
    return new DirectExchange("amq.direct");
  }

  /**
   * Configures the receiver with fanout and direct exchanges. Once we disconnect the consumer the
   * queue should be automatically deleted. To do this with the Spring AMQP client, we defined a
   * AnonymousQueue, which creates a non-durable, exclusive, auto-delete queue with a auto generated
   * name. The receiver class is set to be the RabbitMqReceiverServiceImpl.
   * 
   * @author ErikHaklar
   */
  @Profile("receiver")
  private static class ReceiverConfig {
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
      return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("UserService");
    }

    @Bean
    public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1) {
      return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("LocationService");
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
      return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
    }

    @Bean
    public RabbitMqReceiverServiceImpl receiver() {
      return new RabbitMqReceiverServiceImpl();
    }
  }

  /**
   * Here, the sender class is configured to be the RabbitMqSenderServiceImpl.
   * 
   * @author ErikHaklar
   */
  @Profile("sender")
  @Bean
  public RabbitMqSenderServiceImpl sender() {
    return new RabbitMqSenderServiceImpl();
  }

}
