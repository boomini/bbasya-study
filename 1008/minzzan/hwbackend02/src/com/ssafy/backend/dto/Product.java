package com.ssafy.backend.dto;

public class Product {
	private String pname;
	private int price;
	private String desc;

	public Product() {
		super();
	}

	
	public Product(String pname, int price, String desc) {
		super();
		this.pname = pname;
		this.price = price;
		this.desc = desc;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
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


	@Override
	public String toString() {
		return "상품명 : "+ pname;
	}

	

}
