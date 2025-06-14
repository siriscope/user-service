package com.siriscope.userservice.service;

import com.siriscope.userservice.model.EmailRegistrationRequest;
import com.siriscope.userservice.model.User;
import com.siriscope.userservice.model.UserDto;
import com.siriscope.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Transactional
    public UserDto registerUserUsingEmail(EmailRegistrationRequest emailRegistrationRequest) {
        System.out.println("Inside service method");
        User user = new User();
        user.setEmail(emailRegistrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(emailRegistrationRequest.getPassword()));
        User savedUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setUserName(savedUser.getUserName());
        userDto.setEmail(savedUser.getEmail());

        return userDto;
    }

    public String verifyLogin(User user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateJWTToken(user.getEmail());
        return "Failed";
    }
}
