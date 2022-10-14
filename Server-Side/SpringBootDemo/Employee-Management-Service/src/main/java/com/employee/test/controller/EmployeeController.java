package com.employee.test.controller;

import com.employee.test.entity.Employee;
import com.employee.test.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/hello")
    public String helloWorld(){
        return  "Welcome to Employee Management Service";
    }

    @PostMapping("/saveEmployee")
    public Employee createEmployee(@RequestBody Employee employee){
        var employeeData = employeeService.saveEmployee(employee);
        System.out.println("Employee is saved and generated Id : "+employeeData.getEmployeeId());
        return employeeData;
    }


    @GetMapping("/getEmployees")
    public List<Employee> fetchAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployee/{employeeId}")
    public Employee fetchAllEmployeeById(@PathVariable Integer employeeId){
        return employeeService.getEmployeeById(employeeId).get();
    }
}
