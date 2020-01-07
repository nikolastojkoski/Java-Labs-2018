package Lab2;

public class Kotleta implements Food {
    @Override
    public String eat() {
        return "Kotleta.eat";
    }

    @Override
    public int price() {
        return 2;
    }
}
