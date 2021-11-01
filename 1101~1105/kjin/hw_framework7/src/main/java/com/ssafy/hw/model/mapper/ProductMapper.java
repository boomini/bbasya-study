package com.ssafy.hw.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.hw.model.FileInfoDto;
import com.ssafy.hw.model.ProductDto;

public interface ProductMapper {
	void registerArticle(ProductDto productDto) throws Exception;
	ProductDto getArticle(String no) throws Exception;
	List<ProductDto> listArticle(Map<String, String> map) throws Exception;
	void updateArticle(ProductDto productDto) throws Exception;
	void deleteArticle(String no) throws Exception;
//	ProductDto lastProduct(String no) throws Exception;
	List<FileInfoDto> fileInfoList(String no) throws Exception;
	void registerFile(ProductDto productDto);
	void deleteFile(String no);
}
