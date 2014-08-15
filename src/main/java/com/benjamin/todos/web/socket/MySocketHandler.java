package com.benjamin.todos.web.socket;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.*;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by benjamin on 8/15/14.
 */
public class MySocketHandler implements WebSocketHandler {
  private TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
  @Override
  public void afterConnectionEstablished(final WebSocketSession webSocketSession) throws Exception {
    String id = webSocketSession.getId();
    System.out.println("BinaryMessageSizeLimit : " + webSocketSession.getBinaryMessageSizeLimit());
    System.out.println("Uri : " + webSocketSession.getUri().toString());
    System.out.println("getAcceptedProtocol : "+webSocketSession.getAcceptedProtocol());
    System.out.println("TextMessageSizeLimit : " + webSocketSession.getTextMessageSizeLimit());
    System.out.println("Id : " + id);
    final int num = 0 ;
    taskScheduler.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        int t = num+1;
        try {
          webSocketSession.sendMessage(new TextMessage(t+""));
        }catch (Exception e){

        }
      }
    }, 2000);
//    System.out.println(id);
//    System.out.println(id);
  }

  @Override
  public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
    System.out.println("Message : " + webSocketMessage.getPayload());
    TextMessage message = new TextMessage("Backend");
    webSocketSession.sendMessage(message);
  }

  @Override
  public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

  }

  @Override
  public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }
}
