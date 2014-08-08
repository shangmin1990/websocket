package com.benjamin.todos.web.dao.impl;

import com.benjamin.todos.entity.Todo;
import com.benjamin.todos.web.dao.BaseDao;
import com.benjamin.todos.web.dao.ITodo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 7/29/14.
 */
@Repository
public class TodoImpl extends BaseDao implements ITodo{
  @Override
  public List<Todo> getAllTodos() {
    String sql = "select id,title,completed,user,insert_time from todos";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    List<Todo> todos = new ArrayList<Todo>();
    try {
      connection = getConnection();
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()){
        Todo todo = new Todo();
        todo.setId(resultSet.getInt("id"));
        todo.setCompleted(resultSet.getBoolean("completed"));
        todo.setTitle(resultSet.getString("title"));
        todo.setUser(resultSet.getString("user"));
        todo.setTimestamp(resultSet.getTimestamp("insert_time"));
        todos.add(todo);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        clear(connection,statement,resultSet);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return todos;
  }

  @Override
  public boolean deleteTodo(int id) {
    Connection connection = null;
    Statement statement = null;
    try {
      connection = getConnection();
      statement = connection.prepareStatement("delete from todos where id = ?");
      ((PreparedStatement)statement).setInt(1,id);
      return ((PreparedStatement) statement).execute();
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      try {
        clear(connection,statement,null);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  @Override
  public boolean addTodo(Todo todo) {
    Connection connection = null;
    Statement statement = null;
    try {
      connection = getConnection();
      statement = connection.prepareStatement("insert into todos(title,completed,user,insert_time) values(?,?,?,?)");
      ((PreparedStatement)statement).setString(1, todo.getTitle());
      ((PreparedStatement)statement).setBoolean(2, todo.isCompleted());
      ((PreparedStatement)statement).setString(3, todo.getUser());
      ((PreparedStatement)statement).setTimestamp(4, new Timestamp(System.currentTimeMillis()));
      return ((PreparedStatement) statement).execute();
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      try {
        clear(connection,statement,null);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  @Override
  public boolean modifyTodo(Todo todo) {
    return false;
  }

  @Override
  public boolean clearAll() {
    Connection connection = null;
    Statement statement = null;
    try {
      connection = getConnection();
      statement = connection.createStatement();
      return statement.execute("delete from todos");
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      try {
        clear(connection,statement,null);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }
}
