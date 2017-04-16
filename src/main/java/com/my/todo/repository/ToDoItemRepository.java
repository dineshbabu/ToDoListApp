package com.my.todo.repository;

import com.my.todo.model.ToDoItem;

import java.util.List;

public interface ToDoItemRepository {

    public long createToDoItem(ToDoItem toDoItem);

    public long updateToDoItem(ToDoItem toDoItem);

    public List<ToDoItem> getAllToDoItems();

    public ToDoItem getToDOItem(long id);

    List<ToDoItem> filterToDoItemsByStatus(int status);

    List<ToDoItem> filterToDoItemsByPriority(int priority);

}
