package ex4.models;

public class Bid {
    private Item item;

    public Bid(Item item) {
        this.item = item;
        item.bids.add(this);
    }
}
