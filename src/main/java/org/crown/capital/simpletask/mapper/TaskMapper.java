package org.crown.capital.simpletask.mapper;

import org.crown.capital.simpletask.dto.TaskDTO;
import org.crown.capital.simpletask.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO entityToDto(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setStartDate(task.getStartDate());
        dto.setEndDate(task.getEndDate());
        dto.setStatus(task.getStatus());
        dto.setNote(task.getNote());
        return dto;
    }

    public Task dtoToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setStartDate(dto.getStartDate());
        task.setEndDate(dto.getEndDate());
        task.setStatus(dto.getStatus());
        task.setNote(dto.getNote());
        return task;
    }

}
