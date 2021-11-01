package com.ssafy.ws.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.ws.model.UserDto;
import com.ssafy.ws.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserDto select(String id) throws Exception {
		return sqlSession.getMapper(UserRepo.class).select(id);
	}

	@Override
	public UserDto login(@RequestParam Map<String, String> map) throws Exception {
		return sqlSession.getMapper(UserRepo.class).login(map);
	}


}
