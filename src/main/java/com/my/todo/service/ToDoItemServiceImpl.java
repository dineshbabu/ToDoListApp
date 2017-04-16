package com.my.todo.service;

import com.my.todo.model.ToDoItem;
import com.my.todo.repository.ToDoItemRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoItemServiceImpl implements ToDoItemService {

    @Autowired
    private ToDoItemRepositoryImpl toDoItemRepositoryImpl;

    @Override
    public long createToDoItem(ToDoItem toDoItem) {
        return toDoItemRepositoryImpl.createToDoItem(toDoItem);
    }

    @Override
    public long updateToDoItem(ToDoItem toDoItem) {
        return toDoItemRepositoryImpl.updateToDoItem(toDoItem);
    }

    @Override
    public List<ToDoItem> getAllToDoItems() {
        return toDoItemRepositoryImpl.getAllToDoItems();
    }

    @Override
    public List<ToDoItem> filterToDoItemsByStatus(int status) {
        return toDoItemRepositoryImpl.filterToDoItemsByStatus(status);
    }

    @Override
    public List<ToDoItem> filterToDoItemsByPriority(int priority) {
        return toDoItemRepositoryImpl.filterToDoItemsByPriority(priority);
    }

    @Override
    public ToDoItem getToDOItem(long id) {
        return toDoItemRepositoryImpl.getToDOItem(id);
    }

}
