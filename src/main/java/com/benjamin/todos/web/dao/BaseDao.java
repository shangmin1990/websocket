package com.benjamin.todos.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by benjamin on 7/29/14.
 */
public class BaseDao {
  private static String username = "todo";
  private static String password = "benjamin";
  private static String url = "jdbc:mysql://shmin.net:3306/todos";

  protected Connection getConnection() throws Exception{
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection(url,username,password);
    return connection;
  }
  protected void clear(Connection connection,Statement statement,ResultSet resultSet) throws Exception{
    resultSet.close();
    statement.close();
    connection.close();
  }
}
