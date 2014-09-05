package com.benjamin.todos.web.controller;

import com.benjamin.todos.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.support.MessageHeaderAccessor;
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
  @MessageMapping("/test")
  public void hello(Todo todo) throws Exception{
    System.out.println("Recive message");
    Thread.sleep(2000);
    simpMessagingTemplate.convertAndSend("/topic/fuckyou","Send From backend");
//    return "hello111 backend";
  }

  @MessageMapping("/{destination}")
//  @SendTo("/topic/sometopic")
  public void sendToClient(Message message,@DestinationVariable String destination) throws Exception{
    System.out.println("Recive message");
//    Thread.sleep(2000);
    String text = new String((byte[]) message.getPayload());
    simpMessagingTemplate.convertAndSend("/topic/"+destination,text);
//    return text;
  }

  @MessageMapping("/string")
  /**
   * client.send('xxx')
   */
//  @SendTo("topic/test2")
  //http://stackoverflow.com/questions/25468624/spring4-0-websocket-issue
  public String hello2(Message message) throws Exception{
    System.out.println(message.getPayload().getClass());
    String test = new String((byte[]) message.getPayload());
    Thread.sleep(2000);
//    simpMessagingTemplate.convertAndSend("/topic/test2","Send From backend");
    return "This is message 2";
  }

  @SubscribeMapping("/{path}")
  /**
   * client.subscribe(xxx) 前缀为 application-destination-prefix
   * request-reply 模式,
   */
  public String subscribeTest(@DestinationVariable String path,Message message){
    System.out.println(message.getPayload().getClass());
    String test = new String((byte[]) message.getPayload());
//    simpMessagingTemplate.convertAndSend("/topic/test2","Send From backend");
    return test+"--- From backend";
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
