package com.lukasz.functional.pg6;

import com.lukasz.functional.pg6.domain.Item;
import com.lukasz.functional.pg6.domain.Sale;
import com.lukasz.functional.pg6.domain.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lukasz.functional.pg6.utils.SalesGenerator.getRandomSale;
import static com.lukasz.functional.pg6.utils.SalesGenerator.getRandomSaleListBySizeAndGivenDate;

public class TodaySales {

    private static Logger log = LoggerFactory.getLogger(TodaySales.class);

    static final List<Sale> sales =
            getRandomSaleListBySizeAndGivenDate(new Random().nextInt(40), new Date());

    public static Stream<Sale>  streamOf(final long qty) {
        return Stream.generate(supplier).limit(qty);
    }

    public static Supplier<Sale> supplier = () ->
            getRandomSale(new Date());

    public static Optional<Sale> findSaleOf(final String itemName){
        return sales.stream()
                .filter(sale -> sale.getItems().stream().anyMatch(
                        item -> item.getName().equals(itemName)
                )).findFirst();
    }

    public static void main(String[] args) {
        log.debug("Let's play with the sales for today: {}", new Date());

        System.out.println("-------------  GENERATED ------------- all");
        sales.forEach(System.out::println);
        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------");
        System.out.println("--------- REAL WORK BELOW ------------");


        System.out.println("Number of generated sales: " + sales.stream().count());
        System.out.println("Did we go sale below 400.00: ");
        System.out.println(sales.stream().anyMatch(sale -> sale.total() < 400.00));

        // maximum sale amount?
        DoubleSummaryStatistics stats =
                sales.stream().mapToDouble(Sale::total).summaryStatistics();
        System.out.println("Max sale amount: " + stats.getMax());
        System.out.println("Stats on total: " + stats);

        // how many items we sold today
        Supplier<Stream<Item>> itemStream =
                () -> sales.stream().flatMap(sale -> sale.getItems().stream());
        long itemCount = itemStream.get().count();
        System.out.println("Number of Items sold: " + itemCount);

        // how many unique items did we sold
        long uniqueItemsCount =
                itemStream.get()
                .map(item -> item.getName())
                .distinct().count();
        System.out.println("Unique items sold today: " + uniqueItemsCount);

        // get names of unique items sold today as a List
        List<String> uniqueItemNames =
            itemStream.get()
            .map(item -> item.getName())
            .distinct()
            .collect(Collectors.toList());

        System.out.println("Name of distinct items sold: " + uniqueItemNames);

        // get names of unique items sold today as a String
        String uniqueItemNamesStr =
                itemStream.get()
                        .map(item -> item.getName())
                        .distinct()
                        .collect(Collectors.joining(" & "));

        System.out.println("Name of distinct items sold (string): " + uniqueItemNamesStr);

        // summarize sale by store
        Map<Store, DoubleSummaryStatistics> statisticsMap = sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getStore(), Collectors.summarizingDouble(Sale::total)));
        System.out.println("Summary by store: " + statisticsMap);
        statisticsMap.keySet().stream().forEach(store ->
                System.out.println(store + " stats: " + statisticsMap.get(store))
        );


        // new way of generating Sales
        System.out.println("New way of generating stream of Sales...");
        streamOf(2).forEach(System.out::println);

        // summarize sale by store / parallel
        System.out.println("********************************** parallel ***");
        Map<String, DoubleSummaryStatistics> statisticsMapByThread = streamOf(10000)
                .parallel()
                .collect(Collectors.groupingBy(sale -> Thread.currentThread().getName(),
                        Collectors.summarizingDouble(Sale::total)));
        System.out.println("Summary by thread: " + statisticsMapByThread);
        statisticsMapByThread.keySet().stream().sorted().forEach(store ->
                System.out.println(store + " stats: " + statisticsMapByThread.get(store))
        );

        // how to use optionals ? let's try to find sales with certain item name
        Optional<Sale> saleForItem7 = findSaleOf("Item__#7");
        if(saleForItem7.isPresent()){
            System.out.println("FOUND: " + saleForItem7.get());
        } else {
            System.out.println("NOT FOUND!!!");
        }

        Optional<Store> item7Store =
                findSaleOf("Item__#7").map(sale -> sale.getStore());
        if(item7Store.isPresent()){
            System.out.println("Store with item 7 found: " + item7Store);
        }

    }
}
