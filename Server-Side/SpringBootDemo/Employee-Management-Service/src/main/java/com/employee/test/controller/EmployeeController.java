package com.employee.test.controller;

import com.employee.test.entity.Employee;
import com.employee.test.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/remove/{employeeId}")
    public ResponseEntity<?> removeEmployeeById(@PathVariable Integer employeeId){
        var response = new  ResponseEntity<Employee>(HttpStatus.GONE);
        try {
            employeeService.deleteEmployeeById(employeeId);
        }catch (Exception ex){
            ex.getMessage();
            return ResponseEntity.notFound().build();
        }
        return response;
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(employee,employeeId),HttpStatus.OK);
    }
}
