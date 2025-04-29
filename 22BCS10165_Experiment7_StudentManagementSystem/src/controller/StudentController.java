package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Student;

public class StudentController {
    private Connection conn;

    // Constructor to initialize the database connection
    public StudentController() {
        try {
            // Manually load the MySQL JDBC driver (optional but recommended)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC", "root", "forgetpassword");

            if (conn != null) {
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Unable to establish database connection.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    // Method to add a student to the database
    public void addStudent(Student student) {
        try {
            String sql = "INSERT INTO students (student_id, name, department, marks) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDepartment());
            ps.setDouble(4, student.getMarks());
            ps.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding student.");
            e.printStackTrace();
        }
    }

    // Method to view all students from the database
    public void viewStudents() {
        try {
            String sql = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("student_id") +
                        ", Name: " + rs.getString("name") +
                        ", Department: " + rs.getString("department") +
                        ", Marks: " + rs.getDouble("marks"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching students.");
            e.printStackTrace();
        }
    }

    // Method to delete a student from the database by student_id
    public void deleteStudent(int id) {
        try {
            String sql = "DELETE FROM students WHERE student_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student.");
            e.printStackTrace();
        }
    }
}
