package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsersService {
    List<UserDto> getAllUsers();

    void saveUser(Map<String, String> pool);

    Optional<User> findByEmail(String email);
}
