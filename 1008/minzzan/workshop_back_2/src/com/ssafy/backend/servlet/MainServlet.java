package com.ssafy.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.backend.dto.Book;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // post는 항상 한글 처리
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "/regist_result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);

	}
	
//	private String registerProduct(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String id = request.getParameter("isbn");
//		String title = request.getParameter("title");
//		String author = request.getParameter("author");
//		String price = request.getParameter("price");
//		String desc = request.getParameter("desc");
//
//		
//		return "/regist_result.jsp";
//	}

}
