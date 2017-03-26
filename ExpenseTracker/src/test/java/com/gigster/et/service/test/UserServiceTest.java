package com.gigster.et.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gigster.et.model.User;
import com.gigster.et.repository.UserRepository;
import com.gigster.et.service.impl.UserServiceImpl;


@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
public class UserServiceTest {
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Test
	public void saveUserTest(){
		User user = new User();
		user.setPassword("test");
		Mockito.when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encodedpwd");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		userServiceImpl.saveUser(user);
		Mockito.verify(userRepository).save(user);
	}
	
	@Test
	public void findByIdTest(){
		User user = new User();
		user.setName("test");
		Mockito.when(userRepository.findOne("test")).thenReturn(user);
		User result = userServiceImpl.findById("test");
		Assert.assertEquals("test", result.getName());
	}
}
