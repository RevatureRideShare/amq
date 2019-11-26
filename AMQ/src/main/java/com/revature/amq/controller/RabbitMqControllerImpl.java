package com.revature.amq.controller;

import static com.revature.util.LoggerUtil.info;
import static com.revature.util.LoggerUtil.warn;
import com.revature.amq.tut4.Tut4Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class holds the mapping logic implementation for the RabbitMqController.
 * 
 * @author ErikHaklar
 */
@RestController
public class RabbitMqControllerImpl implements IRabbitMqController {

  /**
   * Creates a new Tut4Sender for usage within this class.
   */
  @Autowired
  public Tut4Sender sender() {
    return new Tut4Sender();
  }

  /**
   * Maps receiveMessage to /message following REST. Use to receive messages to add into the
   * RabbitMQ queue.
   */
  @Override
  @PostMapping("/message")
  @ResponseStatus(HttpStatus.CREATED)
  public void receiveMessage(@RequestBody String messageToSend) {

    try {
      info("MessageToSend:" + messageToSend);
    } catch (NullPointerException e) {
      warn(
          "NullPointerException for info(\"MessageToSend:\" + messageToSend); @ RabbitMqControllerImpl.java");
      e.printStackTrace();
    }

    try {
      sender().send(messageToSend);
    } catch (NullPointerException e) {
      warn("NullPointerException for sender.send() @ RabbitMqControllerImpl.java");
      e.printStackTrace();
    }

  }
}
