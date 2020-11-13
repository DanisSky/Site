package ru.itis.services;

import ru.itis.dto.ChangePasswordForm;

public interface ChangePasswordService {
    boolean changePassword(ChangePasswordForm changePasswordForm);
}
