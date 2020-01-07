package com.company;

public class Cottage implements House {
    @Override
    public void live() {
        System.out.println("Cottage.live");
    }

    @Override
    public int price() {
        return 24000;
    }
}
