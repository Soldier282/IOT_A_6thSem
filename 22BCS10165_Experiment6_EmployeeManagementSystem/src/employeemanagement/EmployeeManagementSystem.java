package employeemanagement;

import java.io.*;
import java.util.*;

class Employee {
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

 public String toString() {
     return id + "," + name + "," + designation + "," + salary;
 }
}

public class EmployeeManagementSystem {
 private static final String FILE_NAME = "employees.txt";

 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     while (true) {
         System.out.println("\nMenu:");
         System.out.println("1. Add Employee");
         System.out.println("2. Display All Employees");
         System.out.println("3. Exit");
         System.out.print("Enter your choice: ");
         int choice = sc.nextInt();
         sc.nextLine();

         switch (choice) {
             case 1:
                 addEmployee(sc);
                 break;
             case 2:
                 displayEmployees();
                 break;
             case 3:
                 System.out.println("Exiting...");
                 sc.close();
                 System.exit(0);
             default:
                 System.out.println("Invalid choice. Try again.");
         }
     }
 }

 private static void addEmployee(Scanner sc) {
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
         System.out.print("Enter Employee Name: ");
         String name = sc.nextLine();
         System.out.print("Enter Employee ID: ");
         int id = sc.nextInt();
         sc.nextLine();
         System.out.print("Enter Designation: ");
         String designation = sc.nextLine();
         System.out.print("Enter Salary: ");
         double salary = sc.nextDouble();

         Employee emp = new Employee(name, id, designation, salary);
         writer.write(emp.toString());
         writer.newLine();
         System.out.println("Employee added successfully!");
     } catch (IOException e) {
         System.out.println("Error writing to file: " + e.getMessage());
     }
 }

 private static void displayEmployees() {
     try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
         String line;
         System.out.println("\nEmployee Details:");
         while ((line = reader.readLine()) != null) {
             String[] parts = line.split(",");
             System.out.println("ID: " + parts[0] + ", Name: " + parts[1] + ", Designation: " + parts[2] + ", Salary: " + parts[3]);
         }
     } catch (IOException e) {
         System.out.println("Error reading file: " + e.getMessage());
     }
 }
}
