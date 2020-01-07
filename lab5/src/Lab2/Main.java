package Lab2;

import javafx.scene.control.TextArea;
import java.util.ArrayList;

public class Main {

    public static void start(String numObjects, TextArea output) {

        int n = 0;
        try {
            n = Integer.parseInt(numObjects);
        }catch(NumberFormatException e){
            e.printStackTrace();
            output.appendText("Invalid input \n");
            return;
        }

        if(n <= 0){
            output.appendText("Invalid value for number of objects \n");
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
            output.appendText("obj.toString() = " + obj.toString() + "\n");
        }

        for(Item obj: items)
        {
            if(obj instanceof Food)
            {
                output.appendText("Found food: " + obj.toString() + "\n");
                output.appendText(((Food) obj).eat() + "\n");
                output.appendText("The price is " + obj.price() + "\n");
            }
        }
    }
}
