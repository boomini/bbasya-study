package com.ssafy.hw.model.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hw.model.FileInfoDto;
import com.ssafy.hw.model.ProductDto;
import com.ssafy.hw.model.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private SqlSession sqlSession;


	@Override
	@Transactional
	public void registerArticle(ProductDto productDto) throws Exception {
//		ProductMapper productMapper=sqlSession.getMapper(ProductMapper.class);
		sqlSession.getMapper(ProductMapper.class).registerArticle(productDto);
		List<FileInfoDto> fileInfos=productDto.getFileInfos();
		if (fileInfos!=null&&!fileInfos.isEmpty()) {
			sqlSession.getMapper(ProductMapper.class).registerFile(productDto);
		}
	}

	@Override
	public ProductDto getArticle(String no) throws Exception {
		return sqlSession.getMapper(ProductMapper.class).getArticle(no);
	}

	@Override
	public List<ProductDto> listArticle(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(ProductMapper.class).listArticle(map);
	}

	@Override
	public void updateArticle(ProductDto productDto) throws Exception {
		sqlSession.getMapper(ProductMapper.class).updateArticle(productDto);
	}

	@Override
	public void deleteArticle(String no, String path) throws Exception {
		List<FileInfoDto> fileList=sqlSession.getMapper(ProductMapper.class).fileInfoList(no);
		sqlSession.getMapper(ProductMapper.class).deleteFile(no);
		sqlSession.getMapper(ProductMapper.class).deleteArticle(no);
		// 폴더에서 사진 지우기
		for (FileInfoDto fileInfoDto: fileList) {
			File file=new File(path+File.separator+fileInfoDto.getSaveFolder()+File.separator+fileInfoDto.getSaveFile());
			file.delete();
		}
	}

//	@Override
//	public ProductDto lastProduct(String no) throws Exception {
//		return productDao.lastProduct(no);
//	}

}
