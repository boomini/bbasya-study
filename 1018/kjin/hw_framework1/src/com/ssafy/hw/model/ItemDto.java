package com.ssafy.hw.model;

public class ItemDto {
	private int seq;
	private String itemCode;
	private String itemName;
	private int itemPrice;
	private String itemCorp;
	private String itemStat;
	private String dtReg;
	private int cateReq;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemCorp() {
		return itemCorp;
	}

	public void setItemCorp(String itemCorp) {
		this.itemCorp = itemCorp;
	}

	public String getItemStat() {
		return itemStat;
	}

	public void setItemStat(String itemStat) {
		this.itemStat = itemStat;
	}

	public String getDtReg() {
		return dtReg;
	}

	public void setDtReg(String dtReg) {
		this.dtReg = dtReg;
	}

	public int getCateReq() {
		return cateReq;
	}

	public void setCateReq(int i) {
		this.cateReq = i;
	}

	@Override
	public String toString() {
		return "ItemDto [seq=" + seq + ", itemCode=" + itemCode + ", itemName=" + itemName + ", itemPrice=" + itemPrice
				+ ", itemCorp=" + itemCorp + ", itemStat=" + itemStat + ", dtReg=" + dtReg + ", cateReq=" + cateReq
				+ "]";
	}

}
