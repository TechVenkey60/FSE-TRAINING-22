package com.employee.ms.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private Integer id;
    private String name;
    private String phone;

    private List<EmployementRecords> employementRecords = new ArrayList<>();

    public Employee() {
    }

    public Employee(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Employee(Integer id, String name, String phone, List<EmployementRecords> employementRecords) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.employementRecords = employementRecords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<EmployementRecords> getEmployementRecords() {
        return employementRecords;
    }

    public void setEmployementRecords(List<EmployementRecords> employementRecords) {
        this.employementRecords = employementRecords;
    }
}
