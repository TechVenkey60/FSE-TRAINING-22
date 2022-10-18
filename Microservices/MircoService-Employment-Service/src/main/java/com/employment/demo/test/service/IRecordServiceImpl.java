package com.employment.demo.test.service;

import com.employment.demo.test.model.EmployementRecords;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class IRecordServiceImpl implements IRecordService {

    List<EmployementRecords> employementRecords = List.of(
            new EmployementRecords(100,"TCS","tcs1@gamil.com",200,2),
            new EmployementRecords(101,"CTS","cts1@gamil.com",201,3),
            new EmployementRecords(102,"Dell","dell@gamil.com",202,4),
            new EmployementRecords(103,"HP","hp@gamil.com",203,5),
            new EmployementRecords(104,"HCL","hcl@gamil.com",204,5),
            new EmployementRecords(105,"apple","applie@gamil.com",205,4),
            new EmployementRecords(106,"google","goggle@gamil.com",200,3)
    );

    @Override
    public List<EmployementRecords> getRecordsOfEmployee(Integer employeeId) {
        return employementRecords.stream()
                                 .filter(employementRecord -> employementRecord.getcEmployeeId().equals(employeeId))
                                 .collect(toList());
    }
}
