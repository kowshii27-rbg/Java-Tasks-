
// Interfaces used by anonymous & local classes

interface Shape {
    double area();
    default String describe() { return "Shape with area = " + String.format("%.2f", area()); }
}

interface Greeting {
    void greet(String name);
}


// Outer class — hosts the member inner class and static nested class
class Engine {

    private String brand;
    private int    horsepower;

    Engine(String brand, int horsepower) {
        this.brand      = brand;
        this.horsepower = horsepower;
    }

    //Member Inner Class
    //Tied to an instance of Engine
    //Can access outer instance fields (brand, horsepower) directly
    class Turbo {
        private int boostPsi;

        Turbo(int boostPsi) { this.boostPsi = boostPsi; }

        void status() {
            // accesses outer class fields without any qualifier
            System.out.println("  Engine : " + brand + " | " + horsepower + " hp");
            System.out.println("  Turbo  : " + boostPsi + " psi boost");
            System.out.println("  Total  : ~" + (horsepower + boostPsi * 10) + " hp (estimated)");
        }
    }

    //Static Nested Class
    //Declared with 'static' — no link to an outer instance
    //Cannot access outer instance fields; works like a top-level class
    //Used for logically grouped helper/utility types
    static class Specs {
        private String fuelType;
        private int    cylinders;

        Specs(String fuelType, int cylinders) {
            this.fuelType  = fuelType;
            this.cylinders = cylinders;
        }

        void print() {
            System.out.println("  Fuel     : " + fuelType);
            System.out.println("  Cylinders: " + cylinders);
        }
    }

    void printBrand() {
        System.out.println("  Brand: " + brand);
    }
}

public class InnerClassDemo {

    //Helper to show anonymous class in action
    static void printShapeInfo(Shape s) {
        System.out.println("  " + s.describe());
    }

    public static void main(String[] args) {

        //1. Member Inner Class
        System.out.println("┌─ 1. Member Inner Class ─────────────────────────────┐");
        Engine engine = new Engine("BMW", 300);
        Engine.Turbo turbo = engine.new Turbo(15);   // needs outer instance
        turbo.status();
        System.out.println();

        //2. Static Nested Class
        System.out.println("┌─ 2. Static Nested Class ────────────────────────────┐");
        Engine.Specs specs = new Engine.Specs("Petrol", 6);  // no outer instance needed
        specs.print();
        System.out.println();

        //3. Local Class
        //Defined inside a method block
        //Visible only within that block
        //Can access effectively-final local variables from the enclosing method
        System.out.println("┌─ 3. Local Class ────────────────────────────────────┐");

        double taxRate = 0.18;   // effectively final — captured by local class

        class Invoice {
            private String item;
            private double price;

            Invoice(String item, double price) {
                this.item  = item;
                this.price = price;
            }

            void print() {
                double tax   = price * taxRate;     // captures enclosing local var
                double total = price + tax;
                System.out.printf("  Item : %-10s  Price: $%6.2f%n", item, price);
                System.out.printf("  Tax  : %.0f%%           Tax  : $%6.2f%n", taxRate * 100, tax);
                System.out.printf("  %-20s Total: $%6.2f%n", "", total);
            }
        }

        new Invoice("Laptop",  899.00).print();
        System.out.println();
        new Invoice("Monitor", 349.00).print();
        System.out.println();

        //4. Anonymous Class
        // A one-off implementation of an interface or abstract class
        // Defined and instantiated in a single expression
        // No name — cannot be reused elsewhere
        System.out.println("┌─ 4. Anonymous Class ────────────────────────────────┐");

        // Anonymous class implementing Shape (circle)
        Shape circle = new Shape() {
            private final double radius = 7.0;

            @Override
            public double area() { return Math.PI * radius * radius; }

            @Override
            public String describe() { return "Circle  r=" + radius + " → area=" + String.format("%.2f", area()); }
        };

        // Anonymous class implementing Shape (rectangle)
        Shape rectangle = new Shape() {
            private final double w = 5.0, h = 9.0;

            @Override
            public double area() { return w * h; }

            @Override
            public String describe() { return "Rectangle " + w + "x" + h + " → area=" + String.format("%.2f", area()); }
        };

        // Anonymous class implementing Greeting
        Greeting formal = new Greeting() {
            @Override
            public void greet(String name) {
                System.out.println("  Good day, " + name + ". Welcome aboard.");
            }
        };

        printShapeInfo(circle);
        printShapeInfo(rectangle);
        formal.greet("Alice");
        System.out.println();

        //Side-by-side comparison 
        System.out.println("┌─ Summary ───────────────────────────────────────────┐");
        System.out.println("  Member inner  : engine.new Turbo(15)   — has outer 'this'");
        System.out.println("  Static nested : new Engine.Specs(...)  — no outer 'this'");
        System.out.println("  Local class   : defined in method block — captures locals");
        System.out.println("  Anonymous     : new Interface() { ... } — no name, one-off");
    }
}