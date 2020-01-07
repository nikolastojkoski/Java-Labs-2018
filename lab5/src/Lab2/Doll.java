package Lab2;

public class Doll implements Toy {
    @Override
    public String play() {
        return "Doll.play";
    }

    @Override
    public int price() {
        return 5;
    }
}
