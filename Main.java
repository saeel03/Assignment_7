import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

// Custom exception for uninitialized student
class UninitializedStudentException extends Exception {
    public UninitializedStudentException(String message) {
        super(message);
    }
}

// Student class
class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

// Generic container class for students
class GenericContainer<T> {
    private List<T> items;

    public GenericContainer() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void processItems(Consumer<T> processor) {
        for (T item : items) {
            processor.accept(item);
        }
    }

    public int getSize() {
        return items.size();
    }
}

// Main class with menu
public class Main {
    public static void main(String[] args) {
        GenericContainer<Student> studentContainer = new GenericContainer<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Adding a student
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    studentContainer.addItem(new Student(name, age));
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    // Displaying students
                    if (studentContainer.getSize() == 0) {
                        System.out.println("No students available.");
                    } else {
                        System.out.println("Students List:");
                        studentContainer.processItems(System.out::println);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
