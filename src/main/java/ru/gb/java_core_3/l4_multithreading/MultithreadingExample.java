package ru.gb.java_core_3.l4_multithreading;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MultithreadingExample {

    public static void main(String[] args) {
        //        futureExample();
//        executorsExample();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> System.out.println("Schedule!"), 2, 1, TimeUnit.SECONDS);
    }

    private static void executorsExample() {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread t = new Thread(r);
////                t.setDaemon(true);
//                t.setName("Some thread");
//                return t;
//            }
//        });

//        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.execute(() -> {
                System.out.printf("Task #%d started. Thread name: %s\n", j, Thread.currentThread().getName());
                try {
                    Thread.sleep((long) (2000 + 2000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Task #%d finished. Thread name: %s\n", j, Thread.currentThread().getName());
            });
        }

        Future<String> future = executorService.submit(() -> "Hello world!");

        System.out.println("All tasks given");

        executorService.shutdown();
//       List<Runnable> unexecuted = executorService.shutdownNow();

        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void futureExample() {
        //        Thread.getAllStackTraces().forEach((k, v) -> System.out.println(k + ": " + v));
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                Thread.sleep(2000);
//                throw new RuntimeException("AAAAAAAAAAAAAAAAA");
                return "Hello from future task";
            }
        });

        new Thread(futureTask).start();

        String result = null;
        try {
            result = futureTask.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
