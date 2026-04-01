import java.util.*;
import java.util.function.*;
import java.util.stream.*;

//Domain Object 
class Product {
    String name;
    String category;
    double price;
    int    stock;

    Product(String name, String category, double price, int stock) {
        this.name     = name;
        this.category = category;
        this.price    = price;
        this.stock    = stock;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-11s | $%6.2f | stock: %d", name, category, price, stock);
    }
}

public class FunctionalDemo {

    public static void main(String[] args) {

        // Supplier: produces a list of products
        Supplier<List<Product>> catalog = () -> Arrays.asList(
            new Product("Laptop",     "Electronics", 999.99, 5),
            new Product("Headphones", "Electronics", 149.99, 0),
            new Product("Desk",       "Furniture",   349.00, 3),
            new Product("Chair",      "Furniture",   199.50, 8),
            new Product("Notebook",   "Stationery",   4.99, 50),
            new Product("Pen",        "Stationery",   1.49, 200),
            new Product("Monitor",    "Electronics", 499.99, 2),
            new Product("Lamp",       "Furniture",    39.99, 12)
        );

        List<Product> products = catalog.get();

        //Predicate: reusable filter conditions
        Predicate<Product> isElectronics  = p -> p.category.equals("Electronics");
        Predicate<Product> isInStock      = p -> p.stock > 0;
        Predicate<Product> isAffordable   = p -> p.price < 500.0;

        //Function: transforms a Product to something else
        Function<Product, String>  toSummary    = p -> p.name + " ($" + String.format("%.2f", p.price) + ")";
        Function<Product, Double>  applyDiscount = p -> p.price * 0.90;   // 10% off

        //Consumer: prints with a label
        Consumer<String>  printLine  = line  -> System.out.println("  " + line);
        Consumer<Product> printProduct = p   -> System.out.println("  " + p);

        
        //Filter —in-stock electronics
       
        System.out.println("┌─ In-stock Electronics ──────────────────────────────┐");
        products.stream()
                .filter(isElectronics.and(isInStock))      // Predicate chaining
                .forEach(printProduct);                    // Consumer
        System.out.println();

        
        //Map — get summary strings for affordable products
        
        System.out.println("┌─ Affordable products (< $500) ──────────────────────┐");
        products.stream()
                .filter(isAffordable)
                .map(toSummary)                            // Function<Product,String>
                .forEach(printLine);                       // Consumer<String>
        System.out.println();

        
        //Map + Reduce — total value of in-stock inventory
        
        double totalValue = products.stream()
                .filter(isInStock)
                .mapToDouble(p -> p.price * p.stock)       // map to primitive stream
                .reduce(0.0, Double::sum);                 // reduce / fold

        System.out.println("┌─ Total in-stock inventory value ────────────────────┐");
        System.out.printf("  $%.2f%n%n", totalValue);

        
        //Full pipeline — discounted names of in-stock electronics, sorted
        
        System.out.println("┌─ In-stock electronics after 10% discount (sorted) ──┐");
        products.stream()
                .filter(isElectronics.and(isInStock))
                .map(p -> new AbstractMap.SimpleEntry<>(p.name, applyDiscount.apply(p)))
                .sorted(Map.Entry.comparingByValue())
                .map(e -> String.format("%-12s → $%.2f", e.getKey(), e.getValue()))
                .forEach(printLine);
        System.out.println();

        //Collect — group products by category
        
        System.out.println("┌─ Products grouped by category ──────────────────────┐");
        Map<String, List<Product>> byCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        byCategory.forEach((category, list) -> {
            System.out.println("  [" + category + "]");
            list.forEach(printProduct);
        });
        System.out.println();

        //Predicate negate — out-of-stock items

        System.out.println("┌─ Out-of-stock items ─────────────────────────────────┐");
        products.stream()
                .filter(isInStock.negate())                // Predicate.negate()
                .map(toSummary)
                .forEach(printLine);
        System.out.println();

        
        //Statistics — summary stats on prices
        DoubleSummaryStatistics stats = products.stream()
                .mapToDouble(p -> p.price)
                .summaryStatistics();

        System.out.println("┌─ Price statistics ───────────────────────────────────┐");
        System.out.printf("  Count : %d%n",    stats.getCount());
        System.out.printf("  Min   : $%.2f%n", stats.getMin());
        System.out.printf("  Max   : $%.2f%n", stats.getMax());
        System.out.printf("  Avg   : $%.2f%n", stats.getAverage());
        System.out.printf("  Sum   : $%.2f%n", stats.getSum());
    }
}