package com.revature.amq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Command Line Runner Configuration class rabbit requires. See:
 * https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html.
 * 
 * @author ErikHaklar
 */
public class RabbitAmqpTutorialsRunner implements CommandLineRunner {

  /**
   * The name before .client (amqservice) is important here. It is what is defined in the
   * application.yml file, where the duration is set (example; duration: 10000).
   */
  @Value("${amqservice.client.duration:0}")
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