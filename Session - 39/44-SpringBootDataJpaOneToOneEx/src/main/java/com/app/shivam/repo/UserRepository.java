package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
