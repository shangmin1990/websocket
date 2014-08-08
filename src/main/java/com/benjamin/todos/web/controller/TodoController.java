package com.benjamin.todos.web.controller;

import com.benjamin.todos.entity.Todo;
import com.benjamin.todos.web.dao.impl.TodoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by benjamin on 7/29/14.
 */
@Controller
public class TodoController {
  @Autowired
  private TodoImpl todoImpl;
  @RequestMapping(value="/todo",method = RequestMethod.GET)
  public @ResponseBody List<Todo> findAll(){
    return todoImpl.getAllTodos();
  }
  @RequestMapping(value="/todo",method = RequestMethod.PUT)
  @ResponseBody
  public boolean addTodo(@RequestBody Todo todo){
    return todoImpl.addTodo(todo);
  }
  @RequestMapping(value="/todo/{id}",method = RequestMethod.DELETE)
  public @ResponseBody boolean deleteTodo(@PathVariable int id){
    return todoImpl.deleteTodo(id);
  }
  @RequestMapping(value="/todo",method = RequestMethod.DELETE)
  @ResponseBody
  public boolean clear(){
    return todoImpl.clearAll();
  }
}
