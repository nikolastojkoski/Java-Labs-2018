package com.company;

public class Ball implements Toy {
    @Override
    public void play() {
        System.out.println("Ball.play");
    }

    @Override
    public int price() {
        return 10;
    }
}
