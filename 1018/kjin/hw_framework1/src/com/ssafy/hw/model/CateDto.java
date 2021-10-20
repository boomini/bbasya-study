package com.ssafy.hw.model;

public class CateDto {
	private int cateSeq;
	String cateName;

	public int getCateSeq() {
		return cateSeq;
	}

	public void setCateSeq(int cateSeq) {
		this.cateSeq = cateSeq;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	@Override
	public String toString() {
		return "CateDto [cateSeq=" + cateSeq + ", cateName=" + cateName + "]";
	}

}
