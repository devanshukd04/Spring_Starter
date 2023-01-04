package com.example.demo.controllers;

import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){

        List<Employee> list=this.employeeService.getAllEmployee();
        if(list.size()<1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployees(@PathVariable("id") int id){

        Employee e=this.employeeService.getEmployee(id);
        if(e==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(e));
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployees(@RequestBody Employee employee){
        Employee e=null;
        try {
            e = this.employeeService.addEmployee(employee);
            if (e == null) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.of(Optional.of(e));
        }
        catch(Exception error) {
            error.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployees(@PathVariable("id") int id){
        try {
            this.employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployees(@PathVariable("id") int id,@RequestBody Employee employee){
        try {
            this.employeeService.updateEmployee(employee,id);
            return ResponseEntity.ok().body(employee);
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
