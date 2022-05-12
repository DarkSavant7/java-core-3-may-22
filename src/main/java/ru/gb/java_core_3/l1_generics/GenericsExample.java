package ru.gb.java_core_3.l1_generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GenericsExample {

    public static void main(String[] args) {
//        simpleBoxExample();
//        genericBoxExample();
//        rawUseExample();
//        multyExample();
//        variantExample();
//        numbersExample();
//        comparingFloats();
//        example();

        int a = 10;
        Integer b = a;
        a = b;

        byte c = 10;
        Byte d = c;
        c = d;
    }

    private static void example() {
        List<Integer> ints = List.of(1, 2, 4, 5);
        List<Integer> ints2 = List.of(22, 11, 123, 14);
//        System.out.println(getFirstObj(ints));
        int sum = (int) getFirstObj(ints) + (int) getFirstObj(ints2);
        System.out.println(sum);

        sum = getFirstObjGeneric(ints) + getFirstObjGeneric(ints2);
        System.out.println(sum);

        List<String> strings = List.of("xfbfd", "dfsgb");
        System.out.println(getFirstObjGeneric(strings));

        System.out.println(getFirstObjGeneric(ints) + getFirstObjGeneric(strings));

        System.out.println(getFirstNum(ints));
//        System.out.println(getFirstNum(strings));

        List<Integer> integers = new ArrayList<>(ints);


        List<? extends Integer> extended = integers;
        for (Integer integer : extended) {
            System.out.println(integer);
        }
//        extended.add(10);

        List<? super Integer> extended2 = integers;
        for (Object o : extended2) {
            System.out.println(o);
        }
        extended2.add(10);

//        Collections.copy();
    }

    private static <T extends Number> T getFirstNum(List<T> list) {
        return list.get(0);
    }
    private static <T> T getFirstObjGeneric(List<T> list) {
        return list.get(0);
    }

    private static Object getFirstObj(List list) {
        return list.get(0);
    }

    private static void comparingFloats() {
        double a = 0.7;
        double b = .0;

        for (int i = 0; i < 70; i++) {
            b += 0.01;
        }

        System.out.println(a == b);
        System.out.println(a);
        System.out.println(b);
    }

    private static void numbersExample() {
        BoxWithNumbers<Number> numBox = new BoxWithNumbers<>(10, 11, 12);
        BoxWithNumbers<Integer> intBox = new BoxWithNumbers<>(10, 11, 12);
        BoxWithNumbers<Integer> intBox2 = new BoxWithNumbers<>(10, 11, 12);
        BoxWithNumbers<Float> floatBox = new BoxWithNumbers<>(10f, 11f, 12f);
//        BoxWithNumbers<String> stringBoxWithNumbers = new BoxWithNumbers<>("gszfg");
        System.out.println(intBox.equalsByAvg(floatBox));
    }

    private static void variantExample() {
        GenericBox<Integer> numberBox = new GenericBox<>(10);
//        GenericBox<Number> intBox = numberBox;

        Integer[] arrInt = new Integer[10];
        Number[] arrNum = arrInt;
    }

    private static void multyExample() {
        GenericBoxMultyType<String, Integer, Float, String, String, Double> multy = new GenericBoxMultyType<>(
                "Hello",
                10,
                14f,
                "world",
                "!",
                3.14
        );

        System.out.printf("1 = %s, 2 = %s, 3= %s, 4 = %s, 5 = %s, 6 = %s\n",
                multy.getxObj(),
                multy.getyObj(),
                multy.getzObj(),
                multy.getaObj(),
                multy.getbObj(),
                multy.getcObj());
    }

    private static void rawUseExample() {
        GenericBox boxInt = new GenericBox(100500);
        GenericBox boxInt2 = new GenericBox(99);

        GenericBox stringBox = new GenericBox("Hello");
        GenericBox stringBox2 = new GenericBox(" world!");

        //A lot of code strings...
//        boxInt.setObj("AAAAAAAAA");
//        stringBox.setObj(4324);
        //A lot of code strings...
        int sum = 0;
        if (boxInt.getObj() instanceof Integer && boxInt2.getObj() instanceof Integer) {
            sum = (int) boxInt.getObj() + (int) boxInt2.getObj();
        } else {
            System.out.println("Cannot calculate");
        }

        String concat = (String) stringBox.getObj() + (String) stringBox2.getObj();
        System.out.println(sum);
        System.out.println(concat);
    }

    private static void genericBoxExample() {
        GenericBox<Integer> boxInt = new GenericBox<>(100500);
        GenericBox<Integer> boxInt2 = new GenericBox<>(99);

        GenericBox<String> stringBox = new GenericBox<>("Hello");
        GenericBox<String> stringBox2 = new GenericBox<>(" world!");

        //A lot of code strings...
//        boxInt.setObj("AAAAAAAAA");
//        stringBox.setObj(4324);
        //A lot of code strings...

        int sum = 0;
        sum = boxInt.getObj() + boxInt2.getObj();


        String concat = stringBox.getObj() + stringBox2.getObj();

        System.out.println(sum);
        System.out.println(concat);
    }

    private static void simpleBoxExample() {
        Box boxInt = new Box(100500);
        Box boxInt2 = new Box(99);

        Box stringBox = new Box("Hello");
        Box stringBox2 = new Box(" world!");

        //A lot of code strings...
        boxInt.setObj("AAAAAAAAA");
        //A lot of code strings...

        int sum = 0;
        if (boxInt.getObj() instanceof Integer && boxInt2.getObj() instanceof Integer) {
            sum = (int) boxInt.getObj() + (int) boxInt2.getObj();
        } else {
            System.out.println("Cannot calculate");
        }

        String concat = (String) stringBox.getObj() + (String) stringBox2.getObj();

        System.out.println(sum);
        System.out.println(concat);
    }
}
