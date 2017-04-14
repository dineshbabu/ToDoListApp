package com.my.todo.service;


import com.my.todo.model.ToDoItem;

import java.util.List;

public interface ToDoItemService {

    public long createToDoItem(ToDoItem toDoItem);

    public long updateToDoItem(ToDoItem toDoItem);

    public int deleteToDoItem(ToDoItem toDoItem);

    public List<ToDoItem> getAllToDoItems();

    public List<ToDoItem> filterToDoItemsByStatus(int status);

    public List<ToDoItem> filterToDoItemsByPriority(int priority);

    public ToDoItem getToDOItem(int id);

}
