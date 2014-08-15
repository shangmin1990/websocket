package com.benjamin.todos.web.socket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by benjamin on 8/15/14.
 */
public class MyRegistHandler extends TextWebSocketHandler {
  private ConcurrentHashMap<String,WebSocketSession> hashMap = new ConcurrentHashMap<String, WebSocketSession>();
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    super.afterConnectionEstablished(session);
    hashMap.put(session.getId(),session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    if(hashMap.contains(session.getId())){
      hashMap.remove(session.getId());
    }
    super.afterConnectionClosed(session, status);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    System.out.println(session.getId());
    System.out.println(message.getPayload());
    TextMessage textMessage = new TextMessage("fdasfads");
    publishMessage(session, textMessage);
    super.handleTextMessage(session, message);
  }

  /**
   * How to publish Message
   * @param webSocketMessage
   * @throws Exception
   */
  public void publishMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception{
    for(WebSocketSession socketSession : hashMap.values()){
//      if(webSocketSession.getId().equals(socketSession.getId())){
//        continue;
//      }
      if(socketSession.isOpen()){
        socketSession.sendMessage(webSocketMessage);
      }else{
       System.out.println(socketSession.getId() + "WebSocketSession has disconnetd");
      }
    }
  }
}
