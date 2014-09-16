package com.benjamin.todos.web.controller;

import com.benjamin.oauth2.authorization.PasswordValidator;
import com.benjamin.oauth2.util.PropertiesUtil;
import com.benjamin.todos.web.oauth2.MyOauth2Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Created by benjamin on 9/16/14.
 */
@Controller
public class LoginController {

  private PasswordValidator passwordValidator =  new MyOauth2Login();

  @RequestMapping("/login/oauth2")
  public String login(@RequestParam String code,@RequestParam String client_id, @RequestParam String state,  RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("code",code);
    redirectAttributes.addAttribute("state", state);
    redirectAttributes.addAttribute("client_id",client_id);
    redirectAttributes.addAttribute("redirect_uri", "http://localhost:81/index.html");
    redirectAttributes.addAttribute("grant_type", "authorization_code");
    return "redirect:/oauth2/access_token";
  }
  @RequestMapping("/login")
  public String login(String username,String password,HttpServletResponse response){
    boolean result = passwordValidator.validPassword(username, password);
    if(result){
      Cookie cookie = new Cookie(PropertiesUtil.getString("user-cookie-name","user"),username);
      response.addCookie(cookie);
      return "redirect:/index.html";
    }else{
      return "redirect:/loginPage.html";
    }
  }
}
