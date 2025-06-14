package com.siriscope.userservice.repository;

import com.siriscope.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByUserName(String userName);
    public Optional<User> findByEmail(String email);
}
