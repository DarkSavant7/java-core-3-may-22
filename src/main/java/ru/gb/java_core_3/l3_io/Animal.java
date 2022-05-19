package ru.gb.java_core_3.l3_io;

import java.io.Serializable;

public class Animal implements Serializable{
    private String type;

    public Animal(String type) {
//        this.type = type;
        System.out.println("Animal born");
    }
}
