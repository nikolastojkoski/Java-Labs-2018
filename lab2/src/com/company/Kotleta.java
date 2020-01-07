package com.company;

public class Kotleta implements Food {
    @Override
    public void eat() {
        System.out.println("Kotleta.eat");
    }

    @Override
    public int price() {
        return 2;
    }
}
