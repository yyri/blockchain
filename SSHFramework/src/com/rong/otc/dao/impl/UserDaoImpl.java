package com.rong.otc.dao.impl;


import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rong.otc.dao.UserDaoI;

@Repository("userDao")
public class UserDaoImpl<T> implements UserDaoI<T> {
	//注入sessionfactory
	@Autowired
	private SessionFactory sessionFactory;
	
	public Serializable get(T o) {
		return null;
	}
	public Serializable save(T o) {
		return sessionFactory.getCurrentSession().save(o);
	}

}
