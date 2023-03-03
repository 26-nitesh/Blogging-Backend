package com.bloggingBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bloggingBackend.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
