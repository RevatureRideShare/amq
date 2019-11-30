package com.revature.amq.tut4;

import static com.revature.util.LoggerUtil.info;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * Note: This class does not belong within the AMQ micro-service, but should be included within
 * every other micro-service that would like to receive RabbitMQ messages. This class currently
 * exists within the AMQ Micro-service for testing purposes. Receives and sends messages to allow
 * for communication between any micro-services. Listens for messages from the queues specified.
 * Where messages are sent from within this AMQ is determined by the destination defined in the body
 * of the message. Messages should begin with their destination followed by a ! Example:
 * UserService!Hello World. Each micro-service should listen for messages for that destination by
 * including/using this class.
 * 
 * @author ErikHaklar
 */
@Service
public class Tut4Receiver {

  /**
   * A direct exchange broadcasts messages with a specific routing key to queues that accept it.
   */
  public DirectExchange direct;

  @Autowired
  public void setDirect(DirectExchange direct) {
    this.direct = direct;
  }

  /**
   * Receives messages from the RabbitMQ queue automatically. There should not be a call to this
   * method except for by a RabbitListener method.
   */
  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive(String in) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    info(" [x] Received '" + in + "'");

    watch.stop();
    info(" [x] Done in " + watch.getTotalTimeSeconds() + "s");
  }

  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void receive1(String in) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    info(" [x] Received '" + in + "'");

    watch.stop();
    info(" [x] Done in " + watch.getTotalTimeSeconds() + "s");
  }

}
