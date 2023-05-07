package com.wrona.northwnd.customers;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CustomerRepository {

    private final HikariDataSource hikariDataSource;


    public List<CustomerEntity> findAllCustomerByName(String name) throws SQLException {
        String query = "SELECT * FROM Customers";
        List<CustomerEntity> customers = new ArrayList<>();
        try (Connection connection = hikariDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                CustomerEntity customer = new CustomerEntity();
                customer.setId(results.getString("CustomerID"));
                customers.add(customer);
            }
        }

        return customers;
    }


}
