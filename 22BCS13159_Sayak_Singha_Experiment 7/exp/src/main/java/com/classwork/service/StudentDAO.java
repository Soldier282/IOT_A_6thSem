package com.classwork.service;

import com.classwork.models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) throws SQLException {
        this.connection = connection;
        initializeDatabase();
    }

    private void initializeDatabase() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                "student_id INT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "department VARCHAR(100), " +
                "marks DOUBLE" +
                ")";
        Statement stmt = connection.createStatement();
        stmt.execute(createTableSQL);
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (student_id, name, department, marks) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, student.getStudentId());
        pstmt.setString(2, student.getName());
        pstmt.setString(3, student.getDepartment());
        pstmt.setDouble(4, student.getMarks());
        pstmt.executeUpdate();
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Student student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("marks"));
            students.add(student);
        }
        return students;
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, department=?, marks=? WHERE student_id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getDepartment());
        pstmt.setDouble(3, student.getMarks());
        pstmt.setInt(4, student.getStudentId());
        pstmt.executeUpdate();
    }

    public void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, studentId);
        pstmt.executeUpdate();
    }
}
