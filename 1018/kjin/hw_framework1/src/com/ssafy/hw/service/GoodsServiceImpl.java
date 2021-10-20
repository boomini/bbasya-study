package com.ssafy.hw.service;

import java.util.List;

import com.ssafy.hw.dao.GoodsDao;
import com.ssafy.hw.model.CateDto;
import com.ssafy.hw.model.ItemDto;

public class GoodsServiceImpl implements GoodsService {

	private GoodsDao goodsDao;
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	@Override
	public List<ItemDto> list(int cateSeq) throws Exception {
		return goodsDao.list(cateSeq);
	}

	@Override
	public List<CateDto> listCate() throws Exception {
		return goodsDao.listCate();
	}

	@Override
	public void insert(ItemDto itemDto) throws Exception {
		goodsDao.insert(itemDto);
	}

	@Override
	public void modify(ItemDto itemDto) throws Exception {
		goodsDao.modify(itemDto);
	}

}
