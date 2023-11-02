package edu.kh.test.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.test.user.model.dao.UserDAO;
import edu.kh.test.user.model.vo.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO dao;

	@Override
	public User search(int memberNo) {
		return dao.search(memberNo);
	}
	
	
}
