package org.example.controller;

import org.example.dto.RegistrationRequestDto;
import org.example.model.Adopter;
import org.example.model.Message;
import org.example.model.Shelter;
import org.example.model.User;
import org.example.repository.AdopterRepository;
import org.example.repository.ShelterRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdopterRepository adopterRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");

            if (username == null || password == null) {
                return ResponseEntity.badRequest().body("Username and password are required");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            User userDetails = userService.login(username, password);

            // Check user role
            String role = userDetails.getRole().contains("SHELTER") ? "SHELTER" : "ADOPTER";

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("tokenType", "Bearer");
            response.put("user", userDetails);
            response.put("role", role);
            response.put("message", "Login successful");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequestDto registrationRequest) {
        try {
            User user;
            if ("SHELTER".equalsIgnoreCase(registrationRequest.getUserType())) {
                Shelter shelter = new Shelter();
                shelter.setShelterName(registrationRequest.getShelterName());
                shelter.setLocation(registrationRequest.getLocation());
                shelter.setWebSite(registrationRequest.getWebSite());
                shelter.setOperatingHours(registrationRequest.getOperatingHours());
                shelter.setDescription(registrationRequest.getDescription());
                shelter.setRole("ROLE_SHELTER");
                user = shelter;
            } else {
                Adopter adopter = new Adopter();
                adopter.setHousingType(registrationRequest.getHousingType());
                adopter.setRole("ROLE_ADOPTER");
                user = adopter;
            }

            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setEmail(registrationRequest.getEmail());
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setAddress(registrationRequest.getAddress());
            user.setPhone(registrationRequest.getPhone());

            User savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            userService.logout();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> updateProfile(@RequestBody Map<String, String> profileData) {
        String email = profileData.get("email");
        String firstName = profileData.get("firstName");
        String lastName = profileData.get("lastName");
        String address = profileData.get("address");
        String phone = profileData.get("phone");

        userService.updateProfile(email, firstName, lastName, address, phone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        if (userService.changePassword(oldPassword, newPassword)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        List<User> users = userService.searchUsers(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/messages")
    public ResponseEntity<Message> sendMessage(
            @PathVariable Long userId,
            @RequestBody Message message) {
        Message sentMessage = userService.sendMessage(userId, message);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserList() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long userId) {
        List<Message> messages = userService.getMessages(userId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}