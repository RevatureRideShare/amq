package com.revature.amq;

import static com.revature.util.LoggerUtil.info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The driver class for the AMQ micro-service. Important note about how this AMQ micro-service
 * works: When a message is sent to the AMQ micro-service (which is done through a PostMapping), the
 * message is then sent into the AMQ micro-service queue. Once it is inside of that queue, the
 * destination micro-service should listen to the queue for a message with the key for that
 * micro-service and grab it automatically. The benefit of this is that messages are queued and
 * handled asynchronously.
 * 
 * @author ErikHaklar
 */
@SpringBootApplication
@EnableScheduling
public class AmqApplication {

  /**
   * This is a command line runner which indicates that a bean should run when contained within a
   * SpringApplication. A command line runner allows us to run the application from powershell and
   * to use commands to activate and test profile methods we configured. The profiles we configured
   * to test are "sender" and "receiver". usage_message is configured to start along with the
   * application in the application.yml file.
   */
  @Profile("usage_message")
  @Bean
  public CommandLineRunner usage() {
    return args -> {
      info("This app uses Spring Profiles to control its behavior.\n");
      info(
          "Sample usage: java -jar rabbit-tutorials.jar --spring.profiles.active=hello-world,sender");
    };
  }

  /**
   * Registers RabbitMQRunner.java if usage-message is not active.
   */
  @Profile("!usage_message")
  @Bean
  public CommandLineRunner tutorial() {
    return new RabbitAmqpTutorialsRunner();
  }

  /**
   * Runs this spring application. Required for any spring boot application to run.
   */
  public static void main(String[] args) throws Exception {
    SpringApplication.run(AmqApplication.class, args);
  }
}
