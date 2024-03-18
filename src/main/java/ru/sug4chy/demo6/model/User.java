package ru.sug4chy.demo6.model;

public class User {

    private long id;
    private String login;
    private String password;
    private String email;

    public User(long id, String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
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