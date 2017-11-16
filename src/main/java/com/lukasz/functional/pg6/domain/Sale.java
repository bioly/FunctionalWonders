package com.lukasz.functional.pg6.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Sale {
    private final Store store;
    private final Date date;
    private final Optional<String> manager;
    private final List<Item> items;

    public Sale(Store store, Date date, Optional<String> manager, List<Item> items) {
        this.store = store;
        this.date = date;
        this.manager = manager;
        this.items = items;
    }

    public Store getStore() {
        return store;
    }

    public Date getDate() {
        return date;
    }

    public Optional<String> getManager() {
        return manager;
    }

    public List<Item> getItems() {
        return items;
    }

    public Double total() {
        return items.stream().mapToDouble(item -> item.getPrice()).sum();
    }

    @Override
    public String toString() {
        return "Sale{" +
                "store=" + store +
                ", date=" + date +
                ", manager=" + manager +
                ", items=" + items +
                '}';
    }
}
