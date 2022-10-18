package com.employment.demo.test.service;

import com.employment.demo.test.model.EmployementRecords;

import java.util.List;

public interface IRecordService {

    List<EmployementRecords> getRecordsOfEmployee(Integer employeeId);
}
