package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entitiy.Student;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Add student using custom endpoint
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    @GetMapping("/all")
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentRepository.findById(id)
                .orElse(null);
    }

    // Update student
    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setCourseFee(updatedStudent.getCourseFee());
                    student.setSemester(updatedStudent.getSemester());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    updatedStudent.setId(id);
                    return studentRepository.save(updatedStudent);
                });
    }

    // Delete student
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
    }
}