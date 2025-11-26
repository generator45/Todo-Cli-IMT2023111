package com.example.todo;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static void printMenu() {
        System.out.println("\n=== To-Do List CLI (Java) ===");
        System.out.println("1. Add task");
        System.out.println("2. List tasks");
        System.out.println("3. Mark task as done");
        System.out.println("4. Remove task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        ToDoList todoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter task description: ");
                    String desc = scanner.nextLine();
                    try {
                        todoList.addTask(desc);
                        System.out.println("Task added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "2" -> {
                    List<ToDoList.Task> tasks = todoList.listTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            ToDoList.Task t = tasks.get(i);
                            String status = t.isDone() ? "✓" : " ";
                            System.out.printf("%d. [%s] %s%n", i, status, t.getDescription());
                        }
                    }
                }
                case "3" -> {
                    System.out.print("Enter task index to mark done: ");
                    String idxStr = scanner.nextLine();
                    try {
                        int idx = Integer.parseInt(idxStr);
                        todoList.markDone(idx);
                        System.out.println("Task marked as done.");
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "4" -> {
                    System.out.print("Enter task index to remove: ");
                    String idxStr = scanner.nextLine();
                    try {
                        int idx = Integer.parseInt(idxStr);
                        todoList.removeTask(idx);
                        System.out.println("Task removed.");
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "5" -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}
