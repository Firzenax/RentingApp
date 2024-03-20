package com.codinglouis.rentingapp.repositories;

import com.codinglouis.rentingapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User findUserByEmail(String email);
}
