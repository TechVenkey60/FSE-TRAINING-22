package com.util.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilTest {
    MathUtil mathUtil = new MathUtil();
    @Test
    void add() {
        System.out.println("Test cae ran");
    }

    @Test
    void testAdd(){
        assertEquals(20,mathUtil.add(10,10));
    }

    @Test
    void testDifference(){
        assertEquals(0,mathUtil.difference(10,10));
    }

    @Test
    void testMultiply(){
        assertEquals(100,mathUtil.multiply(10,10));
    }

    //AssertArrayEquals(exAr,acArr)
    //AssertIteratableEquls(exAr,acArr)


    @Test
    void testArrayOfValues(){
       String[] names = new String[1];
       names[0] = "Venkat";
    }









}