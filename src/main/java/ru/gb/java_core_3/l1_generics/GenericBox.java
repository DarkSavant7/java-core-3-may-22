package ru.gb.java_core_3.l1_generics;

//T (ype) E(lement) K(ey) V(alue)
//public class GenericBox<TYPE, Y, Z> {
public class GenericBox<TYPE> {
//    private static TYPE staticField;
    private TYPE obj;
//    private Y yObj;
//    private Z zObj;

    public GenericBox(TYPE obj) {
        this.obj = obj;
    }

    public GenericBox() {
//        obj = new TYPE();
//        TYPE[] arr = new TYPE[10];
//        TYPE[] arr = (TYPE[])new Object[10];
    }

    public TYPE getObj() {
        return obj;
    }

    public void setObj(TYPE obj) {
        this.obj = obj;
    }

}
