package com.classwork.view;

import java.util.List;
import java.util.Scanner;

import com.classwork.models.Student;

public class StudentView {
    private Scanner scanner = new Scanner(System.in);

    public int showMenu() {
        System.out.println("\n==== Student Management Menu ====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public Student getStudentDetails() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        return new Student(id, name, department, marks);
    }

    public int getStudentIdForUpdateOrDelete(String action) {
        System.out.print("Enter Student ID to " + action + ": ");
        return scanner.nextInt();
    }

    public void showStudents(List<Student> students) {
        System.out.println("\n--- Student List ---");
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentId() + ", Name: " + student.getName() +
                    ", Dept: " + student.getDepartment() + ", Marks: " + student.getMarks());
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
