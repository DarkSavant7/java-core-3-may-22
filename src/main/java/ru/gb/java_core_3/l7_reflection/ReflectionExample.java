package ru.gb.java_core_3.l7_reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionExample {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {
//        Class<Cat> catClass = Cat.class;
//        Class catClass = Class.forName("ru.gb.java_core_3.l7_reflection.ReflectionExample$Cat");
        Cat cat = new Cat("Barsik", "red", 1);
        Class catClass = cat.getClass();

        //Field
        //Constructor
        //Method
        //Modifier

        System.out.println(catClass.getName());
        int mods = catClass.getModifiers();
        System.out.println(Modifier.isStatic(mods));
        System.out.println(Modifier.isPrivate(mods));
        System.out.println(Modifier.toString(mods));


        Field privField = catClass.getDeclaredField("privateField");
//        privField.get(null); //null for static
        System.out.println(privField.get(cat));
        privField.setAccessible(true);
        privField.set(cat, 100500);
        System.out.println(privField.get(cat));

        Field someField = catClass.getDeclaredField("some");
        someField.setAccessible(true);
        someField.set(cat, "BBB");
//        System.out.println(cat);
        Field nameField = catClass.getField("name");
        nameField.setAccessible(true);
        nameField.set(cat, "Murzik");
        System.out.println(cat);


//        System.out.println(privField.getType());


//        Field[] fields = catClass.getFields();
//        Field[] fields = catClass.getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field);
//        }

//        Constructor[] constructors = catClass.getDeclaredConstructors();
        Constructor constructor = catClass.getConstructor(String.class, String.class, int.class);

        Cat reflected = (Cat) constructor.newInstance("Refl", "Black", 999);
        System.out.println(reflected);
//        Cat ref2 = (Cat) catClass.newInstance();

        Method[] methods = catClass.getDeclaredMethods();
        for (Method method : methods) {
//            System.out.println(method);
            if (method.isAnnotationPresent(MyAnno.class)) {
                System.out.println(method);
                System.out.println(method.getAnnotation(MyAnno.class).someParam());

            }
        }

//        Method run = catClass.getDeclaredMethod("run", int.class);
//        run.setAccessible(true);
//        run.invoke(cat, 200);
    }



    public static final class Cat {
        public final String some = "AAAA";
        static final String type = "CAT";
        public final String name;
        public String color;
        private int privateField;
        final int age = 1;
        private Bowl b;

        public Cat() {
//           age = 1;
            name = "Nameless";
        }

        public Cat(String name, String color, int age) {
            this.name = name;
            this.color = color;
//            this.age = age;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "some='" + some + '\'' +
                    ", name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    ", privateField=" + privateField +
                    ", age=" + age +
                    ", b=" + b +
                    '}';
        }

        @MyAnno(someParam = 10)
        public void voice() {
            System.out.println(name + " mew");
        }

        private void run(int distance) {
            System.out.println(name + " running for " + distance);
        }


    }

    public static class Bowl {
        int food;
    }
}
