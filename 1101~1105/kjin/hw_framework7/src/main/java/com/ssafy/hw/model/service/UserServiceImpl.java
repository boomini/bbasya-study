package com.ssafy.hw.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hw.model.UserDto;
import com.ssafy.hw.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(UserMapper.class).login(map);
	}

	@Override
	public void insert(UserDto userDto) throws Exception {
		sqlSession.getMapper(UserMapper.class).insert(userDto);
	}

}
