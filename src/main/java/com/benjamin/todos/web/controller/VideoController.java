package com.benjamin.todos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.io.InputStream;

/**
 * Created by benjamin on 8/18/14.
 */
@Controller
public class VideoController {

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("video")
  @SendToUser
  private void getMediaStream(){
    System.out.println("video");
   simpMessagingTemplate.convertAndSendToUser("TestUser","/topic/video", "I send from backend");
  }
}
