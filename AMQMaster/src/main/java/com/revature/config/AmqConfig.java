package com.revature.config;

import com.revature.services.RabbitMqReceiverServiceImpl;
import com.revature.services.RabbitMqSenderServiceImpl;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// ! Config class to setup the queues by name that RabbitMQServiceImpl will listen at.
// ! Defines profile as either config (the class name) or "messaging".
@Profile({"config", "hello-world"})
@Configuration
public class AmqConfig {

  public static void main(String[] args) {
    System.out.println("Works, Imagine that...");
  }

  // !Sends messages() bean to RabbitMQServiceImpl.java
  @Bean
  public Queue test() {
    return new Queue("test");
  }

  // ! Defines the receiver as RabbitMQServiceImpl.
  @Profile("receiver")
  private static class ReceiverConfig {
    @Bean
    public RabbitMqReceiverServiceImpl receiver1() {
      return new RabbitMqReceiverServiceImpl(1);
    }

    @Bean
    public RabbitMqReceiverServiceImpl receiver2() {
      return new RabbitMqReceiverServiceImpl(2);
    }
  }

  @Profile("sender")
  @Bean
  public RabbitMqSenderServiceImpl sender() {
    return new RabbitMqSenderServiceImpl();
  }

}
