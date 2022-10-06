package com.supplier.test;

import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args){
        //Supplier doesn't take any argument. Returns generic obj
        Supplier<Double> value = () -> Math.random();

        System.out.println(value.get());
    }
}
