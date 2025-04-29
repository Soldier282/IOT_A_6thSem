package JavaExperiment6;
import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String designation;
    private double salary;

    public Employee(String id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void show() {
        System.out.println("-------------------------------------------");
        System.out.println("ID          : " + id);
        System.out.println("Name        : " + name);
        System.out.println("Designation : " + designation);
        System.out.println("Salary      : ₹" + salary);
        System.out.println("-------------------------------------------");
    }
}

public class EmployeeManager {
    static final String FILE = "employees.dat";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int ch;

        while (true) {
            System.out.println("\n========== EMPLOYEE SYSTEM ==========");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
                ch = sc.nextInt();
                sc.nextLine(); // flush newline
            } else {
                System.out.println("Invalid input! Try again.");
                sc.next(); // skip invalid input
                continue;
            }

            switch (ch) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try between 1-3.");
            }
        }
    }

    static void addEmployee() {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        if (checkDuplicate(id)) {
            System.out.println("ID already exists. Try another.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Designation: ");
        String desg = sc.nextLine();

        double salary = 0;
        while (true) {
            System.out.print("Enter Salary: ");
            try {
                salary = Double.parseDouble(sc.nextLine());
                if (salary < 0) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Enter a valid positive salary.");
            }
        }

        Employee emp = new Employee(id, name, desg, salary);
        List<Employee> all = readAll();
        all.add(emp);
        writeAll(all);
        System.out.println("Employee saved successfully.");
    }

    static void displayEmployees() {
        List<Employee> all = readAll();
        if (all.isEmpty()) {
            System.out.println("No employee data found.");
        } else {
            System.out.println("\nAll Employees:");
            for (Employee e : all) {
                e.show();
            }
        }
    }

    static boolean checkDuplicate(String id) {
        for (Employee e : readAll()) {
            if (e.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    static List<Employee> readAll() {
        File file = new File(FILE);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Employee>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    static void writeAll(List<Employee> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}
