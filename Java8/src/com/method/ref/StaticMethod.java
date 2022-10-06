package com.method.ref;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class UserData {

    private String name;
    private Integer age;

    public UserData(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}


public class StaticMethod{
    public static int compareByAge(UserData userData, UserData data){
        return userData.getAge().compareTo(data.getAge());
    }

    public static int compareByName(UserData userData, UserData data){
        return userData.getName().compareTo(data.getName());
    }
    public static void main(String[] args){

        List<UserData> users = Arrays.asList(new UserData("Venkat",23),
                                            new UserData("Suman",65),
                                            new UserData("Bala",3));


        System.out.println("Sort By age and print names");

        Collections.sort(users,StaticMethod::compareByAge);

        users.stream()
                .map(UserData::getName)
                .forEach(System.out::println);


        System.out.println("Sort By Name and print names");

        Collections.sort(users,StaticMethod::compareByName);
        users.stream()
                .sorted(Comparator.comparing(UserData::getName))
                .map(UserData::getName)
                .forEach(System.out::println);



    }
}