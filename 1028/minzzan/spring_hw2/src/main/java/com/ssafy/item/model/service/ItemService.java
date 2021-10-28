package com.ssafy.item.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.item.model.ItemDto;
import com.ssafy.item.model.UserInfoDto;


public interface ItemService {
	
	UserInfoDto login(Map<String, String> map);
	
	void registerItem(ItemDto itemDto) throws Exception;
	List<ItemDto> listItem() throws Exception;
	ItemDto getItem(String item_code) throws Exception;
	void deleteItem(String item_code) throws Exception;
}
