package com.ssafy.test.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.model.PhoneDto;
import com.ssafy.test.model.UserInfoDto;
import com.ssafy.test.model.mapper.PhoneMapper;

@Service
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserInfoDto login(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerPhone(PhoneDto phoneDto) throws Exception {
		sqlSession.getMapper(PhoneMapper.class).registerPhone(phoneDto);
	}

	@Override
	public List<PhoneDto> listPhone() throws Exception {
		return sqlSession.getMapper(PhoneMapper.class).listPhone();
	}

	@Override
	public PhoneDto getPhone(String num) throws Exception {
		return sqlSession.getMapper(PhoneMapper.class).getPhone(num);
	}

	@Override
	public void deletePhone(String num) throws Exception {
		sqlSession.getMapper(PhoneMapper.class).deletePhone(num);
	}

}
