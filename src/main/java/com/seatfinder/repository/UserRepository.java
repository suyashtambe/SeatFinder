package com.seatfinder.repository;

import com.seatfinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// This interface extends JpaRepository which provides CRUD functionality for User entities
public interface UserRepository extends JpaRepository<User, Long> {

}


