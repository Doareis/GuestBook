package com.guestbook.service;

import com.guestbook.repository.UserRepository;
import com.guestbook.dto.UserDTO;
import com.guestbook.exception.EmailExistsException;
import com.guestbook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.guestbook.enums.UserRole.USER;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository repository;

    public User registerNewUserAccount(UserDTO userDTO) throws EmailExistsException {

        if (emailExists(userDTO.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: %s", userDTO.getEmail());
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        setBCryptPassword(user, userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setRole(USER.getName());

        saveUser(user);
        return user;
    }

    private void setBCryptPassword(User user, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
    }

    private void saveUser(User user) {
        repository.save(user);
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }

}
