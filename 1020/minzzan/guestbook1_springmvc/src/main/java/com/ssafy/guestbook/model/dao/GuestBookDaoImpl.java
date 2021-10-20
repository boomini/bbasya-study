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

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.util.DBUtil;

@Repository
public class GuestBookDaoImpl implements GuestBookDao {

	private DBUtil dbUtil = DBUtil.getInstance();

	@Autowired
	private DataSource dataSource;

	@Override
	public void registerArticle(GuestBookDto guestBookDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("insert into guestbook (userid, subject, content, regtime) \n");
			registerArticle.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(registerArticle.toString());
			pstmt.setString(1, guestBookDto.getUserId());
			pstmt.setString(2, guestBookDto.getSubject());
			pstmt.setString(3, guestBookDto.getContent());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<GuestBookDto> listArticle(Map<String, String> map) throws Exception {
		List<GuestBookDto> list = new ArrayList<GuestBookDto>();

		String key = map.get("key");
		String word = map.get("word");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder listArticle = new StringBuilder();
			listArticle.append("select g.articleno, g.userid, g.subject, g.content, g.regtime, m.username \n");
			listArticle.append("from guestbook g, ssafy_member m \n");
			listArticle.append("where g.userid = m.userid \n");
			if (!word.isEmpty()) {
				if (key.equals("subject"))
					listArticle.append("and g.subject like ? \n");
				else
					listArticle.append("and g." + key + "= ? \n");
			}
			listArticle.append("order by g.articleno desc \n");
			pstmt = conn.prepareStatement(listArticle.toString());
			if (!word.isEmpty()) {
				if (key.equals("subject"))
					pstmt.setString(1, "%" + word + "%");
				else
					pstmt.setString(1, word);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestBookDto guestBookDto = new GuestBookDto();
				guestBookDto.setArticleNo(rs.getInt("articleno"));
				guestBookDto.setUserId(rs.getString("userid"));
				guestBookDto.setUserName(rs.getString("username"));
				guestBookDto.setSubject(rs.getString("subject"));
				guestBookDto.setContent(rs.getString("content"));
				guestBookDto.setRegTime(rs.getString("regtime"));

				list.add(guestBookDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public GuestBookDto getArticle(int articleNo) throws Exception {
		GuestBookDto guestBookDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder getArticle = new StringBuilder();
			getArticle.append("select subject, content \n");
			getArticle.append("from guestbook \n");
			getArticle.append("where articleno = ? \n");
			pstmt = conn.prepareStatement(getArticle.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				guestBookDto = new GuestBookDto();
				guestBookDto.setArticleNo(articleNo);
				guestBookDto.setSubject(rs.getString("subject"));
				guestBookDto.setContent(rs.getString("content"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return guestBookDto;
	}

	@Override
	public void updateArticle(GuestBookDto guestBookDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("update guestbook \n");
			registerArticle.append("set subject = ?, content = ? \n");
			registerArticle.append("where articleno = ?");
			pstmt = conn.prepareStatement(registerArticle.toString());
			pstmt.setString(1, guestBookDto.getSubject());
			pstmt.setString(2, guestBookDto.getContent());
			pstmt.setInt(3, guestBookDto.getArticleNo());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("delete from guestbook \n");
			registerArticle.append("where articleno = ?");
			pstmt = conn.prepareStatement(registerArticle.toString());
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
