package ru.gb.java_core_3.l7_reflection;

import ru.gb.java_core_3.l7_reflection.little_hiber.Cat;
import ru.gb.java_core_3.l7_reflection.little_hiber.Employee;
import ru.gb.java_core_3.l7_reflection.little_hiber.LittleHiber;

public class MyHiberTest {
    public static void main(String[] args) {
//        LittleHiber.createTable(Cat.class);
//        LittleHiber.createTable(Employee.class);

        Cat cat1 = new Cat(0, "Barsik", "red");
        Cat cat2 = new Cat(0, "Murzik", "Black");
        Cat cat3 = new Cat(0, "Vaska", "white");

        LittleHiber.insertObject(cat1);
        LittleHiber.insertObject(cat2);
        LittleHiber.insertObject(cat3);

        Employee emp1 = new Employee(1, "xccvfd", 10, 10, "sdfdv", "dfbvfd", "safdbdafb");
        Employee emp2 = new Employee(2, "xccvfd", 10, 10, "sdfdv", "dfbvfd", "safdbdafb");
        Employee emp3 = new Employee(3, "xccvfd", 10, 10, "sdfdv", "dfbvfd", "safdbdafb");

        LittleHiber.insertObject(emp1);
        LittleHiber.insertObject(emp2);
        LittleHiber.insertObject(emp3);
    }
}
