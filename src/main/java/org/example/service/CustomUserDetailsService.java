package org.example.service;

import io.github.bucket4j.*;
import com.google.common.cache.*;
import org.example.exception.InvalidPasswordException;
import org.example.exception.TooManyRequestsException;
import org.example.exception.AccountLockedException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;
    private final Bucket bucket;
    private final LoadingCache<String, Integer> attemptsCache;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, PasswordValidator passwordValidator) {
        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;

        this.bucket = Bucket.builder()
                .addLimit(Bandwidth.classic(3, Refill.intervally(3, Duration.ofSeconds(1))))
                .build();

        this.attemptsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofDays(1))
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(String key) { return 0; }
                });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        checkBruteForce(username);

        try {
            User customUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            loginSucceeded(username);

            return org.springframework.security.core.userdetails.User.builder()
                    .username(customUser.getUsername())
                    .password(customUser.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority(customUser.getRole())))
                    .build();
        } catch (UsernameNotFoundException e) {
            loginFailed(username);
            throw e;
        }
    }

    private void checkBruteForce(String username) {
        if (!bucket.tryConsume(1)) {
            throw new TooManyRequestsException("Too many login attempts");
        }

        int attempts = attemptsCache.getUnchecked(username);
        if (attempts >= 5) {
            throw new AccountLockedException("Account is locked");
        }
    }

    private void loginSucceeded(String username) {
        attemptsCache.invalidate(username);
    }

    private void loginFailed(String username) {
        int attempts = attemptsCache.getUnchecked(username);
        attemptsCache.put(username, attempts + 1);
    }

    public void validatePasswordStrength(String password) {
        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (!result.isValid()) {
            throw new InvalidPasswordException("Password does not meet security requirements");
        }
    }
}