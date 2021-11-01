package com.ssafy.hw.model.mapper;

import java.util.Map;

import com.ssafy.hw.model.UserDto;

public interface UserMapper {
	UserDto login(Map<String, String> map) throws Exception;
	void insert(UserDto userDto) throws Exception;

}
