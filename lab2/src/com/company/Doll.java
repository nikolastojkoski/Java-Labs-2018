package com.company;

public class Doll implements Toy {
    @Override
    public void play() {
        System.out.println("Doll.play");
    }

    @Override
    public int price() {
        return 5;
    }
}
