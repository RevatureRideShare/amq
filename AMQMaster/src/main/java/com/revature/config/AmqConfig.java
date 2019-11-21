package com.revature.config;

import com.revature.services.RabbitMqServiceImpl;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// ! Config class to setup the queues by name that RabbitMQServiceImpl will listen at.
// ! Defines profile as either config (the class name) or "messaging".
@Profile({"config", "messaging"})
@Configuration
public class AmqConfig {

  // !Sends messages() bean to RabbitMQServiceImpl.java
  @Bean
  public Queue messagesQueue0() {
    return new Queue("messagesQueue0");
  }

  // I am not sure if this is actually necessary. I don't think it is doing anything.
  // ! Defines the receiver as RabbitMQServiceImpl.
  @Profile("receiver")
  @Bean
  public RabbitMqServiceImpl receiver() {
    return new RabbitMqServiceImpl();
  }

}
