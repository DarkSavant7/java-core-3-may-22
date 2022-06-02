package ru.gb.java_core_3.l6_log_test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all");
    }


    @BeforeEach
    void init() {
        System.out.println("Before each");
        calculator = new Calculator();
    }

    @AfterEach
    void close() {
        System.out.println("After each");
    }

    @Test
    void addTest() {
        int a = 5;
        int b = 4;
        int result = 9;
        assertEquals(result, calculator.add(a, b));
    }

    @Test
    void subTest() {
        assertEquals(2, calculator.sub(4, 2));
    }

    @Test
    void mulTest() {
        assertEquals(14, calculator.mul(7, 2));
    }

    @Test
    void divTest() {
        assertEquals(3, calculator.div(9, 3));
    }

    @Test
    void shouldThrowArithmeticExceptionWhenDividingByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.div(3, 0));
    }

    @Test
    @Disabled
    void massAddTest1() {
        int a = 5;
        int b = 4;
        int result = 9;
        assertAll(
                () -> assertEquals(result, calculator.add(a, b)),
                () -> assertEquals(4, calculator.add(2, 2)),
                () -> assertEquals(5, calculator.add(4, 1)),
                () -> assertEquals(6, calculator.add(5, 1)),
                () -> assertEquals(7, calculator.add(6, 1)));
    }

    //    @CsvSource(
//            {"2,1,1",
//                    "3,2,1",
//                    "4,2,2"}
//
//    )
//    @CsvFileSource(files = {"test_files/t1.csv", "test_files/t2.csv"})
    @MethodSource("addTestGenerator")
    @ParameterizedTest
    void massAddTest2(int a, int b, int result) {
        assertEquals(result, calculator.add(a, b));
    }

    public static Stream<Arguments> addTestGenerator() {
        int count = 1000;
        List<Arguments> args = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            int a = (int) (Math.random() * count);
            int b = (int) (Math.random() * count);
            int r = a + b;
            args.add(Arguments.of(a, b, r));
        }
        return args.stream();
    }

}