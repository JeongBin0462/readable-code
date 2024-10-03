package cleancode.assignment.day4;

import java.util.List;

public class Order {

    private List<Item> items;
    private Customer customer;

    public boolean emptyOrderItems() {
        return items.isEmpty();
    }

    public boolean userInfoNotFound() {
        return customer != null;
    }

    public boolean isOverTotalPriceZero() {
        return getTotalPrice() > 0;
    }

    private int getTotalPrice() {
        return items.stream()
                .mapToInt(Item::getPrice)
                .sum();
    }
}
