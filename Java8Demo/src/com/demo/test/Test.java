package com.demo.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

public class Test {
    public static void main(String[] args){

        List<Courses> courseList = Arrays.asList(
                new Courses(100,"SSC CGL","Venkat",6,45000.0),
                new Courses(101,"SSC CHSL","Rohita",12,55000.0),
                new Courses(102,"JEE Advanced","suman",8,35000.0),
                new Courses(103,"NEET","Naga",12,40000.0),
                new Courses(104,"RRB NTPC","Nishant",6,35000.0),
                new Courses(105,"BITSAT","Jagan",6,35000.0),
                new Courses(106,"IBPS Clerk","Haritha",8,74000.0),
                new Courses(107,"SBI CLERK","Sai",10,25000.0),
                new Courses(108,"SSC CPO","Gani",5,35000.0),
                new Courses(109,"SSC MTS","Ayub",7,66000.0),
                new Courses(110,"JEE MAIN","Mallesh",6,45000.0),
                new Courses(111,"IBPS RRB","John",12,760000.0),
                new Courses(112,"GMAT","Maxwell",8,47000.0),
                new Courses(113,"LIC AAO","Sasi",9,23000.0),
                new Courses(114,"XAT","Mohan",10,41000.0),
                new Courses(115,"GRE","Chrish",12,24000.0)
        );


       // displayCoursesByFeesInASC(courseList);

       displayCoursesByFeesInDESCOrder(courseList);

        //dispalyCoursesByDurationInASCOrder(courseList);
    }

    private static void dispalyCoursesByDurationInASCOrder(List<Courses> courseList) {
        System.out.println("Display course details based on Duration from shortest to longest");
        courseList.stream()
                .sorted(comparing(Courses::getCourseDuration))
                .forEach(System.out::println);
    }

    private static void displayCoursesByFeesInDESCOrder(List<Courses> courseList) {
        System.out.println("Display course details based on fees in DESC order");

        courseList.stream()
                .sorted(comparing(Courses::getTotalFees).reversed())
                .forEach(System.out::println);


        Collections.sort(courseList, (c1,c2) -> c1.getTotalFees().compareTo(c2.getTotalFees()) );

    }

    private static void displayCoursesByFeesInASC(List<Courses> courseList) {
        System.out.println("Display course details based on fees in ASC order");

        courseList.stream()
                .sorted(comparing(Courses::getTotalFees))
                .forEach(System.out::println);
    }
}
