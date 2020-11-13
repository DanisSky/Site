package ru.itis.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.ChangePasswordForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

public class ChangePasswordServiceImpl implements ChangePasswordService {
    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public ChangePasswordServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean changePassword(ChangePasswordForm chPasForm) {
        Optional<User> user = usersRepository.findById(chPasForm.getUserId());
        System.out.println("id" + chPasForm.getUserId());
        if (chPasForm.getNewPassword().equals(chPasForm.getRepeatedNewPassword())) {
            System.out.println("first if");
            if (user.isPresent() &&
                    passwordEncoder.matches(chPasForm.getOldPassword(), user.get().getHashPassword())) {
                System.out.println("second if");
                usersRepository.updatePassword(user.get().getId(), passwordEncoder.encode(chPasForm.getNewPassword()));
                return true;
            }
        }
        return false;
    }
}
