package com.ssafy.hw.model.service;

import java.util.Map;

import com.ssafy.hw.model.UserDto;

public interface UserService {
	UserDto login(Map<String, String> map) throws Exception;
	void insert(UserDto userDto) throws Exception;
}
