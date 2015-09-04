package com.cherry.application.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cherry.application.spring.dao.UserDao;
import com.cherry.application.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public String findUser(int userId) {
		return userDao.findUser(userId);
	}

}
