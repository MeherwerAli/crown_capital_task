package org.crown.capital.simpletask.service;

import org.crown.capital.simpletask.dto.TaskDTO;
import org.crown.capital.simpletask.enums.TaskStatus;
import org.crown.capital.simpletask.mapper.TaskMapper;
import org.crown.capital.simpletask.model.Task;
import org.crown.capital.simpletask.repository.TaskRepository;
import org.crown.capital.simpletask.util.exceptions.CannotCreateException;
import org.crown.capital.simpletask.util.exceptions.CannotUpdateException;
import org.crown.capital.simpletask.util.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

//    @Override
    public TaskDTO findTaskById(Long id) throws NotFoundException {
        return taskRepository.findById(id)
                .map(taskMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found"));
    }

//    @Override
    public Iterable<TaskDTO> findAllTasks() {

        List<TaskDTO> taskList = new ArrayList<>();

        taskRepository.findAllByIsDeletedFalse().forEach(task -> taskList.add(taskMapper.entityToDto(task)));

        if (!taskList.isEmpty()) {
            logger.info("Task List Size: " + taskList.size());
        } else {
            taskList.add(new TaskDTO(Long.valueOf("123"), "testTask1", new Date(), new Date(), TaskStatus.CREATED, "This is Test Task"));
            taskList.add(new TaskDTO(Long.valueOf("456"), "testTask2", new Date(), new Date(), TaskStatus.IN_PROGRESS, "This is Test Task"));
            taskList.add(new TaskDTO(Long.valueOf("789"), "testTask3", new Date(), new Date(), TaskStatus.FINISHED, "This is Test Task"));
        }

        return taskList;
    }

//    @Override
    public TaskDTO saveTask(TaskDTO taskDto) throws CannotCreateException {
        try {
            Task savedTask = taskRepository.save(taskMapper.dtoToEntity(taskDto));
            return taskMapper.entityToDto(savedTask);
        } catch (Exception ex) {
            throw new CannotCreateException("Failed to create task", ex);
        }
    }

//    @Override
    public TaskDTO updateTask(TaskDTO taskDto) throws NotFoundException, CannotUpdateException {
        if (taskDto.getId() == null || !taskRepository.existsById(taskDto.getId())) {
            throw new NotFoundException("Task with id " + taskDto.getId() + " not found");
        }

        try {
            Task updatedTask = taskRepository.save(taskMapper.dtoToEntity(taskDto));
            return taskMapper.entityToDto(updatedTask);
        } catch (Exception ex) {
            throw new CannotUpdateException("Failed to update task", ex);
        }
    }

//    @Override
    public void deleteTask(Long id) throws NotFoundException {
        if (id == null || !taskRepository.existsById(id)) {
            throw new NotFoundException("Task with id " + id + " not found");
        }
        try {
            Task taskToDelete = taskRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found"));
            taskToDelete.setDeleted(true);
            taskRepository.save(taskToDelete);
        } catch (Exception ex) {
            throw new CannotUpdateException("Failed to update task", ex);
        }
    }

}
