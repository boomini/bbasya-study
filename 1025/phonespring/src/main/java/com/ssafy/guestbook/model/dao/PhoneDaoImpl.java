package com.ssafy.guestbook.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.guestbook.model.PhoneDto;
import com.ssafy.util.DBUtil;

@Repository
public class PhoneDaoImpl implements PhoneDao {
	
	@Autowired
	private DBUtil dbUtil;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void registerArticle(PhoneDto phoneDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder registerPhone = new StringBuilder();
			registerPhone.append("insert into phones (num, model, price, vcode) \n");
			registerPhone.append("values (?, ?, ?, ?)");
			pstmt = conn.prepareStatement(registerPhone.toString());
			pstmt.setString(1, phoneDto.getNum());
			pstmt.setString(2, phoneDto.getModel());
			pstmt.setString(3, phoneDto.getPrice());
			pstmt.setString(4, phoneDto.getVcode());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<PhoneDto> listArticle(Map<String, Object> map) throws Exception {
		List<PhoneDto> list = new ArrayList<PhoneDto>();
		
		String key = (String) map.get("key");
		String word = (String) map.get("word");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder listArticle = new StringBuilder();
			listArticle.append("select p.num, p.model, p.price, c.vendor \n");
			listArticle.append("from phones p, company c \n");
			listArticle.append("where p.vcode = c.vcode \n");
			if(!word.isEmpty()) {
				if(key.equals("model"))
					listArticle.append("and p.model like ? \n");
				else 
					listArticle.append("and p.model" + key + " = ? \n");
			}
			listArticle.append("order by p.num desc \n");
			listArticle.append("limit ?, ?");
			pstmt = conn.prepareStatement(listArticle.toString());
			int idx = 0;
			if(!word.isEmpty()) {
				if(key.equals("model"))
					pstmt.setString(++idx, "%" + word + "%");
				else 
					pstmt.setString(++idx, word);
			}
			pstmt.setInt(++idx, (int) map.get("start"));
			pstmt.setInt(++idx, (int) map.get("spp"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PhoneDto phoneDto = new PhoneDto();
				phoneDto.setNum(rs.getString("num"));
				phoneDto.setModel(rs.getString("model"));
				phoneDto.setPrice(rs.getString("price"));
				phoneDto.setVcode(rs.getString("vcode"));
				
				list.add(phoneDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int getTotalCount(Map<String, String> map) throws Exception {
		int cnt = 0;
		
		String key = map.get("key");
		String word = map.get("word");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(num) \n");
			sql.append("from phones \n");
			if(!word.isEmpty()) {
				if(key.equals("model"))
					sql.append("where model like ? \n");
				else 
					sql.append("where " + key + " = ? \n");
			}
			pstmt = conn.prepareStatement(sql.toString());
			if(!word.isEmpty()) {
				if(key.equals("model"))
					pstmt.setString(1, "%" + word + "%");
				else 
					pstmt.setString(1, word);
			}
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public PhoneDto getArticle(String num) throws Exception {
		PhoneDto phoneDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder getArticle = new StringBuilder();
			getArticle.append("select num, model, price, vcode \n");
			getArticle.append("from phones \n");
			getArticle.append("where num = ? \n");
			pstmt = conn.prepareStatement(getArticle.toString());
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				phoneDto = new PhoneDto();
				phoneDto.setNum(num);
				phoneDto.setModel(rs.getString("model"));
				phoneDto.setPrice(rs.getString("price"));
				phoneDto.setVcode(rs.getString("vcode"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return phoneDto;
	}

//	@Override
//	public void updateArticle(PhoneDto phoneDto) throws Exception {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dataSource.getConnection();
//			StringBuilder registerArticle = new StringBuilder();
//			registerArticle.append("update phones \n");
//			registerArticle.append("set model = ?, content = ? \n");
//			registerArticle.append("where articleno = ?");
//			pstmt = conn.prepareStatement(registerArticle.toString());
//			pstmt.setString(1, phoneDto.getSubject());
//			pstmt.setString(2, phoneDto.getContent());
//			pstmt.setInt(3, phoneDto.getArticleNo());
//			pstmt.executeUpdate();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//	}

	@Override
	public void deleteArticle(String num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("delete from phones \n");
			registerArticle.append("where num = ?");
			pstmt = conn.prepareStatement(registerArticle.toString());
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
