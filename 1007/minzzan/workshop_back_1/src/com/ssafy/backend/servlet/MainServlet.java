package com.ssafy.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
		Book book = new Book();
		book.setIsbn(request.getParameter("isbn"));
		book.setTitle(request.getParameter("title"));
		book.setAuthor(request.getParameter("author"));
		book.setPrice(Integer.parseInt(request.getParameter("price")));
		book.setDesc(request.getParameter("desc"));

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("	<body>");
		out.println("		<h1>입력 내용</h1>");
		out.println(book.toString());
		out.println("	</body>");
		out.println("</html>");
		

	}

}
