package ex3.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Item {
    private Set<Bid> bids = new HashSet<>();

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void addBid(Bid bid) {
        if (bid == null) throw new NullPointerException("Can't add a null bid.");
        if (bid.getItem() != null) {
            throw new IllegalStateException("Bid is already assigned to an item.");
        }
        bids.add(bid);
        bid.setItem(this);
    }
}
