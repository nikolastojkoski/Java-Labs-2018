package com.company;

public class Sandwich implements Food {
    @Override
    public void eat() {
        System.out.println("Sandwich.eat");
    }

    @Override
    public int price() {
        return 9;
    }
}
