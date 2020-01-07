package Lab2;

public class Cottage implements House {
    @Override
    public String live() {
        return "Cottage.live";
    }

    @Override
    public int price() {
        return 24000;
    }
}
