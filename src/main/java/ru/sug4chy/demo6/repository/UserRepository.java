package ru.sug4chy.demo6.repository;

import ru.sug4chy.demo6.model.User;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserRepository implements Closeable {

    protected final Connection connection;

    public UserRepository() {
        this.connection = getPostgresqlConnection();
    }

    private static Connection getPostgresqlConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/java_db";
            var authProperties = new Properties();
            authProperties.put("user", "postgres");
            authProperties.put("password", "postGet5243");

            return DriverManager.getConnection(url, authProperties);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private ResultSet executeQuery(String query) {
        ResultSet result;
        try {
            var statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public User getUserByLogin(String login) {
        try {
            var result = executeQuery("select * from users u where u.login = '" + login + "'");
            if (!result.next()) {
                return null;
            }

            return new User(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
