package com.ssafy.item.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.item.model.ItemDto;
import com.ssafy.item.model.UserInfoDto;
import com.ssafy.item.model.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserInfoDto login(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerItem(ItemDto itemDto) throws Exception {
		sqlSession.getMapper(ItemMapper.class).registerItem(itemDto);
	}

	@Override
	public List<ItemDto> listItem() throws Exception {
		return sqlSession.getMapper(ItemMapper.class).listItem();
	}

	@Override
	public ItemDto getItem(String item_code) throws Exception {
		return sqlSession.getMapper(ItemMapper.class).getItem(item_code);
	}

	@Override
	public void deleteItem(String item_code) throws Exception {
		sqlSession.getMapper(ItemMapper.class).deleteItem(item_code);
	}

}
