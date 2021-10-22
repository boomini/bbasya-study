package com.ssafy.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.test.dao.TestDao;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;
	
	@Override
	public void register() {
		// TODO Auto-generated method stub

	}

}
