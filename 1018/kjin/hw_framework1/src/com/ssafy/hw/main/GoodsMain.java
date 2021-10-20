package com.ssafy.hw.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssafy.hw.model.CateDto;
import com.ssafy.hw.model.ItemDto;
import com.ssafy.hw.service.GoodsService;
import com.ssafy.hw.service.GoodsServiceImpl;

public class GoodsMain {

	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean flag=true;
		while(flag) {
			System.out.println("1.상품추가  2.상품수정  3.카테고리로 상품검색 4.카테고리목록  0.종료");
			System.out.println("---------------------------------------------------");
			System.out.print("번호 입력: ");
			int num;
			try {
				num = Integer.parseInt(in.readLine());
				switch(num) {
				case 1: insertItem(); break;
				case 2: updateItem(); break;
				case 3: listItem(); break;
				case 4: listCate(); break;
				default: flag=false; break;
				}
				System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void listCate() {
		ApplicationContext context= new ClassPathXmlApplicationContext("main/resources/application.xml");
		GoodsService goodsService = context.getBean("goodsService", GoodsServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			List<CateDto> list=goodsService.listCate();
			for (CateDto cateDto: list) {
				System.out.println(cateDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void listItem() {
		ApplicationContext context= new ClassPathXmlApplicationContext("main/resources/application.xml");
		GoodsService goodsService = context.getBean("goodsService", GoodsServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("검색할 카테고리 번호: ");
		try {
			int cateSeq=Integer.parseInt(in.readLine());
			List<ItemDto> list=goodsService.list(cateSeq);
			for (ItemDto itemDto: list) {
				System.out.println(itemDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void updateItem() {
		ApplicationContext context= new ClassPathXmlApplicationContext("main/resources/application.xml");
		GoodsService goodsService = context.getBean("goodsService", GoodsServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			ItemDto itemDto=new ItemDto();
			System.out.print("수정할 상품번호: ");
			itemDto.setSeq(Integer.parseInt(in.readLine()));
			System.out.print("판매가: ");
			itemDto.setItemPrice(Integer.parseInt(in.readLine()));
			System.out.print("공급사: ");
			itemDto.setItemCorp(in.readLine());
			System.out.print("판매상태: ");
			itemDto.setItemStat(in.readLine());
			goodsService.modify(itemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertItem() {
		ApplicationContext context= new ClassPathXmlApplicationContext("main/resources/application.xml");
		GoodsService goodsService = context.getBean("goodsService", GoodsServiceImpl.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			ItemDto itemDto=new ItemDto();
			System.out.print("상품코드: ");
			itemDto.setItemCode(in.readLine());
			System.out.print("상품이름: ");
			itemDto.setItemName(in.readLine());
			System.out.print("판매가: ");
			itemDto.setItemPrice(Integer.parseInt(in.readLine()));
			System.out.print("공급사: ");
			itemDto.setItemCorp(in.readLine());
			System.out.print("판매상태: ");
			itemDto.setItemStat(in.readLine());
			System.out.print("카테고리 번호: ");
			itemDto.setCateReq(Integer.parseInt(in.readLine()));
			goodsService.insert(itemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
