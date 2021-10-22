package com.ssafy.test.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public void register() {
		// TODO Auto-generated method stub

	}

}
