package com.example.moviebooking;

import com.example.moviebooking.user.repository.User;
import com.example.moviebooking.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
          if(userRepository.findByUsername("vijay_user").isEmpty()) {
              userRepository.save(new User("vijay_user", passwordEncoder.encode("Principal123$"),"admin"));
          }
        };
    }

}
