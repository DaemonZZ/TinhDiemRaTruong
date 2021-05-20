package com.tinhdiemratruong;

import java.io.Serializable;

public class BoDiem implements Comparable<BoDiem>, Serializable {
    private int a,b,c,d,f;

    public BoDiem(int a, int b, int c, int d, int f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.f = f;
    }

    public BoDiem() {
        this.a=0;
        this.b=0;
        this.c=0;
        this.d=0;
        this.f=0;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int sum(){
        return 4*a+3*b+2*c+d;
    }

    @Override
    public int compareTo(BoDiem o) {
        return this.sum()>=o.sum()?1:-1;
    }
}
