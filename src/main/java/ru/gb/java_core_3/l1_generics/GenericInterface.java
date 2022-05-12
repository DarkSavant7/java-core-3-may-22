package ru.gb.java_core_3.l1_generics;

public interface GenericInterface<M> {
    M doSomething(M request);


     class GenClass1<M> implements GenericInterface<M> {
        @Override
        public M doSomething(M request) {
            return null;
        }
    }

     class GenClass2 implements GenericInterface<String> {
        @Override
        public String doSomething(String request) {
            return null;
        }
    }

    class GenClass3 implements GenericInterface {
        @Override
        public Object doSomething(Object request) {
            return null;
        }
    }
}
