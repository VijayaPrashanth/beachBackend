package com.example.moviebooking.user.registereduser;

import com.example.moviebooking.excpetions.EmailIDAlreadyExistsException;
import com.example.moviebooking.excpetions.PhoneNumberAlreadyExistsException;
import com.example.moviebooking.excpetions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class RegisteredUserController {

    @Autowired
    private RegisteredUserService registeredUserService;

    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody RegisteredUserRequest registeredUserRequest) throws UsernameAlreadyExistsException, EmailIDAlreadyExistsException, PhoneNumberAlreadyExistsException {

        RegisteredUser registeredUser = null;
        try{
            registeredUser =  registeredUserService.register(registeredUserRequest);
        }
        catch(UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(EmailIDAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(PhoneNumberAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Boolean> usernameAlreadyExists(@PathVariable String username) {
        boolean isUsernameIsAlreadyExist = this.registeredUserService.checkUsernameIsAlreadyExist(username);

        return ResponseEntity.status(HttpStatus.OK).body(isUsernameIsAlreadyExist);
    }
}
