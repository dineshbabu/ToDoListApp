package com.my.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.todo.helper.Priority;
import com.my.todo.helper.Status;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ToDoItem {

    private long id;

    private String name;

    private int priority;

    private String priorityText;

    private int status;

    private String statusText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPriorityText() {
        return Arrays.stream(Priority.values())
                .filter(priority1 -> priority1.getPriorityValue() == priority)
                .map(priority1 -> priority1.getPriorityDisplayText())
                .reduce("",String::concat);
    }

    public void setPriorityText(String priorityText) {
        this.priorityText = priorityText;
    }

    public String getStatusText() {
        return Arrays.stream(Status.values())
                .filter(status1 -> status1.getStatusValue() == status)
                .map(status -> status.getStatusDisplayText())
                .reduce("",String::concat);
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
