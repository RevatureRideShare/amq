package com.revature.services;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * Receives and sends messages to allow for communication between any micro-services. Listens for
 * messages from the queues specified. All messages sent to the AMQ should go to test, where they
 * are sent from within this AMQ is determined by the destination defined in the body of the
 * message. Messages should begin with their destination followed by a ! Example: UserService!...
 * Each micro-service should listen for messages from the destination (HTTP Response for that
 * micro-service).
 * 
 * @author ErikHaklar
 */
@RabbitListener(queues = "test")
public class RabbitMqReceiverServiceImpl {

  private final int instance;

  public RabbitMqReceiverServiceImpl(int i) {
    this.instance = i;
  }

  /**
   * Receives messages from the RabbitMQ queue automatically. There should not be a call to this
   * method.
   * 
   * @author ErikHaklar
   */
  @RabbitHandler
  public void receive(String in) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    System.out.println("instance " + this.instance + " [x] Received '" + in + "'");
    // doWork(in);
    watch.stop();
    System.out
        .println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    pullAndSendMessage(in);
  }

  /**
   * Pulls the message from the RabbitMQ queue and then sends it off to it's destination
   * micro-service. GetDestination: Gets the micro-service the message should be sent to by
   * splitting the message where the ! is. Messages should begin with their destination followed by
   * a !. Example: UserService!...
   * 
   * @author ErikHaklar
   */
  public void pullAndSendMessage(String message) {
    String[] getDestination;
    getDestination = message.split("!", 2);

    // Write logic to send message to it's destination here.
  }
}
