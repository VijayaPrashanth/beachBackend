package com.example.moviebooking.user.registereduser;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
public class RegisteredUserRequest {

    @JsonProperty
    @NotBlank(message = "name must be provided")
    private String name;

    @JsonProperty
    @NotBlank(message = "username must be provided")
    @Pattern(regexp = "^[A-za-z_].*$",message = "username is not valid")
    private String username;

    @JsonProperty
    @NotBlank(message = "email must be provided")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z.-]+$",message = "email id is invalid")
    private String email;

    @JsonProperty
    @NotBlank(message = "password must be provided")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,64}$", message = "password is invalid")
    private String password;

    @JsonProperty
    @NotBlank(message = "phone number must be provided")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "phone number must have only 10 digits")
    private String phonenumber;

    public RegisteredUserRequest(){}

    public RegisteredUserRequest(String name, String username, String email, String password, String phonenumber) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "RegisteredUserRequest{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
