package com.my.todo.helper;

public enum Status {
    COMPLETE("Complete", 1),
    IN_PROGRESS("In progress", 2),
    PENDING("Pending", 3);

    private final String statusDisplayText;
    private int statusValue;

    Status(String statusDisplayText, int statusValue){
        this.statusDisplayText = statusDisplayText;
        this.statusValue = statusValue;

    }

    public String getStatusDisplayText() {
        return statusDisplayText;
    }

    public int getStatusValue() {
        return statusValue;
    }
}
