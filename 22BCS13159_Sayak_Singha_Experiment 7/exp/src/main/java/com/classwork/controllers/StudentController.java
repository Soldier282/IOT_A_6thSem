package com.classwork.controllers;

import java.sql.SQLException;
import java.util.List;

import com.classwork.models.Student;
import com.classwork.service.StudentDAO;
import com.classwork.view.StudentView;

public class StudentController {
    private StudentDAO dao;
    private StudentView view;

    public StudentController(StudentDAO dao, StudentView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        int choice;
        do {
            choice = view.showMenu();
            try {
                switch (choice) {
                    case 1:
                        Student newStudent = view.getStudentDetails();
                        dao.addStudent(newStudent);
                        view.showMessage("Student added successfully!");
                        break;
                    case 2:
                        List<Student> students = dao.getAllStudents();
                        view.showStudents(students);
                        break;
                    case 3:
                        int updateId = view.getStudentIdForUpdateOrDelete("update");
                        Student updatedStudent = view.getStudentDetails();
                        updatedStudent.setStudentId(updateId);
                        dao.updateStudent(updatedStudent);
                        view.showMessage("Student updated successfully!");
                        break;
                    case 4:
                        int deleteId = view.getStudentIdForUpdateOrDelete("delete");
                        dao.deleteStudent(deleteId);
                        view.showMessage("Student deleted successfully!");
                        break;
                    case 5:
                        view.showMessage("Exiting... Goodbye!");
                        break;
                    default:
                        view.showMessage("Invalid choice! Try again.");
                }
            } catch (SQLException e) {
                view.showMessage("Database Error: " + e.getMessage());
            }
        } while (choice != 5);
    }
}
