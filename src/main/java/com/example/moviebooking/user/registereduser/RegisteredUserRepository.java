package com.example.moviebooking.user.registereduser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser,Long> {

    Optional<RegisteredUser> findByEmail(String email);

    Optional<RegisteredUser> findByPhonenumber(String phoneNumber);

    List<RegisteredUser> findByUsername(String username);
}
