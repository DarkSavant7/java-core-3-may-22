package ru.gb.java_core_3.l4_multithreading.j2;

import java.io.IOException;

public class MultithreadingExample {

    private final static Object mon1 = new Object();
    private final static Object mon2 = new Object();

    private static int wordNum = 0;

    public static void main(String[] args) throws IOException {
        threadCreationExample();
//        mainFinishedFirstExample();
//        threadStopExample();
//        raceConditionExample();
//        stupidDeadLockExample();
//        Desktop desktop = Desktop.getDesktop();
//        desktop.browse(URI.create("https://yandex.ru"));

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printHello();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printWorld();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printHello();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printWorld();
            }
        }).start();
    }

    private static void printWorld() {
        synchronized (mon1) {
            while (1 != wordNum) {
                try {
                    mon1.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(" world!");
            wordNum = 0;
            mon1.notifyAll();
        }
    }

    private static void printHello() {
        synchronized (mon1) {
            while (0 != wordNum) {
                try {
                    mon1.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.print("Hello");
            wordNum = 1;
            mon1.notifyAll();
        }
    }

    private static void stupidDeadLockExample() {
        new Thread(MultithreadingExample::doA).start();
        new Thread(MultithreadingExample::doB).start();
    }

    private static void doA() {
        System.out.println("doA start");

        synchronized (mon1) {
            try {
                System.out.println("got mon 1");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            doB();
        }
    }

    private static void doB() {
        System.out.println("doB start");

        synchronized (mon2) {
            try {
                System.out.println("got mon 2");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            doA();
        }
    }

    private static void raceConditionExample() {
        RaceConditionExample example = new RaceConditionExample();
        Thread t1 = new Thread(() -> increment(example));
        Thread t2 = new Thread(() -> increment(example));
        Thread t3 = new Thread(() -> increment(example));
//        t1.setPriority(5);
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        example.printVariables();
    }

    private static void increment(RaceConditionExample example) {
        for (int i = 0; i < 1000; i++) {
            example.increment();
        }
    }

    private static void threadStopExample() {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500);
                    System.out.println("PING");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
//        t.setDaemon(true);
        t.start();
        System.out.println("Thread started");
        try {
            Thread.sleep(2000);
            t.interrupt();
//            t.suspend();
//            t.resume();
//            t.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mainFinishedFirstExample() {
        Thread main = Thread.currentThread();

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(main.getState());
                System.out.println("Thread completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        System.out.println("Main finished");
    }

    private static void threadCreationExample() {
        System.out.printf("Hello from main! Thread name is: %s\n", Thread.currentThread().getName());
//        Thread.getAllStackTraces().forEach((k, v) -> System.out.println(k + ": " + v));
        MyThread myThread = new MyThread();
//        myThread.run();
        myThread.start();
//        myThread.start();
        new Thread(myThread).start();
        Thread myRunnable = new Thread(new MyRunnable());
        myRunnable.start();

        Thread anon = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf("Hello from Anonymous! Thread name is: %s\n", Thread.currentThread().getName());
            }
        });
        anon.start();

        new Thread(() -> System.out.printf("Hello from lambda! Thread name is: %s\n", Thread.currentThread().getName())).start();
        new Thread(MultithreadingExample::printSomething).start();
    }

    private static void printSomething() {
        System.out.printf("Hello from Method reference! Thread name is: %s\n", Thread.currentThread().getName());
    }

    static class RaceConditionExample {
        private final Object mon = new Object();
        private int a;
        private int b;
        private int c;

        public RaceConditionExample() {
            a = 0;
            b = 0;
            c = 0;
        }

        // static -> mon = class
        // non static -> mon = this
        public /*synchronized */ void increment() {
            synchronized (mon) {
                a++;
                b++;
                c++;
            }
        }

        public synchronized void printVariables() {
            System.out.printf("a = %d, b = %d, c = %d\n", a, b, c);
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.printf("Hello from MyRunnable! Thread name is: %s\n", Thread.currentThread().getName());
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.printf("Hello from MyThread! Thread name is: %s\n", Thread.currentThread().getName());
        }
    }
}
