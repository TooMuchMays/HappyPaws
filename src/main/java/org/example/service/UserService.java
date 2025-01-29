package org.example.service;

import org.example.model.Message;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserActions {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(String username, String password) throws AuthenticationException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            return userOptional.get();
        }
        throw new AuthenticationException("Invalid username or password");
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public void updateProfile(String email, String firstName, String lastName, String address, String phone) {
        // Implementation for updating profile
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        // Implementation for changing password
        return true;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> searchUsers(String query) {
        // Implementation for searching users
        return List.of();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Message sendMessage(Long userId, Message message) {
        // Implementation for sending message
        return null;
    }

    @Override
    public List<Message> getMessages(Long userId) {
        // Implementation for getting messages
        return List.of();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isLoggedIn() {
        // Implementation for checking if a user is logged in
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }
}