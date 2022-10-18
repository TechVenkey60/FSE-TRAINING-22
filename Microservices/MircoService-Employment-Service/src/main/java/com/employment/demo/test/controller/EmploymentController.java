package com.employment.demo.test.controller;


import com.employment.demo.test.model.EmployementRecords;
import com.employment.demo.test.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmploymentController {

    @Autowired
    private IRecordService recordService;

    @GetMapping("/records/{employeeId}")
    public List<EmployementRecords> getEmployeeRecords(@PathVariable Integer employeeId){
        return  recordService.getRecordsOfEmployee(employeeId);
    }
 }
