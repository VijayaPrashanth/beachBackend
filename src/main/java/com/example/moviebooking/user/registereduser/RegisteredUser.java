package com.example.moviebooking.user.registereduser;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "registereduser")
public class RegisteredUser {

    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty
    private String username;

    @Column(nullable = false)
    @JsonProperty
    private String name;

    @Column(nullable = false)
    @JsonProperty
    private String email;

    @Column(nullable = false)
    @JsonProperty
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must have exactly 10 digits")
    private String phonenumber;

    public RegisteredUser(){}

    public RegisteredUser(String username, String name, String email, String phonenumber) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, email, phonenumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!(obj instanceof RegisteredUser))
            return false;
        RegisteredUser that = (RegisteredUser) obj;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phonenumber, that.phonenumber);
    }
}
