package ru.sug4chy.demo6.model;

public class User {

    private long id;
    private final String login;
    private final String password;
    private final String email;

    public User(long id, String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}