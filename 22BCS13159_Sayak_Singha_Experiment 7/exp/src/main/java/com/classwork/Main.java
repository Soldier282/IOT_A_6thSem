package com.classwork;

import java.sql.Connection;
import java.sql.SQLException;

import com.classwork.controllers.StudentController;
import com.classwork.service.StudentDAO;
import com.classwork.view.StudentView;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Main {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb");
        config.setUsername("sa");
        config.setPassword("");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        try (HikariDataSource dataSource = new HikariDataSource(config);
                Connection connection = dataSource.getConnection()) {

            StudentDAO dao = new StudentDAO(connection);
            StudentView view = new StudentView();
            StudentController controller = new StudentController(dao, view);

            controller.run();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}