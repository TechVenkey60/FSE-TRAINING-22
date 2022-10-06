package com.lamda.com.predicate.test;

import java.util.function.Predicate;

public class PredicateTest {


    //Printing my name length >
    public static Predicate<String> hasLengthOf10 = s -> s.length()>10;

    public static void conatinsV(){
        Predicate<String> conatinsVLetter = name -> name.contains("V");

        System.out.println(hasLengthOf10.or(conatinsVLetter).test("VenkateshKokkanti"));
    }

    public static void main(String[] args){

        //Create Predicate
        Predicate<Integer> isAnAdult = age -> age>18;

        Predicate<Integer> isRetire = i -> i>62;

        //Calling Prdicate test method to print adult & not adult.
//        System.out.println(isAnAdult.test(25));
//        System.out.println(isAnAdult.test(12));

        //Combine Preidcates

//        System.out.println(isAnAdult.and(isRetire).test(63));
//        System.out.println(isAnAdult.and(isRetire).test(43));


        conatinsV();


    }
}
