package ru.sug4chy.demo6.service;

import ru.sug4chy.demo6.model.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static final Map<String, User> loginToProfile = new HashMap<>() {{
        put("12", new User("12", "1234", "123"));
    }};

    public void addNewUser(User user) {
        loginToProfile.put(user.getLogin(), user);
    }

    public User getUserByLogin(String login) {
        return loginToProfile.get(login);
    }
}
