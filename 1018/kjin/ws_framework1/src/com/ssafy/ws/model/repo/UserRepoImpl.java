package com.ssafy.ws.model.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.ssafy.util.DBUtil;
import com.ssafy.ws.model.UserDto;

public class UserRepoImpl implements UserRepo {
	
	private DataSource dataSource;
	
	
//	public UserRepoImpl(DataSource dataSource) {
//		this.dataSource=dataSource;
//	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public UserDto select(String id) throws Exception {
		UserDto userDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, pass, rec_id \n");
			sql.append("from user \n");
			sql.append("where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userDto = new UserDto();
				userDto.setId(id);
				userDto.setName(rs.getString("name"));
				userDto.setPass(rs.getString("pass"));
				userDto.setRec_id(rs.getString("rec_id"));
			}
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return userDto;
	}

}
