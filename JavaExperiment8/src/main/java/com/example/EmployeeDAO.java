package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {
    public static List<Employee> getAllEmployees(){
        List<Employee> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from employee;");
            ResultSet rs = ps.executeQuery();   
            while (rs.next()){
                Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(emp);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static Employee getEmployeeById(int id) {
        Employee emp = null;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

}
