package com.works.todolist.controller;

import com.works.todolist.model.Task;
import com.works.todolist.repository.TaskRepository;
import com.works.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("task", new Task());
        return "index";
    }

    @PostMapping("/task")
    public String addTask(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/tasks")
    public String listTasks(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 2; // Number of tasks per page
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Task> taskPage = taskRepository.findAll(pageable);

        model.addAttribute("taskPage", taskPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("task", new Task()); // Ensure task object is added to the model
        return "tasks";
    }
}
