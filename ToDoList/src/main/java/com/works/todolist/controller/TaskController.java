package com.works.todolist.controller;

import com.works.todolist.model.Task;
import com.works.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("task", new Task());
        return "index";
    }

    @PostMapping("/task")
    public String addTask(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
    @GetMapping("/tasks")
    @ResponseBody
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
