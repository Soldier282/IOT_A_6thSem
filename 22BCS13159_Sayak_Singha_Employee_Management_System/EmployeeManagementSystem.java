import java.io.*;
import java.util.Scanner;

class Employee implements Serializable {
    private String name;
    private int id;
    private String designation;
    private double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    public String toFileString() {
        return id + "," + name + "," + designation + "," + salary;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4)
            return null;
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String designation = parts[2];
        double salary = Double.parseDouble(parts[3]);
        return new Employee(name, id, designation, salary);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_PATH = "file.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Employee Management Menu =====");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void addEmployee(Scanner scanner) {
        try {
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Employee Designation: ");
            String designation = scanner.nextLine();

            System.out.print("Enter Employee Salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            Employee emp = new Employee(name, id, designation, salary);

            FileWriter fos = new FileWriter(FILE_PATH, true);
            BufferedWriter bw = new BufferedWriter(fos);
            bw.write(emp.toFileString());
            bw.newLine();
            bw.close();
            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    private static void displayAllEmployees() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("No employees found.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            System.out.println("\n--- List of Employees ---");
            while ((line = br.readLine()) != null) {
                Employee emp = Employee.fromFileString(line);
                if (emp != null) {
                    System.out.println(emp);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }
}
