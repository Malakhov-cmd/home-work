package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import com.sbrf.reboot.repository.statements.Statements;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerH2Repository implements CustomerRepository {

    private final static String JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/my_db";

    private final static String USER = "sa";
    private final static String PASS = "";

    private Long id = 0L;

    public CustomerH2Repository() {
    }

    private Long generateId() {
        return this.id += 1;
    }

    public void createCustomerTable() {
        System.out.println("Create table");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement(
                     String.valueOf(Statements.valueOf("createStatement").getState())
             )
        ) {
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Ошибка исполнения запроса");
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        System.out.println("Select customer");

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     String.valueOf(Statements.valueOf("selectAllStatement").getState()));
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                customers.add(new Customer(id, name, email));
            }
            return customers;
        } catch (SQLException e) {
            System.err.println("Ошибка исполнения запроса");
            return customers;
        }
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        System.out.println("Insert customer");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     String.valueOf(Statements.valueOf("insertStatement").getState())
             )
        ) {
            preparedStatement.setLong(1, generateId());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, eMail);

           preparedStatement.execute();
           return true;
        } catch (SQLException e) {
            System.err.println("Ошибка исполнения запроса");
            return false;
        }
    }

    public void deleteCustomer(Long id) {
        System.out.println("Delete customer");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     String.valueOf(Statements.valueOf("deleteCustomerById").getState())
             )
        ) {
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Ошибка исполнения запроса");
        }
    }

    public void deleteTableCustomer() {
        System.out.println("Delete table");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = connection.prepareStatement(
                     String.valueOf(Statements.valueOf("deleteCustomersTable").getState())
             )
        ) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


