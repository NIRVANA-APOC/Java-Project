package project.Utils;

public class Order {
    private final String goodsID;
    private final int outCounter;

    public Order() {
        this.goodsID = "default_goods";
        this.outCounter = 0;
    }

    public Order(String goodsID, int outCounter) {
        this.goodsID = goodsID;
        this.outCounter = outCounter;
    }
}
