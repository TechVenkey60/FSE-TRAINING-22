package com.employee.ms.demo.model;

public class EmployementRecords {

    private Integer companyId;
    private String companyName;
    private String email;
    private String cEmployeeId;
    private Integer experienceInYears;

    public EmployementRecords() {
    }

    public EmployementRecords(Integer companyId, String companyName, String email, String cEmployeeId, Integer experienceInYears) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.email = email;
        this.cEmployeeId = cEmployeeId;
        this.experienceInYears = experienceInYears;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getcEmployeeId() {
        return cEmployeeId;
    }

    public void setcEmployeeId(String cEmployeeId) {
        this.cEmployeeId = cEmployeeId;
    }

    public Integer getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(Integer experienceInYears) {
        this.experienceInYears = experienceInYears;
    }
}
