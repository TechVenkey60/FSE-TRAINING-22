package com.consumer.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerWithAndThen {
    public static void main(String[] args){

        //Combine 2 consumers using andThen()
        Consumer<List<Integer>> modify = list ->{
            for(int i = 0; i<list.size();i++){
              list.set(i,list.get(i)*10);
            }
        };

          //Display list int values using consumer function.
        Consumer<List<Integer>> displayIntegerValues = values -> values.stream()
                                                                        .forEach(System.out::println);

        List<Integer> values = Arrays.asList(1,2,3,4,55,7,6);

        displayIntegerValues.accept(values);


        modify.andThen(displayIntegerValues).accept(values);



    }
}
