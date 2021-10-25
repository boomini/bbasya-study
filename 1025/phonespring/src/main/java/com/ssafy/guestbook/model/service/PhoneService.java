package com.ssafy.guestbook.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.guestbook.model.PhoneDto;

public interface PhoneService {

	void registerArticle(PhoneDto phoneDto) throws Exception;
	List<PhoneDto> listArticle(Map<String, String> map) throws Exception;
	PhoneDto getArticle(String num) throws Exception;
//	void updateArticle(PhoneDto phoneDto) throws Exception;
	void deleteArticle(String num) throws Exception;
	
}
