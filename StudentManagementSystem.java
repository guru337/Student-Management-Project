/*Comments added*/
/*DBMS Example */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String name;
    int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber;
    }
}

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        // Load existing students from file
        loadStudentsFromFile(students);

        // Menu
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner, students);
                    break;
                case 2:
                    displayStudents(students);
                    break;
                case 3:
                    saveStudentsToFile(students);
                    System.out.println("Exiting the program. Data saved to file.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private static void loadStudentsFromFile(List<Student> students) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                int rollNumber = Integer.parseInt(parts[1].trim());
                students.add(new Student(name, rollNumber));
            }
        } catch (IOException | NumberFormatException e) {
            // Ignore errors, as the file might not exist initially
        }
    }

    private static void saveStudentsToFile(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.name + ", " + student.rollNumber);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addStudent(Scanner scanner, List<Student> students) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();

        students.add(new Student(name, rollNumber));
        System.out.println("Student added successfully!");
    }

    private static void displayStudents(List<Student> students) {
        System.out.println("\nStudent Information:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
