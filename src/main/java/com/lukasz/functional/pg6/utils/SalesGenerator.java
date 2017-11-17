package com.lukasz.functional.pg6.utils;

import com.lukasz.functional.pg6.domain.Item;
import com.lukasz.functional.pg6.domain.Sale;
import com.lukasz.functional.pg6.domain.Store;

import java.util.*;

public class SalesGenerator {

    private static Random random;

    static {
        random = new Random();
    }

    public static List<Sale> getRandomSaleListBySizeAndGivenDate(final int size, final Date date){
        List<Sale> sales = new ArrayList<>();
        // the magic
        int i = size;
        while(i-- >= 0){
            sales.add(new Sale(getRandomStore(),
                      date,
                      Optional.of(getRandomManagerName()),
                      getRandomItemList()));
        }

        return sales;
    }

    public static Sale getRandomSale(final Date date){
        return new Sale(getRandomStore(),
                    date,
                    Optional.of(getRandomManagerName()),
                    getRandomItemList());
    }

    private static List<Item> getRandomItemList() {
        List<Item> items = new ArrayList<>();
        int numberOfItems = random.nextInt(10);
        for(int i = 0; i < numberOfItems + 1; i++){
            items.add(new Item("Item__#" + (i+1), random.nextDouble() * 1000));
        }
        return items;
    }

    private static String getRandomManagerName() {

        String names = "a1b2c3d4e5f6g9j";


        int low = random.nextInt(names.length());
        String name = names.substring(low);


        return name;

    }

    private static Store getRandomStore() {
        int storeId = random.nextInt(Store.values().length);
        switch(storeId){
            case 0:
                return Store.KANSAS_CITY;
            case 1:
                return Store.ST_LOUIS;
            case 2:
                return Store.NEW_YORK;
            case 3:
                return Store.CHICAGO;
            case 4:
                return Store.DALLAS;
            case 5:
                return Store.SAN_ANTONIO;
            default:
                return Store.DALLAS;
        }
    }
}
