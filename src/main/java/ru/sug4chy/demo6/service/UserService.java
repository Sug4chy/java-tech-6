package ru.sug4chy.demo6.service;

import ru.sug4chy.demo6.model.User;
import ru.sug4chy.demo6.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void addNewUser(User user) {
        if (user == null) {
            return;
        }
        userRepository.addUser(user);

    }

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
}