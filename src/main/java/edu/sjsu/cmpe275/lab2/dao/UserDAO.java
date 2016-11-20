package edu.sjsu.cmpe275.lab2.dao;

import java.util.List;

import edu.sjsu.cmpe275.lab2.model.User;

public interface UserDAO {
	
	public User findUserById(String id);

	public void saveUser(User User);
	
	public void updateUser(User User);
	
	public void deleteUserById(String id);
	
	public List<User> findAllUsers();
	
	public boolean checkUserPresentById(String id);

}
