package com.siriscope.userservice.controller;

import com.siriscope.userservice.model.EmailRegistrationRequest;
import com.siriscope.userservice.model.User;
import com.siriscope.userservice.model.UserDto;
import com.siriscope.userservice.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siriscope/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/emailRegistration")
    public ResponseEntity<?> registerUsingEmail(@Valid @RequestBody EmailRegistrationRequest emailRegistrationRequest) {
        System.out.println("inside controller end point");
        UserDto registeredUser = userService.registerUserUsingEmail(emailRegistrationRequest);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return new ResponseEntity<>(userService.verifyLogin(user), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello(HttpSession httpSession) {
        return new ResponseEntity<>("hello " + httpSession.getId(), HttpStatus.OK);
    }
}
