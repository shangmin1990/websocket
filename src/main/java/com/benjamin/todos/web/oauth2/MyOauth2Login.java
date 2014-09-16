package com.benjamin.todos.web.oauth2;

import com.benjamin.oauth2.authorization.PasswordValidator;

/**
 * Created by benjamin on 9/10/14.
 */
public class MyOauth2Login implements PasswordValidator {
  @Override
  public boolean validPassword(String username, String password) {
    if(username!=null && password !=null && username.equals("test") && password.equals("test")){
      return true;
    }else {
      return false;
    }
  }
}
