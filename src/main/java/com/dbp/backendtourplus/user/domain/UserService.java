package com.dbp.backendtourplus.user.domain;

import com.dbp.backendtourplus.email.domain.EmailService;
import com.dbp.backendtourplus.email.event.EmailEvent;
import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.user.dto.UserDto;
import com.dbp.backendtourplus.user.exceptions.UserAlreadyExistsException;
import com.dbp.backendtourplus.user.exceptions.UserNotFoundException;
import com.dbp.backendtourplus.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private EmailService emailService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsService userDetailsService() {
        return username -> userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {

        user = userRepository.save(user);

        String subject = "Welcome to TourPlus!";
        String content = "Thank you for registering. Your account has been created successfully.";

        eventPublisher.publishEvent(new EmailEvent(user.getEmail(), subject, content));

        return user;
    }

    public User updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        user.setRole(userDto.getRole());
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}


