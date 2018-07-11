package com.tysheng.playground;

/**
 * Created by tysheng
 * Date: 30/6/18 5:46 PM.
 * Email: tyshengsx@gmail.com
 */
public class Foo2 {
    public int i2 = 2;

    {
        i2 = 3;
    }

    public Foo2() {
        System.out.println("cons");
    }

    public Foo2(int i) {
        this.i2 = i;

    }

    @Override
    public String toString() {
        return "Foo2{" +
                "i2=" + i2 +
                '}';
    }
}
