package ru.gb.java_core_3.l1_generics.homework;

public class Test {
    public static void main(String[] args) {
        FruitBox<Apple> appleFruitBox = new FruitBox<>(new Apple(), new Apple(), new Apple());
//        FruitBox f = new FruitBox();
        FruitBox<Orange> oranges = new FruitBox<>(new Orange(), new Orange());

        var orange1 = new Orange();
        var orange2 = new Orange();
        var orange3 = new Orange();

        System.out.println(appleFruitBox.equalsByWeight(oranges));

        FruitBox<Orange> orangeFruitBox = new FruitBox<>(orange1, orange2, orange3);

        System.out.println(oranges.getWeight());
        oranges.transferAll(orangeFruitBox);
        System.out.println(orangeFruitBox.getWeight());
        System.out.println(oranges.getWeight());
//        oranges.transferAll(appleFruitBox);
//        oranges.add(new Apple());
//        appleFruitBox.add(new Orange());
//        System.out.println(appleFruitBox.getClass().getName());
//        System.out.println(f.getClass().getName());
//        System.out.println(oranges.getClass().getName());

//        appleFruitBox.add(new Orange());

    }
}
