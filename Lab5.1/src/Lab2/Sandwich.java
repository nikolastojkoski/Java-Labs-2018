package Lab2;

public class Sandwich implements Food {
    @Override
    public String eat() {
        return "Sandiwch.eat";
    }

    @Override
    public int price() {
        return 9;
    }
}
