package samuelvalentini;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Boys T-Shirt", "boys", 14.99));
        products.add(new Product("Clean Code", "book", 45.99));
        products.add(new Product("Effective Java", "book", 55.50));
        products.add(new Product("Design Patterns", "book", 120.00));
        products.add(new Product("Refactoring", "book", 89.99));
        products.add(new Product("Java Concurrency in Practice", "book", 110.75));
        products.add(new Product("Spring in Action", "book", 64.30));
        products.add(new Product("Head First Java", "book", 39.90));
        products.add(new Product("Domain-Driven Design", "book", 130.20));
        products.add(new Product("Algorithms", "book", 95.00));
        products.add(new Product("The Pragmatic Programmer", "book", 48.80));
        products.add(new Product("Introduction to Algorithms", "book", 150.00));
        products.add(new Product("Effective Modern C++", "book", 72.40));
        products.add(new Product("Artificial Intelligence: A Modern Approach", "book", 115.60));
        products.add(new Product("Operating System Concepts", "book", 99.99));
        products.add(new Product("Computer Networks", "book", 105.45));
        products.add(new Product("Database System Concepts", "book", 87.25));
        products.add(new Product("Microservices Patterns", "book", 102.10));
        products.add(new Product("Cracking the Coding Interview", "book", 44.95));
        products.add(new Product("Code Complete", "book", 108.99));
        products.add(new Product("Software Engineering", "book", 76.15));
        products.add(new Product("Baby Bottle Set", "baby", 18.99));
        products.add(new Product("Soft Plush Bear", "baby", 12.50));
        products.add(new Product("Baby Stroller", "baby", 149.99));
        products.add(new Product("Infant Car Seat", "baby", 189.00));
        products.add(new Product("Baby Blanket", "baby", 24.90));
        products.add(new Product("Teething Ring", "baby", 7.99));
        products.add(new Product("Baby Monitor", "baby", 99.99));
        products.add(new Product("Diaper Bag", "baby", 39.50));
        products.add(new Product("Baby High Chair", "baby", 129.99));
        products.add(new Product("Pacifier Pack", "baby", 9.99));
        products.add(new Product("Boys Sneakers", "boys", 49.90));
        products.add(new Product("Boys Jacket", "boys", 79.99));
        products.add(new Product("Boys Jeans", "boys", 34.50));
        products.add(new Product("Boys Backpack", "boys", 27.80));

        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer("Mario Rossi", 1));
        customers.add(new Customer("Luca Bianchi", 2));
        customers.add(new Customer("Giulia Verdi", 1));
        customers.add(new Customer("Anna Neri", 3));
        customers.add(new Customer("Paolo Gialli", 2));
        customers.add(new Customer("Sara Conti", 1));

        List<Order> orders = new ArrayList<>();

        orders.add(new Order(
                "created",
                LocalDate.of(2026, 1, 10),
                List.of(products.get(0), products.get(2), products.get(5)),
                customers.get(0)
        ));

        orders.get(0).setDeliveryDate(LocalDate.of(2026, 3, 17));
        orders.get(0).setStatus("delivered");

        orders.add(new Order(
                "shipped",
                LocalDate.of(2026, 1, 15),
                List.of(products.get(1), products.get(4), products.get(10)),
                customers.get(1)
        ));

        orders.add(new Order(
                "processing",
                LocalDate.of(2026, 1, 20),
                List.of(products.get(3), products.get(7)),
                customers.get(2)
        ));

        orders.add(new Order(
                "created",
                LocalDate.of(2026, 2, 5),
                List.of(products.get(20), products.get(21), products.get(24)),
                customers.get(3)
        ));

        orders.add(new Order(
                "shipped",
                LocalDate.of(2026, 2, 11),
                List.of(products.get(22), products.get(25)),
                customers.get(4)
        ));

        orders.add(new Order(
                "processing",
                LocalDate.of(2026, 2, 18),
                List.of(products.get(23), products.get(26), products.get(29)),
                customers.get(5)
        ));

        orders.add(new Order(
                "created",
                LocalDate.of(2026, 3, 2),
                List.of(products.get(30), products.get(31)),
                customers.get(0)
        ));

        orders.add(new Order(
                "shipped",
                LocalDate.of(2026, 3, 8),
                List.of(products.get(32), products.get(33)),
                customers.get(1)
        ));

        orders.add(new Order(
                "processing",
                LocalDate.of(2026, 3, 12),
                List.of(products.get(34), products.get(30), products.get(33)),
                customers.get(2)
        ));

        //raggruppare ordini per cliente
        Map<Customer, List<Order>> ordersByCustomers = orders.stream().collect(Collectors.groupingBy(Order::getCustomer));
        ordersByCustomers.forEach((customer, ordersByCustomer) -> System.out.println(customer + ": " + ordersByCustomer));

        //partendo dal risultato precedente
        Map<Customer, Double> totalSpendPerCustomer = ordersByCustomers.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, value -> value.getValue().stream().mapToDouble(order -> order.getOrderTotal()).sum()));
        totalSpendPerCustomer.forEach((customer, totalSpend) -> System.out.println(customer.getName() + " - Total: " + totalSpend));

        // altra versione partendo da orders
        Map<Customer, Double> totalSpendPerCustomer2 = orders.stream().collect(Collectors.groupingBy((Order::getCustomer), Collectors.summingDouble(Order::getOrderTotal)));
        totalSpendPerCustomer2.forEach((customer, totalSpend) -> System.out.println(customer.getName() + " - Total: " + totalSpend));

        //prodotti più costosi
        List<Product> moreExpensiveProducts = products.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).limit(10).toList();
        moreExpensiveProducts.forEach(product -> System.out.println(product.getCategory() + " - " + product.getName() + " - " + product.getPrice()));

        OptionalDouble averageOrd = orders.stream().mapToDouble(Order::getOrderTotal).average();
        double averageOrder = 0;
        if (averageOrd.isPresent()) {
            averageOrder = averageOrd.getAsDouble();
        }
        System.out.println(MessageFormat.format("La media degli importi degli ordini è {0,number,#0.00} €", averageOrder));

        // raggruppo i prodotti per categoria e sommo i prezzi
        Map<String, Double> cumulativePricePerCategory = products.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(product -> product.getPrice())));
        System.out.println(cumulativePricePerCategory);
    }
}
