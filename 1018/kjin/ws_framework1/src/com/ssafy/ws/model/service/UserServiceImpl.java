package com.ssafy.ws.model.service;

import com.ssafy.ws.model.UserDto;
import com.ssafy.ws.model.repo.UserRepo;

public class UserServiceImpl implements UserService {

	private UserRepo userRepo;
	
//	public UserServiceImpl(UserRepo userRepo) {
//		this.userRepo=userRepo;
//	}
	

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDto select(String id) throws Exception {
		return userRepo.select(id);
	}


}
