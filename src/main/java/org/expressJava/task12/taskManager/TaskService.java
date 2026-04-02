package org.expressJava.task12.taskManager;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskService<T extends Number> {
    Map<T, Task<T>> tasks = new HashMap<>();

    public synchronized void addTask(Task<T> task) {
        if(tasks.putIfAbsent(task.getId(), task) != null){
            throw new IllegalStateException("Task already exists");
        }
    }

    public synchronized void deleteTask(T id) {
        if(tasks.remove(id) == null){
            throw new IllegalStateException("Task not found");
        }
    }

    public synchronized List<Task<T>> searchTaskByStatus(String status) {
        return tasks.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted((Task<T> x, Task<T>y)-> x.getData().compareTo(y.getData()))
                .collect(Collectors.toList());
    }

    public synchronized List<Task<T>> searchTaskByPriority(int priority) {
        return tasks.values().stream()
                .filter(t -> t.getPriority() == priority)
                .sorted(Comparator.comparing(Task::getData))
                .collect(Collectors.toList());
    }
}
