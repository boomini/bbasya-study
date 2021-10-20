package com.ssafy.hw.service;

import java.util.List;

import com.ssafy.hw.model.CateDto;
import com.ssafy.hw.model.ItemDto;

public interface GoodsService {
	List<ItemDto> list(int cateSeq) throws Exception;
	List<CateDto> listCate() throws Exception;
	void insert(ItemDto itemDto) throws Exception;
	void modify(ItemDto itemDto) throws Exception;

}
