package com.my.todo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.todo.exception.InvalidInputException;
import com.my.todo.model.ToDoItem;
import com.my.todo.service.ToDoItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todo")
public class ToDoListControllerImpl implements ToDoListController{

    @Autowired
    private ToDoItemServiceImpl toDoListServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createToDoItem(@RequestBody ToDoItem toDoItem) throws InvalidInputException {
        if(!isValidToDoItem(toDoItem, null)){
            return ResponseEntity.badRequest().body("Invalid ToDoItem");
        }
        return ResponseEntity.ok(String.valueOf(toDoListServiceImpl.createToDoItem(toDoItem)));
    }

    @Override
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateToDoItem(@RequestBody ToDoItem toDoItem, @PathVariable("id") long id) throws InvalidInputException {
        if(!isValidToDoItem(toDoItem, id)){
            return ResponseEntity.badRequest().body("Invalid ToDoItem");
        }
        return ResponseEntity.ok(String.valueOf(toDoListServiceImpl.updateToDoItem(toDoItem)));
    }

    @Override
    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String listAllToDoItems() throws JsonProcessingException {
        List<ToDoItem> toDoItems = toDoListServiceImpl.getAllToDoItems();
        return objectMapper.writeValueAsString(toDoItems);
    }

    @Override
    @RequestMapping(path = "/list/status/{statusid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String filterToDoItemsByStatus(@PathVariable("statusid") int statusId) throws JsonProcessingException {
        List<ToDoItem> toDoItems = toDoListServiceImpl.filterToDoItemsByStatus(statusId);
        return objectMapper.writeValueAsString(toDoItems);
    }

    @Override
    @RequestMapping(path = "/list/priority/{priorityid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String filterToDoItemsByPriority(@PathVariable("priorityid") int priorityId) throws JsonProcessingException {
        List<ToDoItem> toDoItems = toDoListServiceImpl.filterToDoItemsByPriority(priorityId);
        return objectMapper.writeValueAsString(toDoItems);
    }

    @Override
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getToDoItem(@PathVariable("id") long id) throws JsonProcessingException {
        ToDoItem toDoItem = toDoListServiceImpl.getToDOItem(id);
        return objectMapper.writeValueAsString(toDoItem);
    }

    private boolean isValidToDoItem(ToDoItem toDoItem, Long id) throws InvalidInputException {
        if(toDoItem.getName() == null){
            return false;
        }
        if( id != null && toDoItem.getId() != id){
            return false;
        }
        return true;
    }
}
