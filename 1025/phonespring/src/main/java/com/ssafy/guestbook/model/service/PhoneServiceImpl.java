package com.ssafy.guestbook.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.guestbook.model.PhoneDto;
import com.ssafy.guestbook.model.dao.PhoneDao;

@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Autowired
	private PhoneDao phoneDao;

	@Override
	public void registerArticle(PhoneDto phoneDto) throws Exception {
		phoneDao.registerArticle(phoneDto);
	}

	@Override
	public List<PhoneDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return phoneDao.listArticle(param);
	}

	@Override
	public PhoneDto getArticle(String num) throws Exception {
		return phoneDao.getArticle(num);
	}

//	@Override
//	public void updateArticle(PhoneDto phoneDto) throws Exception {
//		phoneDao.updateArticle(phoneDto);
//	}

	@Override
	public void deleteArticle(String num) throws Exception {
		phoneDao.deleteArticle(num);
	}

}
