package com.example.todo;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    public static class Task {
        private String description;
        private boolean done;

        public Task(String description) {
            this.description = description;
            this.done = false;
        }

        public String getDescription() {
            return description;
        }

        public boolean isDone() {
            return done;
        }

        public void markDone() {
            this.done = true;
        }
    }

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        tasks.add(new Task(description.trim()));
    }

    public List<Task> listTasks() {
        return tasks;
    }

    public void markDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
        tasks.get(index).markDone();
    }

    public void removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index");
        }
        tasks.remove(index);
    }
}
