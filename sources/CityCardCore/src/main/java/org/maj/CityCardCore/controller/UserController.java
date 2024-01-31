package org.maj.CityCardCore.controller;

import jakarta.websocket.server.PathParam;
import org.maj.CityCardCore.dto.LoginDto;
import org.maj.CityCardCore.dto.RegisterDto;
import org.maj.CityCardCore.enums.Role;
import org.maj.CityCardCore.model.User;
import org.maj.CityCardCore.dto.UserResponse;
import org.maj.CityCardCore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping("/makeuser")
    public ResponseEntity<User> makeUser(@RequestBody LoginDto loginData) {
        User user = new User(loginData.getLogin(), loginData.getPassword(), true);
        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginDto loginData) {
        User user = repository.findByLoginAndCheckIfPasswordIsCorrect(loginData.getLogin(), loginData.getPassword());
        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Login failed");
        return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> makeUser(@RequestBody RegisterDto registerData) {
        User user = new User(registerData.getLogin(),registerData.getPassword(), false);
        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
