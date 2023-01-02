package com.example.demo.services;

import com.example.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private List<Employee> list=new ArrayList<>();

    public List<Employee> getAllEmployee(){
        return list;
    }

    public Employee getEmployee(int id){
        Employee employee=null;
        employee=list.stream().filter(e->e.getId()==id).findFirst().get();
        return employee;
    }

    public Employee addEmployee(Employee employee){
        list.add(employee);
        return employee;
    }

    public void deleteEmployee(int id){
        list=list.stream().filter(employee->{
            if(employee.getId()!=id){
                return true;
            }
            else{
                return false;
            }
        }).collect(Collectors.toList());
    }

    public void updateEmployee(Employee employee,int id){
        list=list.stream().map(e->{
            if(e.getId()==id){
                e.setDepartment(employee.getDepartment());
                e.setName(employee.getName());
                e.setSalary(employee.getSalary());
                e.setPosition((employee.getPosition()));
            }
            return e;
        }).collect(Collectors.toList());
    }
}
