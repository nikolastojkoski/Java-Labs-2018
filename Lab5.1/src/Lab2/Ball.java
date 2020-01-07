package Lab2;

public class Ball implements Toy {
    @Override
    public String play() {
        return "Ball.play";
    }

    @Override
    public int price() {
        return 10;
    }
}
