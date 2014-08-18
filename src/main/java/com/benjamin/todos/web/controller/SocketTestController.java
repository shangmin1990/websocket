package com.benjamin.todos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;

import java.util.Random;

/**
 * Created by benjamin on 8/14/14.
 */
@Controller
public class SocketTestController {

  private TaskScheduler taskScheduler = new ConcurrentTaskScheduler();

  private Random random = new Random();

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;
  @MessageMapping("/hello111")
  public void hello() throws Exception{
    System.out.println("Recive message");
    Thread.sleep(2000);
    simpMessagingTemplate.convertAndSend("/topic/test1","Send From backend");
  }

  @MessageMapping("/hello222")
  @SendTo("topic/test2")
  public String hello2() throws Exception{
    System.out.println("hello2222 message");
    Thread.sleep(2000);
//    simpMessagingTemplate.convertAndSend("/topic/test2","Send From backend");
    return "This is message 2";
  }

//  @MessageMapping("/start")
//  public void test3(){
//    taskScheduler.scheduleAtFixedRate(new Runnable() {
//      @Override
//      public void run() {
//        int a = random.nextInt();
//        System.out.println(a);
//        simpMessagingTemplate.convertAndSend("/topic/b",a);
//        simpMessagingTemplate.convertAndSend("/topic/a/c",a);
//        simpMessagingTemplate.convertAndSend("/a/b/c",a);
//      }
//    }, 1000);
//  }
}
