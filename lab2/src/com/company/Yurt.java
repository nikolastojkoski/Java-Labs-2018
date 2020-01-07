package com.company;

public class Yurt implements House {
    @Override
    public void live() {
        System.out.println("Yurt.live");
    }

    @Override
    public int price() {
        return 15000;
    }
}
