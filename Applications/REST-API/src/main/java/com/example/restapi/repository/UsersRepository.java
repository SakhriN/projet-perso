package com.example.restapi.repository;

import com.example.restapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    public Users findUsersByUsersFirstnameAndUsersLastnameAndUsersEmailAndUsersUsername
            (String users_firstname,
             String users_lastname,
             String users_email,
             String users_username);

    public Optional<Users> findByUsersEmail(String email);


}
