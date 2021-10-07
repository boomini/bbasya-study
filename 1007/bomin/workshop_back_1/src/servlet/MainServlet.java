package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Book;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Book bookDto = new Book(request.getParameter("isbn"),request.getParameter("title"), request.getParameter("author"),Integer.parseInt(request.getParameter("price")),request.getParameter("desc"));

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>SSAFY - 입력내용</title>");
		out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println(
				"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
		out.println("    <style>");
		out.println("        mark.sky {");
		out.println("            background: linear-gradient(to top, #54fff9 20%, transparent 30%);");
		out.println("        }");
		out.println("    </style>");
		out.println("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>");
		out.println(
				"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>");
		out.println(
				"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
		out.println("    <script type=\"text/javascript\">");
		out.println("        $(document).ready(function () {");
		out.println("            $(\"#mvRegisterBtn\").click(function () {");
		out.println("                location.href = \"/context-root/guestbook/write.html\";");
		out.println("            });");
		out.println("        });");
		out.println("    </script>");
		out.println("</head>");
		out.println("<body>");
		out.println("    <div class=\"container text-center mt-3\">");
		out.println("        <div class=\"col-lg-8 mx-auto\">");
		out.println("            <h2 class=\"p-3 mb-3 shadow bg-light\">입력내용</h2>");

		out.println("            <table class=\"table table-active text-left\">");
		out.println("                <tbody>");
		out.println("                    <tr class=\"table-info\">");
		out.println("                        <td> 도서번호:"+ bookDto.getIsbn()+"</td>");
		out.println("                        <td> 도서명: "+ bookDto.getTitle()+"</td>");
		out.println("                        <td> 가격: "+ bookDto.getPrice()+"</td>");
		out.println("                        <td class=\"text-right\">저자 : "+ bookDto.getAuthor()+"</td>");
		out.println("                    </tr>");
		out.println("                    <tr>");
		out.println("                        <td class=\"p-4\" colspan=\"4\">"+ bookDto.getDesc()+"</td>");
		out.println("                    </tr>");
		out.println("                </tbody>");
		out.println("            </table>");

		out.println("        </div>");
		out.println("    </div>");
		out.println("</body>");
		out.println("</html>");
	}
}
