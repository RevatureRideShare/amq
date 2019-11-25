package com.revature.controller;

import com.revature.services.IRabbitMqService;
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

  IRabbitMqService mqService;

  /**
   * Grabs the IRabbitMqService for usage within this class.
   * 
   * @author ErikHaklar
   */
  @Autowired
  public void setMqService(IRabbitMqService mqService) {
    this.mqService = mqService;
  }

  /**
   * Maps receiveMessage to /message following REST. Use to receive messages to add into the
   * RabbitMQ queue.
   * 
   * @author ErikHaklar
   */
  @Override
  @PostMapping("/message")
  @ResponseStatus(HttpStatus.CREATED)
  public boolean receiveMessage(@RequestBody String messageToSend, String queueName) {
    return mqService.receiveMessage(messageToSend, queueName);
  }
}
