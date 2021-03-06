package com.guestbook.repository;

import com.guestbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Created by douglas.reis on 11/15/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where upper(u.username) = upper(?1)")
    Optional<User> findByUsername(String username);

    @Query("select u from User u where upper(u.email) = upper(?1)")
    Optional<Object> findByEmail(String email);
}
