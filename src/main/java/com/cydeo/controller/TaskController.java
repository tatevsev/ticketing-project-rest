package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getTasks() {
        return ResponseEntity.ok(new ResponseWrapper("All the tasks are successfully retrieved", taskService.listAllTasks(), HttpStatus.OK ));
    }

    @GetMapping("{id}")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new ResponseWrapper("Task with id " + id + " is retrieved", taskService.findById(id), HttpStatus.OK ));
     }


     @PostMapping
     @RolesAllowed("Manager")
     public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO task) {
        taskService.save(task);
        return ResponseEntity.ok(new ResponseWrapper("New task is created",HttpStatus.CREATED));
     }


     @DeleteMapping("{id}")
     @RolesAllowed("Manager")
     public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.ok(new ResponseWrapper("Task with id " + id + " is deleted", HttpStatus.OK));
    }

    @PutMapping("{id}")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> updateTask(@PathVariable("id") Long id, @RequestBody TaskDTO task) {
        taskService.update(task);
        return ResponseEntity.ok(new ResponseWrapper("Task with id " + id + " is updated", HttpStatus.OK));

    }


    @GetMapping("/employee/pending-tasks")
    @RolesAllowed("Employee")
    public ResponseEntity<ResponseWrapper> employeePendingTasks(){
      return  ResponseEntity.ok( new ResponseWrapper("Pending tasks are retrieved", taskService.listAllTasksByStatusIsNot(Status.COMPLETE),HttpStatus.OK));
    }


    @PutMapping("/employee/update")
    @RolesAllowed("Employee")
    public ResponseEntity<ResponseWrapper> employeeUpdateTasks(@RequestBody TaskDTO task){
        taskService.update(task);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated", HttpStatus.OK));
    }


    @GetMapping("/employee/archive")
    @RolesAllowed("Employee")
    public ResponseEntity<ResponseWrapper> employeeArchivedTasks(){
        return ResponseEntity.ok(new ResponseWrapper("Retrieved archived tasks",taskService.listAllTasksByStatus(Status.COMPLETE),HttpStatus.OK));
    }




}
