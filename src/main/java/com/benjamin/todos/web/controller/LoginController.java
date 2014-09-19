package com.benjamin.todos.web.controller;

import com.benjamin.oauth2.authorization.PasswordValidator;
import com.benjamin.oauth2.util.PropertiesUtil;
import com.benjamin.todos.web.oauth2.MyOauth2Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Created by benjamin on 9/16/14.
 */
@Controller
public class LoginController {

  private PasswordValidator passwordValidator =  new MyOauth2Login();

  @RequestMapping("/login")
  public String login(@RequestParam(required = false) String code, @RequestParam(value = "state", required = false) String state,  RedirectAttributes redirectAttributes, @CookieValue(value = "user",required = false) String userIdentity, HttpServletRequest request, @RequestParam(required = false) String username){
    //oauth认证,将请求转发
    if(code != null){
      if(userIdentity == null){
        userIdentity = username;
        if(userIdentity == null){
          //先要登录
          return "forward:/loginPage.jsp";
        }
      }
        //获取令牌
      request.setAttribute("user", userIdentity);
      return "forward:/oauth2/access_token";
    }
    return null;
  }
//  @RequestMapping("/login")
//  public String login(String username,String password,HttpServletResponse response){
//    boolean result = passwordValidator.validPassword(username, password);
//    if(result){
//      Cookie cookie = new Cookie(PropertiesUtil.getString("user-cookie-name","user"),username);
//      response.addCookie(cookie);
//      return "redirect:/index.html";
//    }else{
//      return "forward:/loginPage.jsp";
//    }
//  }
}
