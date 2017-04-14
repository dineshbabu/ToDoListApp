package com.my.todo.helper;


public enum Priority {

    HIGH("High", 1),
    MEDIUM("Medium", 2),
    LOW("Low", 3);

    private String priorityDisplayText;
    private int priorityValue;

    Priority(String priorityDisplayText, int priorityLevel) {
        this.priorityDisplayText = priorityDisplayText;
        this.priorityValue = priorityLevel;
    }


    public int getPriorityValue() {
        return priorityValue;
    }

    public String getPriorityDisplayText() {
        return priorityDisplayText;
    }
}
