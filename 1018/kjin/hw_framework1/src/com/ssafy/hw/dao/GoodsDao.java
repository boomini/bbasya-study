package com.ssafy.hw.dao;

import java.util.List;

import com.ssafy.hw.model.CateDto;
import com.ssafy.hw.model.ItemDto;

public interface GoodsDao {
	List<ItemDto> list(int cateSeq) throws Exception;
	List<CateDto> listCate() throws Exception;
	void insert(ItemDto itemDto) throws Exception;
	void modify(ItemDto itemDto) throws Exception;
	
}
