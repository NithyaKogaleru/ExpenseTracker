package com.gigster.et.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigster.et.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {

	User findByRole(String role);
}
