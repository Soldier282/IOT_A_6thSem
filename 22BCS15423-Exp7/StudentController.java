// StudentController.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private Connection connection;
    private StudentView view;

    public StudentController(StudentView view) {
        this.view = view;
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            // Update with your database URL, username, and password
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        String query = "INSERT INTO Students (Name, Department, Marks) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDepartment());
            stmt.setFloat(3, student.getMarks());
            stmt.executeUpdate();
            view.displayMessage("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Student student = new Student(rs.getInt("StudentID"), rs.getString("Name"),
                        rs.getString("Department"), rs.getFloat("Marks"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}