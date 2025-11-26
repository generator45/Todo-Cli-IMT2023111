package com.example.todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    @Test
    void testAddTask() {
        ToDoList todo = new ToDoList();
        todo.addTask("Study Jenkins");
        assertEquals(1, todo.listTasks().size());
        assertEquals("Study Jenkins", todo.listTasks().get(0).getDescription());
        assertFalse(todo.listTasks().get(0).isDone());
    }

    @Test
    void testAddEmptyTaskThrows() {
        ToDoList todo = new ToDoList();
        assertThrows(IllegalArgumentException.class, () -> todo.addTask("  "));
    }

    @Test
    void testMarkDone() {
        ToDoList todo = new ToDoList();
        todo.addTask("Task A");
        todo.markDone(0);
        assertTrue(todo.listTasks().get(0).isDone());
    }

    @Test
    void testRemoveTask() {
        ToDoList todo = new ToDoList();
        todo.addTask("Task A");
        todo.addTask("Task B");
        todo.removeTask(0);
        assertEquals(1, todo.listTasks().size());
        assertEquals("Task B", todo.listTasks().get(0).getDescription());
    }

    @Test
    void testInvalidIndexThrows() {
        ToDoList todo = new ToDoList();
        todo.addTask("Task A");
        assertThrows(IndexOutOfBoundsException.class, () -> todo.markDone(5));
    }
}
