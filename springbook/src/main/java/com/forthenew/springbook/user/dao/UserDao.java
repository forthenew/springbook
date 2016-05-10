package com.forthenew.springbook.user.dao;

import java.util.List;

import com.forthenew.springbook.user.User;

public interface UserDao {
	public void add(final User user);
	public User get(String id);
	public void deleteAll();
	public int getCount();
	public List<User> getAll();
}