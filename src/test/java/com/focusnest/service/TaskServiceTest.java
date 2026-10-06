package com.focusnest.service;

import com.focusnest.exception.TaskNotFoundException;
import com.focusnest.repository.TaskRepository;
import com.focusnest.model.Task;
import com.focusnest.model.TaskStatus;
import com.focusnest.model.TaskPriority;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnTaskWhenTaskExists() {
        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));
        Task result = taskService.getTaskByID(1L);

        assertEquals(task, result);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {
        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getTaskByID(1L)
        );
    }

    @Test
    void shouldCreateTask() {
        Task task = new Task();
        task.setTitle("New task");

        when(taskRepository.save(task))
                .thenReturn(task);

        Task result = taskService.createTask(task);

        assertEquals(task, result);
        verify(taskRepository).save(task);

    }

    @Test
    void shouldReturnAllTasks() {
        Task task1 = new Task();
        task1.setTitle("Task 1");

        Task task2 = new Task();
        task2.setTitle("Task 2");

        when(taskRepository.findAll())
                .thenReturn(List.of(task1, task2));

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        assertEquals(task1, result.get(0));
        assertEquals(task2, result.get(1));

        verify(taskRepository).findAll();
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository).delete(task);
    }

    @Test
    void shouldUpdateTask() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setTitle("Old title");

        Task updatedTask = new Task();
        updatedTask.setTitle("New title");
        updatedTask.setDescription("New description");
        updatedTask.setStatus(TaskStatus.IN_PROGRESS);
        updatedTask.setPriority(TaskPriority.HIGH);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(existingTask));

        when(taskRepository.save(existingTask))
                .thenReturn(existingTask);

        Task result = taskService.updateTask(1L, updatedTask);

        assertEquals("New title", result.getTitle());
        assertEquals("New description", result.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, result.getStatus());
        assertEquals(TaskPriority.HIGH, result.getPriority());

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(existingTask);
    }


    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingTask() {
       when(taskRepository.findById(1L))
               .thenReturn(Optional.empty());

       assertThrows(
               TaskNotFoundException.class,
               () -> taskService.updateTask(1L, new Task())
       );

       verify(taskRepository).findById(1L);
       verify(taskRepository, never()).save(any());
    }
}
