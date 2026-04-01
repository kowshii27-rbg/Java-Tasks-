import java.io.*;
import java.lang.annotation.*;

//Custom Annotation 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface AppInfo {
    String author() default "Unknown";
    String version() default "1.0";
}

//Enum
enum Status {
    ACTIVE, INACTIVE, PENDING;

    public boolean isActive() {
        return this == ACTIVE;
    }
}

//Serializable Class with Annotation
@AppInfo(author = "Kowshik", version = "2.0")
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int    id;
    Status status;

    Employee(String name, int id, Status status) {
        this.name   = name;
        this.id     = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + ", status=" + status + "}";
    }
}

public class Demo {

    static final String TEXT_FILE   = "employee.txt";
    static final String SERIAL_FILE = "employee.ser";

    public static void main(String[] args) throws Exception {

        // 1. Enum usage
        System.out.println("=== Enum ===");
        Employee emp = new Employee("Kowshik", 101, Status.ACTIVE);
        System.out.println("Created : " + emp);
        System.out.println("Is active? " + emp.status.isActive());

        // 2. Annotation reading
        System.out.println("\n=== Annotation ===");
        AppInfo info = Employee.class.getAnnotation(AppInfo.class);
        System.out.println("Author : " + info.author());
        System.out.println("Version: " + info.version());

        // 3. File write & read
        System.out.println("\n=== File I/O ===");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TEXT_FILE))) {
            bw.write(emp.toString());
        }
        System.out.println("Written to " + TEXT_FILE);

        try (BufferedReader br = new BufferedReader(new FileReader(TEXT_FILE))) {
            System.out.println("Read back: " + br.readLine());
        }

        // 4. Serialization
        System.out.println("\n=== Serialization ===");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIAL_FILE))) {
            oos.writeObject(emp);
        }
        System.out.println("Serialized to " + SERIAL_FILE);

        // 5. Deserialization
        System.out.println("\n=== Deserialization ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIAL_FILE))) {
            Employee loaded = (Employee) ois.readObject();
            System.out.println("Deserialized: " + loaded);
            System.out.println("Status enum : " + loaded.status + " | ordinal=" + loaded.status.ordinal());
        }
    }
}