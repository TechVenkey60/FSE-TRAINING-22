package com.consumer.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args){

        //Consumer
        Consumer<Integer> display = value -> System.out.println(value);

        //call accept method using display ref.
//        display.accept(23);
//        display.accept(886);


        //Display list int values using consumer function.
        Consumer<List<Integer>> displayIntegerValues = values -> values.stream()
                                                                        .forEach(System.out::println);

        List<Integer> values = Arrays.asList(1,2,3,4,55,7,6);

        displayIntegerValues.accept(values);

    }
}
