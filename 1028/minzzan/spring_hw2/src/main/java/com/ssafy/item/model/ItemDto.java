package com.ssafy.item.model;

public class ItemDto {
	private int seq;
	private String item_code;
	private String item_name;
	private int item_price;
	private String item_corp;
	private String item_stat;
	private String dt_reg;
	private int cate_seq;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public String getItem_corp() {
		return item_corp;
	}

	public void setItem_corp(String item_corp) {
		this.item_corp = item_corp;
	}

	public String getItem_stat() {
		return item_stat;
	}

	public void setItem_stat(String item_stat) {
		this.item_stat = item_stat;
	}

	public String getDt_reg() {
		return dt_reg;
	}

	public void setDt_reg(String dt_reg) {
		this.dt_reg = dt_reg;
	}

	public int getCate_seq() {
		return cate_seq;
	}

	public void setCate_seq(int cate_seq) {
		this.cate_seq = cate_seq;
	}

	@Override
	public String toString() {
		return "ItemVO [seq=" + seq + ", item_code=" + item_code + ", item_name=" + item_name + ", item_price="
				+ item_price + ", item_corp=" + item_corp + ", item_stat=" + item_stat + ", dt_reg=" + dt_reg
				+ ", cate_seq=" + cate_seq + "]";
	}
	
	

}
