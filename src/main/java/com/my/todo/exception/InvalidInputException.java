package com.my.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Invalid ToDoItem")
public class InvalidInputException extends Throwable {
    public InvalidInputException(String s) {
        super(s);
    }
}
