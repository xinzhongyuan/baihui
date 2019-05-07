package cn.itcast;

import java.util.ArrayList;
import java.util.List;

class t4 extends Thread {

    Integer[] i;
    List list;
    private Object o;

    public t4(Integer[] i, Object o) {
        this.i = i;
        this.o = o;
    }

    public t4(List list, Object o) {
        this.list = list;
        this.o = o;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.o) {
                // int y=i[0s];
                int y = (int) list.get(0);
                if (y % 2 == 1) {
                    try {
                        this.o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("0000" + Thread.currentThread().getName() + "--" + y++);
                    list.set(0, y);
                    this.o.notify();
                }

            }
        }
    }
}

class t5 extends Thread {
    Integer[] i;
    List list;
    private Object o;

    public t5(Integer[] i, Object o) {
        this.i = i;
        this.o = o;
    }

    public t5(List list, Object o) {
        this.list = list;
        this.o = o;
    }

    @Override
    public void run() {

        while (true) {

            synchronized (this.o) {
                // int y=i[0];
                int y = (int) list.get(0);
                if (y % 2 == 0) {
                    try {
                        this.o.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else{
                    System.out.println("1111" + Thread.currentThread().getName() + "--" + y++);
                    list.set(0, y);
                    this.o.notify();
                }



            }
        }
    }
}

public class Test {
    public static void main(String[] args) {
        // Integer[] i=new Integer[1];
        List list = new ArrayList();
        list.add(1);
        Object o = new Object();
        // i[0]=0;
        t4 t1 = new t4(list, o);
        t1.start();
        t5 t2 = new t5(list, o);
        t2.start();
    }
}
