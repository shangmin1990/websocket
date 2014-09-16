package com.benjamin.todos.web.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by benjamin on 9/9/14.
 */
@Controller
@RequestMapping("/api")
public class OauthTest {
  @RequestMapping("test")
  public @ResponseBody String test(){
    return "Oauth2 Hello World";
  }
}
