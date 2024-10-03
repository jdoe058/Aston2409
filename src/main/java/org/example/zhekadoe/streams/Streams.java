package org.example.zhekadoe.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    record Trader(String name, String city) {
    }

    record Transaction(Trader trader, int year, int value) {
    }

    public static void main(String[] args) {
        List<Transaction> transactions = getTransactions();

        // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        transactions.stream().filter(x -> x.year == 2011)
                .sorted(Comparator.comparingInt(Transaction::value))
                .forEach(System.out::println);

        // 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        transactions.stream()
                .map(x -> x.trader.city)
                .distinct().toList()
                .forEach(System.out::println);

        // 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        transactions.stream()
                .map(Transaction::trader)
                .distinct()
                .filter(x -> x.city.equals("Cambridge"))
                .sorted((x, y) -> x.name.compareToIgnoreCase(y.name))
                .forEach(System.out::println);

        // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        System.out.println(transactions.stream()
                .map(x -> x.trader.name)
                .distinct()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.joining(", ")));

        // 5. Выяснить, существует ли хоть один трейдер из Милана.
        System.out.println(transactions.stream()
                .anyMatch(x -> x.trader.city.equals("Milan")));

        // 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        transactions.stream()
                .filter(x -> x.trader.city.equals("Cambridge"))
                .collect(Collectors.groupingBy(
                        x -> x.trader.name,
                        Collectors.summingInt(Transaction::value)
                ))
                . forEach((x, y) -> System.out.println(x + " - " + y));

        // 7. Какова максимальная сумма среди всех транзакций?
        System.out.println(transactions.stream().mapToInt(Transaction::value).max());

        // 8. Найти транзакцию с минимальной суммой.
        System.out.println(transactions.stream().min(Comparator.comparingInt(Transaction::value)));
    }

    private static List<Transaction> getTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
}
