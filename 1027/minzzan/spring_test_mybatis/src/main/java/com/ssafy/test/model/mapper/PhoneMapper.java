package com.ssafy.test.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.test.model.PhoneDto;
import com.ssafy.test.model.UserInfoDto;

public interface PhoneMapper {
	UserInfoDto login(Map<String, String> map);

	void registerPhone(PhoneDto phoneDto) throws Exception;
	List<PhoneDto> listPhone() throws Exception;
	PhoneDto getPhone(String item_code) throws Exception;
	void deletePhone(String item_code) throws Exception;
}
