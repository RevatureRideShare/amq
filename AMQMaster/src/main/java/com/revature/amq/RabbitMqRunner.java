package com.revature.amq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

// ! Configuration class rabbit requires. See:
// ! https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html.
public class RabbitMqRunner implements CommandLineRunner {

  @Value("${messagesQueue0.client.duration:0}")
  private int duration;

  @Autowired
  private ConfigurableApplicationContext ctx;

  @Override
  public void run(String... arg0) throws Exception {
    System.out.println("Ready ... running for " + duration + "ms");
    Thread.sleep(duration);
    ctx.close();
  }

}
