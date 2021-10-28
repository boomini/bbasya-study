package com.ssafy.item.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.item.model.ItemDto;
import com.ssafy.item.model.UserInfoDto;


public interface ItemMapper {
	UserInfoDto login(Map<String, String> map);

	void registerItem(ItemDto itemDto) throws Exception;
	List<ItemDto> listItem() throws Exception;
	ItemDto getItem(String num) throws Exception;
	void deleteItem(String num) throws Exception;
}
