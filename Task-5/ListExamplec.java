import java.util.*;

// Student class definition
class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Overriding toString to print meaningful data instead of memory addresses
    @Override
    public String toString() {
        return id + " " + name;
    }
}

public class ListExamplec {
    public static void main(String[] args) {
        // Creating a List to hold Student objects
        List<Student> students = new ArrayList<>();

        // Adding new Student objects to the list
        students.add(new Student(1, "Kowshik"));
        students.add(new Student(2, "Suraj"));
        students.add(new Student(3, "Rahul"));
        students.add(new Student(4, "Rajesh"));

        // Iterating through the list and printing each student
        System.out.println("Student List:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}