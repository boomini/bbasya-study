package com.ssafy.guestbook.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.dao.GuestBookDao;

@Service
public class GuestBookServiceImpl implements GuestBookService {

	@Autowired
	private GuestBookDao guestBookDao;
	
	@Override
	public void registerArticle(GuestBookDto guestBookDto) throws Exception {
		guestBookDao.registerArticle(guestBookDto);
	}

	@Override
	public List<GuestBookDto> listArticle(Map<String, String> map) throws Exception {
//		key = key == null ? "" : key.trim();
//		word = word == null ? "" : word.trim();
		return guestBookDao.listArticle(map);
	}

	@Override
	public GuestBookDto getArticle(int articleNo) throws Exception {
		return guestBookDao.getArticle(articleNo);
	}

	@Override
	public void updateArticle(GuestBookDto guestBookDto) throws Exception {
		guestBookDao.updateArticle(guestBookDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		guestBookDao.deleteArticle(articleNo);
	}

}
