package com.lamda;


import java.util.Arrays;
import java.util.List;

public class PredicateTestWithObj {

    public static void main(String[] args) {

        List<User> users = Arrays.asList(
                new User("Venkey", "SE"),
                new User("Suman", "SSE"),
                new User("Gani", "admin"),
                new User("Bala", "admin"),
                new User("Suresh", "admin"),
                new User("Nag", "Lead"));

        //print admin role user if exist in list
        users.stream()
              .filter(user -> user.getRole().equals("admin"))
              .forEach(System.out::println);

    }
}
