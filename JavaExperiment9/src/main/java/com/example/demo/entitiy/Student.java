package com.example.demo.entitiy;
import jakarta.persistence.*;

@Table(name = "student")
@Entity
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
	private int id;
	private String name;
	private int courseFee;
	private int semester;
	
	public Student() {
		
	}
	public Student(int id, String name, int courseFee, int semester) {
		super();
		this.id = id;
		this.name = name;
		this.courseFee = courseFee;
		this.semester = semester;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", courseFee=" + courseFee + ", semester=" + semester + "]";
	}
	
	
}
