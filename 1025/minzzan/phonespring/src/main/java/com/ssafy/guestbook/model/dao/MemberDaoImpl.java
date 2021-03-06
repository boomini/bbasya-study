package com.ssafy.guestbook.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.util.DBUtil;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private DBUtil dbUtil;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public int idCheck(String id) throws Exception {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select count(id) \n");
			loginMember.append("from userinfo \n");
			loginMember.append("where id = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public void registerMember(MemberDto memberDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder registerMember = new StringBuilder();
			registerMember.append("insert into userinfo (id, pw) \n");
			registerMember.append("values (?, ?)");
			pstmt = conn.prepareStatement(registerMember.toString());
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPw());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public MemberDto login(Map<String, String> map) throws Exception {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select id \n");
			loginMember.append("from userinfo \n");
			loginMember.append("where id = ? and pw = ? \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("pw"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setId(rs.getString("id"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

}
