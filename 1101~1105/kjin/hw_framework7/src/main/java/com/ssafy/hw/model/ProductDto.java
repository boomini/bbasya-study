package com.ssafy.hw.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="{ProductDto: 상품 정보 ", description = "상품목록 입니다.")
public class ProductDto {
	@ApiModelProperty(value="상품 번호")
	private String no;
	@ApiModelProperty(value="상품명")
	private String name;
	@ApiModelProperty(value="상품 가격")
	private int price;
	@ApiModelProperty(value="상품 설명")
	private String desc;
	@ApiModelProperty(value="상품 파일")
	private List<FileInfoDto> fileInfos;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}

	@Override
	public String toString() {
		return "ProductDto [no=" + no + ", name=" + name + ", price=" + price + ", desc=" + desc + "]";
	}
	
	
}
