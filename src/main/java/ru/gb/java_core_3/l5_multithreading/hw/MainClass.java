package ru.gb.java_core_3.l5_multithreading.hw;

public class MainClass {

    public static void main(String[] args) {
        Race race = new Race(8,
                new Road(60),
                new Tunnel(80, 4),
                new Road(40),
                new Tunnel(30, 2),
                new Road(100),
                new Road(20));

        race.makeRace();
    }
}
