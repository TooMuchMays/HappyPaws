package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    Optional<User> findByUsername(String username);

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if username exists
    boolean existsByUsername(String username);

    // Check if email exists
    boolean existsByEmail(String email);

    // Search users by username or email (case insensitive)
    List<User> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String usernameQuery,
            String emailQuery
    );

    // Find users by role
    List<User> findByRole(String role);

    // Search users by first name or last name
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<User> searchByName(@Param("query") String query);

    // Save user
    @Override
    User save(User user);

    // Find user by ID
    @Override
    Optional<User> findById(Long id);

    // Delete user
    @Override
    void deleteById(Long id);
}