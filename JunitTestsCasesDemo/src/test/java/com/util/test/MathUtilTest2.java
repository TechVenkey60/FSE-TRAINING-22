package com.util.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilTest2 {

    private MathUtil mathUtil;

    @BeforeEach
    void setUp() {
        mathUtil = new MathUtil();
    }

    @Nested
    class AdditionTestCases{
        @Test
        void testPositiveNumbers() {
          assertEquals(30,mathUtil.add(10,20),"Only Positive numbers");
        }

        @Test
        void testPositiveNumberAndNegativeNumber() {
            assertEquals(5,mathUtil.add(10,-5),"One Positive number and Negative number");
        }

        @Test
        void testNegativeNumbers() {
            assertEquals(-30,mathUtil.add(-10,-20),"Only negative numbers");
        }
    }


    @Test
    void testMultiply(){
        assertAll( () -> assertEquals(100,mathUtil.multiply(10,10)),
                () -> assertEquals(50,mathUtil.multiply(5,10)));
    }

    @Test
    void testDivision(){
        assertThrows(ArithmeticException.class, () -> mathUtil.division(1,0));
    }

    @Test
    void testComputeCircle(){
        assertNotEquals(2322.5,mathUtil.computeAreaCircle(20));
    }
}
