package com.my.todo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.todo.exception.InvalidInputException;
import com.my.todo.model.ToDoItem;
import org.springframework.http.ResponseEntity;

public interface ToDoListController {

    public ResponseEntity<String> createToDoItem(ToDoItem toDoItemName) throws InvalidInputException;

    public ResponseEntity<String> updateToDoItem(ToDoItem toDoItemName, long id) throws InvalidInputException;

    public String listAllToDoItems() throws JsonProcessingException;

    public String filterToDoItemsByStatus(int statusId) throws JsonProcessingException;

    public String filterToDoItemsByPriority(int priorityId) throws JsonProcessingException;

    public String getToDoItem(long id) throws JsonProcessingException;

}
