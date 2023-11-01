package com.example.moviebooking.user.registereduser;

import com.example.moviebooking.excpetions.EmailIDAlreadyExistsException;
import com.example.moviebooking.excpetions.PhoneNumberAlreadyExistsException;
import com.example.moviebooking.excpetions.UsernameAlreadyExistsException;
import com.example.moviebooking.user.repository.User;
import com.example.moviebooking.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository, UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.registeredUserRepository = registeredUserRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisteredUser register(RegisteredUserRequest registeredUserRequest) throws UsernameAlreadyExistsException, EmailIDAlreadyExistsException, PhoneNumberAlreadyExistsException {

        if(checkUsernameIsAlreadyExist(registeredUserRequest.getUsername()))
        {
            throw new UsernameAlreadyExistsException("username already exists");
        }

        if(registeredUserRepository.findByEmail(registeredUserRequest.getEmail()).isPresent())
        {
            throw new EmailIDAlreadyExistsException("email id already exists");
        }

        if(registeredUserRepository.findByPhonenumber(registeredUserRequest.getUsername()).isPresent())
        {
            throw new PhoneNumberAlreadyExistsException("phone number already exists");
        }

        User user = new User(registeredUserRequest.getUsername(),passwordEncoder.encode(registeredUserRequest.getPassword()),"customer");

        this.userRepository.save(user);

        return registeredUserRepository.save(
                new RegisteredUser(
                        registeredUserRequest.getUsername(),
                        registeredUserRequest.getName(),
                        registeredUserRequest.getEmail(),
                        registeredUserRequest.getPhonenumber()
                )
        );

    }

    public boolean checkUsernameIsAlreadyExist(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }
}
