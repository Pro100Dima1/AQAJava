package org.expressJava.task12.taskManager;

public class Task<T extends Number> {
    private String status;
    private String data;
    private T id;
    private int priority;

    public Task(String status, String data, T id, int priority) {
        this.status = status;
        this.data = data;
        this.id = id;
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public T getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }
}
