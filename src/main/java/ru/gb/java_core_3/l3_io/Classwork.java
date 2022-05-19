package ru.gb.java_core_3.l3_io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Classwork {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        fileExample();
//        simpleFileWrite();
//        simpleFileRead();
//        bufferingExample();
//        readWithReaderExample();
//        sequenseExample();
//        rafExample();
//        simpleSerializableExample();

        CatEx cat = new CatEx("Murzik", "red");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("example/murzik.sr"))) {
            oos.writeObject(cat);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("example/murzik.sr"))) {
            CatEx deserialized = (CatEx) ois.readObject();

            System.out.println(cat);
            System.out.println(deserialized);
            System.out.println(cat == deserialized);
            System.out.println(cat.equals(deserialized));
        }

    }

    private static void simpleSerializableExample() throws IOException, ClassNotFoundException {
        Cat cat = new Cat("Barsik", "red");

//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("example/barsik.sr"))) {
//            oos.writeObject(cat);
//        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("example/barsik.sr"))) {
            Cat deserialized = (Cat) ois.readObject();

            System.out.println(cat);
            System.out.println(deserialized);
            System.out.println(cat == deserialized);
            System.out.println(cat.equals(deserialized));
        }
    }

    private static void rafExample() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile("example/ex1.txt", "r")) {
            int b;
            raf.seek(40);
            while ((b = raf.read()) != -1) {
                System.out.print((char) b);
            }
        }
    }

    private static void sequenseExample() throws IOException {
        ArrayList<InputStream> streams = new ArrayList<>();
        streams.add(new FileInputStream("example/ex1.txt"));
        streams.add(new FileInputStream("example/ex3.txt"));
        streams.add(new FileInputStream("example/ex4.txt"));

        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(streams));

        int b;
        while ((b = sis.read()) != -1) {
            System.out.print((char) b);
        }
    }

    private static void readWithReaderExample() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("example/ex1.txt"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }

            br.lines()
                    .distinct()
//                    .filter(s -> s.contains(" "))
                    .forEach(System.out::println);
        }
    }

    private static void bufferingExample() throws IOException {
        //        long start = System.currentTimeMillis();
//        try (FileInputStream fis = new FileInputStream("example/ex2.txt")) {
//            int b;
//            while((b = fis.read()) > -1) {
////                System.out.print((char) b);
//            }
//        }
//        System.out.println(System.currentTimeMillis() - start);

//        long start = System.currentTimeMillis();
////        byte[] buf = new byte[8];
//        byte[] buf = new byte[512];
//
//        try (FileInputStream fis = new FileInputStream("example/ex2.txt")) {
//            int b;
//            while((b = fis.read(buf)) > -1) {
////                System.out.print((char) b);
//            }
//        }
//        System.out.println(System.currentTimeMillis() - start);

        long start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("example/ex2.txt"))) {
            int b;
            while ((b = bis.read()) > -1) {
                //................
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void simpleFileRead() throws IOException {
        try (FileInputStream fis = new FileInputStream("example/ex1.txt")) {
            int b;
            while((b = fis.read()) > -1) {
                System.out.print((char) b);
            }
        }
    }

    private static void simpleFileWrite() throws IOException {
        File file = new File("example/ex1.txt");
        String s = "Hello world!";

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(s.getBytes());
        }
    }

    private static void fileExample() throws IOException {
        File file = new File("example/ex1.txt");
        File dir = new File("example");

        if (!dir.exists()) {
            dir.mkdir();
        }
        System.out.println(file.exists());

        if (!file.exists()) {
            file.createNewFile();
        }

//        System.out.println(file.isFile());
//        System.out.println(file.isDirectory());
//        System.out.println(dir.isDirectory());
//        System.out.println(dir.isFile());
//        file.delete();


        File dir2 = new File("example/1/2/3/4/5/6/7/8/9");
//        System.out.println(dir2.mkdir());
//        System.out.println(dir2.mkdirs());

//        String[] list = dir2.list();
//
//        for (String s : list) {
//            System.out.println(s);
//        }

//        File[] list = dir2.listFiles((d, name) -> name.endsWith(".xls"));
//        File[] list = dir2.listFiles(File::isFile);
//        for (File file1 : list) {
//            System.out.println(file1);
//        }

        recursiveFileWalk(new File("./"));
    }

    private static void recursiveFileWalk(File root) {
        if (root.isFile()) {
            System.out.println("File -->> " + root.getPath());
        } else {
            System.out.println("Catalog -->> " + root.getPath());
            File[] files = root.listFiles();
            for (File file : files) {
                recursiveFileWalk(file);
            }
        }
    }

}
