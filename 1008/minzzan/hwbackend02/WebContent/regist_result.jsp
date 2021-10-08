<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.ssafy.backend.dto.Product"%>

<%
	String root = request.getContextPath();
	int cnt = 0;

	Product Product = new Product();
	Product.setPname(request.getParameter("pname"));
	Product.setPrice(Integer.parseInt(request.getParameter("price")));
	Product.setDesc(request.getParameter("desc"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 도서 관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
mark.sky {
	background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}

table {
	font-family: arial, sans-serif;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $("#mvRegisterBtn").click(function () {
        location.href = "<%=root%>/regist.html";
		});
	});
</script>
</head>
<body>
	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h3>상품이 저장되었습니다.</h3>
			<table class="table table-active text-left">
				<%
				cnt++;
				%>
				<tr class="table-info">
					<td>상품 번호</td>
					<td><%=cnt%></td>
				</tr>
				<tr class="table-info">
					<td>상품명</td>
					<td><%=Product.getPname()%></td>
				</tr>
				<tr class="table-info">
					<td>상품 가격</td>
					<td><%=Product.getPrice()%></td>
				</tr>
				<tr class="table-info">
					<td>상품 설명</td>
					<td><%=Product.getDesc()%></td>
				</tr>
			</table>
			<div class="m-3 text-left">
				<button type="button" id="mvRegisterBtn" class="btn btn-link">상품 목록</button>
			</div>
		</div>
	</div>
</body>
</html>