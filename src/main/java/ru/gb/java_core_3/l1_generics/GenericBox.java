package ru.gb.java_core_3.l1_generics;

//T (ype) E(lement) K(ey) V(alue)
//public class GenericBox<X, Y, Z> {
public class GenericBox<X> {
//    private static X staticField;
    private X obj;

    public GenericBox(X obj) {
        this.obj = obj;
    }

    public GenericBox() {
//        obj = new X();
//        X[] arr = new X[10];
    }

    public X getObj() {
        return obj;
    }

    public void setObj(X obj) {
        this.obj = obj;
    }

}
