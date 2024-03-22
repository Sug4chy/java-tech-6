package ru.sug4chy.demo6.service;

import ru.sug4chy.demo6.model.User;
import ru.sug4chy.demo6.repository.UserRepository;

public class UserService {

    public UserService() {
    }

    public void addNewUser(User user) {
        if (user == null) {
            return;
        }
        try (var userRepository = new UserRepository()) {
            userRepository.addUser(user);
        }
    }

    public User getUserByLogin(String login) {
        User user;
        try (var userRepository = new UserRepository()) {
            user = userRepository.getUserByLogin(login);
        }
        return user;
    }
}