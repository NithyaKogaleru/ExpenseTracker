package com.gigster.et.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gigster.et.model.User;
import com.gigster.et.repository.UserRepository;
import com.gigster.et.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ADMIN");
		userRepository.save(user);
	}
	
	@Override
	public User findById(String id){
		return userRepository.findOne(id);
	}
}
