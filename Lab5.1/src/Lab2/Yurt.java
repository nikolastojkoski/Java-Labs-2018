package Lab2;

public class Yurt implements House {
    @Override
    public String live() {
        return "Yurt.live";
    }

    @Override
    public int price() {
        return 15000;
    }
}
