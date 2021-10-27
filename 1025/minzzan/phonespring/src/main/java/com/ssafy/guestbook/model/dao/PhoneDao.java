package com.ssafy.guestbook.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.guestbook.model.PhoneDto;

public interface PhoneDao {
	
	void registerArticle(PhoneDto phoneDto) throws Exception;
	List<PhoneDto> listArticle(Map<String, Object> map) throws Exception;
	int getTotalCount(Map<String, String> map) throws Exception;
	PhoneDto getArticle(String num) throws Exception;
//	void updateArticle(PhoneDto phoneDto) throws Exception;
	void deleteArticle(String num) throws Exception;
	
}
