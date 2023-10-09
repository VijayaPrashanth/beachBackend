package com.example.moviebooking.user.view;

import com.example.moviebooking.user.UserService;
import com.example.moviebooking.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    private UserRepository userRepository;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    Map<String, Object> login(Principal principal) {
        String username = principal.getName();
        Map<String, Object> userDetails = new HashMap<>();

        userDetails.put("username", username);
        return userDetails;
    }

}
