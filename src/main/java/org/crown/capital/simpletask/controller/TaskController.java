package org.crown.capital.simpletask.controller;

import org.crown.capital.simpletask.dto.TaskDTO;
import org.crown.capital.simpletask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/list_task")
    public String listTask(Model model) {
        Iterable<TaskDTO> taskList = taskService.findAllTasks();

        model.addAttribute("tasks", taskList);

        return "all-tasks";
    }

    @GetMapping("/create_task")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new TaskDTO());
        return "create-task"; // This JSP will show the form to create a task.
    }

    @PostMapping("/create_task")
    public String handleTaskSubmission(@Valid @ModelAttribute("task") TaskDTO task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid input data");
            return "create-task";
        }

        taskService.saveTask(task); // Assuming you have a method in your service to save the task.
        return "redirect:/list_task"; // Redirect to the list of tasks after successful creation.
    }

    @GetMapping("/update_task/{id}")
    public String showUpdateTaskForm(@PathVariable("id") Long taskId, Model model) {
        TaskDTO existingTask = taskService.findTaskById(taskId); // Assuming you have a method in your service to fetch a task by its ID.

        if (existingTask == null) {
            model.addAttribute("errorMessage", "Task not found");
            return "error-page"; // You can redirect to a custom error page or the task list page with an error message.
        }

        model.addAttribute("task", existingTask);
        return "update-task"; // This JSP will show the form to update a task.
    }

    @PostMapping("/update_task")
    public String handleTaskUpdate(@Valid @ModelAttribute("task") TaskDTO task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid input data");
            return "update-task";
        }

        taskService.updateTask(task); // Assuming you have a method in your service to update the task.
        return "redirect:/list_task"; // Redirect to the list of tasks after successful update.
    }

    @GetMapping("/delete_task/{id}")
    public String deleteTask(@PathVariable("id") Long taskId, RedirectAttributes redirectAttributes) {
        try {
            taskService.deleteTask(taskId); // Assuming you have a method in your service to delete a task by its ID.
            redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting task. Please try again.");
        }
        return "redirect:/list_task"; // Redirect to the list of tasks after attempting deletion.
    }
}