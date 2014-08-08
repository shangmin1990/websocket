package com.benjamin.todos.web.dao;

import com.benjamin.todos.entity.Todo;

import java.util.List;

/**
 * Created by benjamin on 7/29/14.
 */
public interface ITodo {
  /**
   * getAll todos
   */
  List<Todo> getAllTodos();

  boolean deleteTodo(int id);

  boolean addTodo(Todo todo);

  boolean modifyTodo(Todo todo);

  boolean clearAll();
}
