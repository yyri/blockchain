package com.rong.otc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rong.otc.dao.UserDaoI;
import com.rong.otc.model.User;

@Service("userService")
@Transactional
public class UserService {
	// 自动注入dao
	@Autowired
	private UserDaoI<User> userDao;

	public void test() {
		System.out.println("UserService Test.");
	}

	public void addUser(User user) {
		userDao.save(user);
	}

}
