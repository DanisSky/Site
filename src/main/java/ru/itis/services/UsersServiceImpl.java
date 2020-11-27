package ru.itis.services;

import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public void saveUser(Map<String, String> pool) {
        User user = User.builder()
                .firstName(pool.get("first_name"))
                .lastName(pool.get("last_name"))
                .hashPassword(pool.get("hash_password"))
                .email(pool.get("email"))
                .phone(pool.get("phone"))
                .build();
        usersRepository.save(user);
    }

    @Override
    public Optional<String> getRole(User user) {
        if (usersRepository.getRole(user.getId()).isPresent()){
            return Optional.of("admin");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

}
