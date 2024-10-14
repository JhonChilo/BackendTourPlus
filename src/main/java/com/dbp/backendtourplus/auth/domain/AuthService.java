package com.dbp.backendtourplus.auth.domain;

import com.dbp.backendtourplus.auth.dto.JwtAuthResponse;
import com.dbp.backendtourplus.auth.dto.LoginReq;
import com.dbp.backendtourplus.auth.dto.RegisterReq;
import com.dbp.backendtourplus.auth.exceptions.UserAlreadyExistException;
import com.dbp.backendtourplus.config.JwtService;
import com.dbp.backendtourplus.user.domain.Role;
import com.dbp.backendtourplus.user.domain.User;
import com.dbp.backendtourplus.user.infrastructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper = new ModelMapper();
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
    }

    public JwtAuthResponse login(LoginReq req) {
        Optional<User> user = userRepository.findByEmail(req.getEmail());

        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");

        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwtService.generateToken(user.get()));
        return response;
    }

    public JwtAuthResponse register(RegisterReq req) {
        Optional<User> user = userRepository.findByEmail(req.getEmail());
        if (user.isPresent()) throw new UserAlreadyExistException("Email is already registered");

        User newUser = new User();
        newUser.setFirstname(req.getFirstname());
        newUser.setLastname(req.getLastname());
        newUser.setEmail(req.getEmail());
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));
        newUser.setRole(Role.USER);

        userRepository.save(newUser);


        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwtService.generateToken(newUser));
        return response;
    }
}