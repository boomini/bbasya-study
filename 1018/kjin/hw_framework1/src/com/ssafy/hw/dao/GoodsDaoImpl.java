package com.ssafy.hw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ssafy.hw.model.CateDto;
import com.ssafy.hw.model.ItemDto;
import com.ssafy.util.DBUtil;

public class GoodsDaoImpl implements GoodsDao {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	} 
	
	@Override
	public List<ItemDto> list(int cateSeq) throws Exception {
		List<ItemDto> list = new ArrayList<ItemDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select seq, item_code, item_name, item_price, item_corp, item_stat, dt_reg, cate_seq \n");
			sql.append("from goodsinfo \n");
			sql.append("where cate_seq=? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, cateSeq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ItemDto itemDto = new ItemDto();
				itemDto.setSeq(rs.getInt("seq"));
				itemDto.setItemCode(rs.getString("item_code"));
				itemDto.setItemName(rs.getString("item_name"));
				itemDto.setItemPrice(rs.getInt("item_price"));
				itemDto.setItemCorp(rs.getString("item_corp"));
				itemDto.setItemStat(rs.getString("item_stat"));
				itemDto.setDtReg(rs.getString("dt_reg"));
				itemDto.setCateReq(rs.getInt("cate_seq"));
				
				list.add(itemDto);
			}
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public List<CateDto> listCate() throws Exception {
		List<CateDto> list = new ArrayList<CateDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select cate_seq, cate_name \n");
			sql.append("from goodscategory \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CateDto cateDto = new CateDto();
				cateDto.setCateSeq(rs.getInt("cate_seq"));
				cateDto.setCateName(rs.getString("cate_name"));
				list.add(cateDto);
			}
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
		return list;
	}

	@Override
	public void insert(ItemDto itemDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt=0;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into goodsinfo (item_code, item_name, item_price, item_corp, item_stat, dt_reg, cate_seq) \n");
			sql.append("values (?, ?, ?, ?, ?, now(), ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, itemDto.getItemCode());
			pstmt.setString(2, itemDto.getItemName());
			pstmt.setInt(3, itemDto.getItemPrice());
			pstmt.setString(4, itemDto.getItemCorp());
			pstmt.setString(5, itemDto.getItemStat());
			pstmt.setInt(6, itemDto.getCateReq());
			cnt=pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}

	}

	@Override
	public void modify(ItemDto itemDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt=0;
		try {
			conn = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update goodsinfo \n");
			sql.append("set item_price=?, item_corp=?, item_stat=?\n");
			sql.append("where seq = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, itemDto.getItemPrice());
			pstmt.setString(2, itemDto.getItemCorp());
			pstmt.setString(3, itemDto.getItemStat());
			pstmt.setInt(4, itemDto.getSeq());
			cnt=pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, conn);
		}

	}

}
