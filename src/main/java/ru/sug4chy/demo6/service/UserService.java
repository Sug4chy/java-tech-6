package ru.sug4chy.demo6.service;

import ru.sug4chy.demo6.dto.UserDto;
import ru.sug4chy.demo6.repository.UserRepository;

public class UserService{

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void addNewUser(UserDto user) {
        if (user == null) {
            return;
        }
        userRepository.addUser(user);
    }

    public UserDto getUserByLogin(String login) {
        var user = userRepository.getUserByLogin(login);
        if (user == null) {
            return null;
        }
        return new UserDto(user.getLogin(), user.getPassword(), user.getEmail());
    }
}