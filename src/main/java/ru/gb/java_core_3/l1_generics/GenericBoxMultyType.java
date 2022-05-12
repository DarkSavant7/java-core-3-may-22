package ru.gb.java_core_3.l1_generics;

public class GenericBoxMultyType<H, E, L, P, B, C> {
    private H xObj;
    private E yObj;
    private L zObj;
    private P aObj;
    private B bObj;
    private C cObj;

    public GenericBoxMultyType(H xObj, E yObj, L zObj, P aObj, B bObj, C cObj) {
        this.xObj = xObj;
        this.yObj = yObj;
        this.zObj = zObj;
        this.aObj = aObj;
        this.bObj = bObj;
        this.cObj = cObj;
    }

    public H getxObj() {
        return xObj;
    }

    public void setxObj(H xObj) {
        this.xObj = xObj;
    }

    public E getyObj() {
        return yObj;
    }

    public void setyObj(E yObj) {
        this.yObj = yObj;
    }

    public L getzObj() {
        return zObj;
    }

    public void setzObj(L zObj) {
        this.zObj = zObj;
    }

    public P getaObj() {
        return aObj;
    }

    public void setaObj(P aObj) {
        this.aObj = aObj;
    }

    public B getbObj() {
        return bObj;
    }

    public void setbObj(B bObj) {
        this.bObj = bObj;
    }

    public C getcObj() {
        return cObj;
    }

    public void setcObj(C cObj) {
        this.cObj = cObj;
    }
}
