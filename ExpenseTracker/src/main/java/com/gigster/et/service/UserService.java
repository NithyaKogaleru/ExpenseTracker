package com.gigster.et.service;

import com.gigster.et.model.User;

public interface UserService {

	void saveUser(User user);

	User findById(String id);

}
