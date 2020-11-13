package ru.itis.repositories;

import ru.itis.models.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    ArrayList<User> findAllByName(String name);
    Optional<User> findByEmail(String email);
    void updatePassword(Long id, String hashPassword);
}
