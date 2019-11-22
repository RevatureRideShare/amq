package com.revature.amq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

// ! The driver class for the AMQ micro-service.
@SpringBootApplication
@EnableScheduling
public class AmqApplication {

  /**
   * This is a command line runner which indicates that a bean should run when contained within a
   * SpringApplication.
   * 
   * @return
   */
  @Profile("usage_message")
  @Bean
  public CommandLineRunner usage() {
    return args -> {
      System.out.println("This app uses Spring Profiles to control its behavior.\n");
      System.out
          .println("Sample usage: java -jar AMQ.jar --spring.profiles.active=hello-world,sender");
    };
  }

  // ! Registers RabbitMQRunner.java if usage-message is not active,
  @Profile("!usage_message")
  @Bean
  public CommandLineRunner test() {
    return new RabbitMqRunner();
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(AmqApplication.class, args);
  }

}
