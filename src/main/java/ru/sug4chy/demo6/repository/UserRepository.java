package ru.sug4chy.demo6.repository;

import ru.sug4chy.demo6.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UserRepository {

    private static Connection connection;

    private static void createPostgresqlConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            var dbProperties = new Properties();
            try (var in = Files.newInputStream(Paths.get("D:\\JetBrains\\IdeaProjects\\demo6\\src\\main\\resources\\database.properties"))) {
                dbProperties.load(in);
            }

            String url = dbProperties.getProperty("url");
            String user = dbProperties.getProperty("user");
            String password = dbProperties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkConnection() {
        if (connection == null) {
            createPostgresqlConnection();
        }
    }

    private <T> T executeQuery(String query, ResultHandler<T> handler) {
        checkConnection();
        try {
            var statement = connection.createStatement();
            var result = statement.executeQuery(query);
            if (!result.next()) {
                return null;
            }

            return handler.handle(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void executeUpdate(String query) {
        checkConnection();
        try {
            var statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUserByLogin(String login) {
        return executeQuery("select * from users u where u.login = '" + login + "'",
                set -> new User(
                        set.getString("login"),
                        set.getString("password"),
                        set.getString("email")
                ));
    }

    public void addUser(User user) {
        executeUpdate("insert into users(login, password, email) values ('" + user.getLogin()
                + "', '" + user.getPassword() + "', '" + user.getEmail() + "')");
    }
}
