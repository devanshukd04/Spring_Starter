package com.example.demo.controllers;

import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return this.employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployees(@PathVariable("id") int id){
        return this.employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    public Employee addEmployees(@RequestBody Employee employee){
        Employee e=this.employeeService.addEmployee(employee);
        return e;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployees(@PathVariable("id") int id){
        this.employeeService.deleteEmployee(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployees(@PathVariable("id") int id,@RequestBody Employee employee){
        this.employeeService.updateEmployee(employee,id);
        return employee;
    }
}
