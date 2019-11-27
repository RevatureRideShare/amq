package com.revature.amq.tut4;

import static com.revature.util.LoggerUtil.info;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
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
   * Configures the receiver with direct exchanges. Once we disconnect the consumer the queue should
   * be automatically deleted. To do this with the Spring AMQP client, we defined a AnonymousQueue,
   * which creates a non-durable, exclusive, auto-delete queue with a auto generated name. The
   * receiver class is set to be the RabbitMqReceiverServiceImpl.
   */
  @Bean
  public Queue autoDeleteQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Queue autoDeleteQueue2() {
    return new AnonymousQueue();
  }

  @Bean
  public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
    return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
  }

  @Bean
  public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1) {
    return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("black");
  }

  @Bean
  public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue2) {
    return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
  }

  @Bean
  public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue2) {
    return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("black");
  }

  /**
   * Listens for messages inside autoDeleteQueue1, then calls receive() passing in the message from
   * that queue.
   */
  // @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  // public void receive1(String in) throws InterruptedException {
  // receive(in, 1);
  // }

  /**
   * Listens for messages inside autoDeleteQueue2, then calls receive() passing in the message from
   * that queue.
   */
  // @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  // public void receive2(String in) throws InterruptedException {
  // receive(in, 2);
  // }

  /**
   * Receives messages from the RabbitMQ queue automatically. There should not be a call to this
   * method except for by a RabbitListener method.
   */
  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive(String in, int receiver) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    info("instance " + receiver + " [x] Received '" + in + "'");

    watch.stop();
    info("instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
  }

  @RabbitListener(queues = "#{autoDeleteQueue2.name}")
  public void receive1(String in, int receiver) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    info("instance " + receiver + " [x] Received '" + in + "'");

    watch.stop();
    info("instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
  }

}
