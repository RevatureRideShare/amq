package com.revature.services;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * Receives and sends messages to allow for communication between any micro-services. Listens for
 * messages from the queues specified. Where messages are sent from within this AMQ is determined by
 * the destination defined in the body of the message. Messages should begin with their destination
 * followed by a ! Example: UserService!... Each micro-service should listen for messages for that
 * destination (HTTP Response for that micro-service). Example: "/UserService".
 * 
 * @author ErikHaklar
 */
public class RabbitMqReceiverServiceImpl {

  /**
   * Listens for messages inside autoDeleteQueue1, then calls receive() passing in the message from
   * that queue.
   * 
   * @author ErikHaklar
   */
  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive1(String in) throws InterruptedException {
    receive(in, 1);
  }

  /**
   * Listens for messages inside autoDeleteQueue2, then calls receive() passing in the message from
   * that queue.
   * 
   * @author ErikHaklar
   */
  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void receive2(String in) throws InterruptedException {
    receive(in, 2);
  }

  /**
   * Receives messages from the RabbitMQ queue automatically. There should not be a call to this
   * method except for by a RabbitListener method.
   * 
   * @author ErikHaklar
   */
  @RabbitHandler
  public void receive(String in, int receiver) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    System.out.println("instance " + receiver + " [x] Received '" + in + "'");
    watch.stop();
    System.out
        .println("instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    pullAndSendMessage(in);
  }

  /**
   * Pulls the message from the RabbitMQ queue and then sends it off to it's destination
   * micro-service. GetDestination: Gets the micro-service the message should be sent to by
   * splitting the message where the ! is. Messages should begin with their destination followed by
   * a !. Example: UserService!... Probably Should be pulling messages from the micro-service that
   * is the messages destination. This needs work!
   * 
   * @author ErikHaklar
   */
  public void pullAndSendMessage(String message) {
    String[] getDestination;
    getDestination = message.split("!", 2);

    // Write logic to send message to it's destination here.
  }
}
