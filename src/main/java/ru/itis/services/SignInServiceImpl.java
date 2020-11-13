package ru.itis.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {
    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Boolean isSignIn(String email, String password) {
        Optional<User> user = usersRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getHashPassword())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
