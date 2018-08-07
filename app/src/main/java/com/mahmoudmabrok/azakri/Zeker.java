package com.mahmoudmabrok.azakri;

/**
 * Created by motamed on 3/26/2018.
 */

public class Zeker {
    private String name;
    private int count = 1;

    public Zeker(String name) {
        this.name = name;
    }

    public Zeker(String name, int number) {
        this(name);
        this.count = number;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
