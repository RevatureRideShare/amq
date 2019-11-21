package com.revature.services;

import static com.revature.util.LoggerUtil.info;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

// ! Receives and sends messages to allow for communication between any micro-services.
// ! Listens for messages from the queues specified. Example: "messagesQueue0" listens
// ! for messages defined in AMQConfig.java.
// ! All messages sent to the AMQ should go to messagesQueue0 or above, where they
// ! are sent from within this AMQ is determined by the destination defined in the
// ! body of the message.
// ! Messages should begin with their destination followed by a !
// ! Example: UserService!...
// ! Each micro-service should listen for messages from the destination
// ! (queue name for that micro-service).
@RabbitListener(queues = "messagesQueue0")
public class RabbitMqServiceImpl implements RabbitMqService {

  // ! Grabs the default template rabbit has that contains boilerplate RabbitMQ client classes.
  @Autowired
  private RabbitTemplate template;

  // ! Receives Queue from AMQConfig.java
  @Autowired
  private Queue queue;



  public static void main(String[] argv) throws Exception {

  }

  // !Sends a message to the RabbitMQ queue to be read by another method in another micro-service.
  @Override
  public boolean sendMessage(String messageToSend, String queueName) {
    // String message = "Hello World!";
    System.out.println(messageToSend + " " + queueName);
    this.template.convertAndSend(queueName, messageToSend);
    System.out.println(" [x] Sent '" + messageToSend + "'");

    return true;
  }


  // ! Listens for messages from the AMQ's queue; "MessagesQueue0".
  // ! Sends messages received to their destination as determined by their body.
  @Override
  @RabbitHandler
  public boolean getMessages(String in) {

    System.out.println(" [x] Received '" + in + "'");

    // ! Get the micro-service the message should be sent to by splitting the message where the !
    // is.
    // ! Messages should begin with their destination followed by a !
    // ! Example: UserService!...
    String[] getDestination;
    getDestination = in.split("!", 2);

    boolean test = false;

    info("Sending message " + in + " to destination: " + getDestination[0]);
    test = sendMessage(in, getDestination[0]);

    return test;
  }

}
