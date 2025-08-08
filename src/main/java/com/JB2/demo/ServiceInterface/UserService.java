package com.JB2.demo.ServiceInterface;

import java.util.List;
import java.util.Optional;

import com.JB2.demo.entity.User;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> updateUser(User user);

    void deleteUser(Long id);

    boolean isUserExist(Long userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    // add more methods here related user service [logic]
    User getUserByEmail(String email);

}
