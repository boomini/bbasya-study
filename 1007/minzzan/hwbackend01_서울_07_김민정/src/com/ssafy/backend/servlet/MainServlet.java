package com.ssafy.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.backend.dto.Product;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		product.setPname(request.getParameter("pname"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setDesc(request.getParameter("desc"));

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("	<body>");
		out.println("		<p>**** 상품 등록 완료 ****</p>");
		out.println(product.toString());
		out.println("	</body>");
		out.println("</html>");
		

	}

}
