package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter number of objects: ");
        Scanner scanner = new Scanner(System.in);

        int n = 0;
        try {
            n = scanner.nextInt();
        }catch(Exception e){
            System.out.println("Invalid input");
            return;
        }

        if(n <= 0){
            System.out.println("Invalid value for number of objects");
        }

        ArrayList<Item> items = new ArrayList<Item>();

        for(int i=0; i < n; i++) {
            switch(i % 6){
                case 0: items.add(new Ball());
                        break;
                case 1: items.add(new Doll());
                        break;
                case 2: items.add(new Sandwich());
                        break;
                case 3: items.add(new Kotleta());
                        break;
                case 4: items.add(new Cottage());
                        break;
                case 5: items.add(new Yurt());
                        break;
            }
        }
        
        for(Item obj: items)
        {
            System.out.println("obj.toString() = " + obj.toString());
        }

        for(Item obj: items)
        {
            if(obj instanceof Food)
            {
                System.out.println("Found food: " + obj.toString());
                ((Food) obj).eat();
                System.out.println("The price is " + obj.price());
            }
        }
    }
}
