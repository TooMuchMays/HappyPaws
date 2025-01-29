package org.example.service;

import org.example.model.Message;
import org.example.model.User;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

public interface UserActions {
    Optional<User> findByUsername(String username);
    User login(String username, String password) throws AuthenticationException;
    void logout();
    void updateProfile(String email, String firstName, String lastName, String address, String phone);
    boolean changePassword(String oldPassword, String newPassword);
    Optional<User> findById(Long userId);
    List<User> searchUsers(String query);
    User createUser(User user);
    void deleteUser(Long userId);
    Message sendMessage(Long userId, Message message);
    List<Message> getMessages(Long userId);
    List<User> getAllUsers();
    boolean isLoggedIn();
}