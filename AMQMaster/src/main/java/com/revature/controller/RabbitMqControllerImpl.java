package com.revature.controller;

import com.revature.services.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// ! This class holds the mapping logic implementation for the RabbitMqController.
@RestController
public class RabbitMqControllerImpl implements RabbitMqController {

  RabbitMqService mqService;

  // ! Grabs the RabbitMqService for usage within this class.
  @Autowired
  public void setMqService(RabbitMqService mqService) {
    this.mqService = mqService;
  }

  // ! Maps sendMessage to /sendMessage following REST.
  @Override
  @PostMapping("/sendMessage")
  @ResponseStatus(HttpStatus.CREATED)
  public boolean sendMessage(@RequestBody String messageToSend, String queueName) {
    return mqService.sendMessage(messageToSend, queueName);
  }

  // ! Maps getMessages to /getMessages following REST.
  @Override
  @PostMapping("/getMessages")
  public boolean getMessages(String in) {
    return mqService.getMessages(in);
  }


}
