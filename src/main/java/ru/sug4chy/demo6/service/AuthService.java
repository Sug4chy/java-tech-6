package ru.sug4chy.demo6.service;

import ru.sug4chy.demo6.dto.UserDto;
import ru.sug4chy.demo6.repository.UserRepository;
public class AuthService {

    private final UserRepository userRepository;

    public AuthService() {
        this.userRepository = new UserRepository();
    }

    public void addNewUser(UserDto user) {
        //loginToProfile.put(user.getLogin(), user);
    }

    public UserDto getUserByLogin(String login) {
        var user = userRepository.getUserByLogin(login);
        return new UserDto(user.getLogin(), user.getPassword(), user.getEmail());
    }
}