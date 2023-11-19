package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.UserDao;
import com.ssafit.pjt.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User login(User user) {
		User tmp = userDao.selectUserById(user.getId());
        if (tmp != null && tmp.getPassword().equals(user.getPassword()))
            return tmp;
        return null;
	}

	@Override
	public int signup(User user) {
		try {
			return userDao.insertUser(user);
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public User getUser(int userKey) {
		return userDao.selectUser(userKey);
	}

	@Override
	public int modifyUser(User user, String loginUserId) {
		// 권한 확인
		User u = userDao.selectUser(user.getUserKey());
		if (u == null) 
			return 0; // 사용자 없으면 0 반환
		else if (u.getUserKey() == Integer.parseInt(loginUserId)) 
			return userDao.updateUser(user);
		else
			return -1; // 권한 없으면 -1 반환
	}

	@Override
	public int removeUser(int userKey, String loginUserId) {
		// 권한 확인
		User u = userDao.selectUser(userKey);
		if (u == null) 
			return 0; // 사용자 없으면 0 반환
		else if (u.getUserKey() == Integer.parseInt(loginUserId))
			return userDao.deleteUser(userKey);
		else
			return -1; // 권한 없으면 -1 반환
	}

	@Override
	public List<User> getUserList(String id) {
		return userDao.selectUserList(id);
	}
	
}
