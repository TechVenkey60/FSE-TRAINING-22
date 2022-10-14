package com.demo.test;

public class Courses {
    private Integer courseId;
    private String courseName;
    private String courseTeacher;
    private Integer courseDuration;
    private Double totalFees;

    public Courses(Integer courseId, String courseName, String courseTeacher, Integer courseDuration, Double totalFees) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.courseDuration = courseDuration;
        this.totalFees = totalFees;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public Integer getCourseDuration() {
        return courseDuration;
    }

    public Double getTotalFees() {
        return totalFees;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseTeacher='" + courseTeacher + '\'' +
                ", courseDuration=" + courseDuration +
                ", totalFees=" + totalFees +
                '}';
    }
}
