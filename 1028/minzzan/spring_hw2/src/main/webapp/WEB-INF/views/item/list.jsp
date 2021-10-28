<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<div class="container text-center mt-3">
	<div class="col-lg-8 mx-auto">
		<h2 class="p-3 mb-3 shadow bg-light">
			<mark class="sky">SSAFY 상품 보기</mark>
		</h2>
		<%-- <c:forEach var="item" items="${items}"> --%>
		<table border="0" cellpadding="0" cellspacing="1" width="590"
			bgcolor="BBBBBB">
			<tr>
				<td align=center height="22">상품 번호</label></td>
				<td align=center height="22">상품명</td>
				<td align=center height="22">상품 가격</label></td>
				<td align=center height="22">제조사</td>
			</tr>
			<c:forEach var="item" items="${items}">
			<tr>
				<td align=center height="22">${item.item_code}</td>
				<td align=center height="22">
					<a href="${root}/detail?num=${item.item_code}">${item.item_name}</a>
				</td>
				<td align=center height="22">${item.item_price}원</td>
				<td align=center height="22">${item.item_corp}</td>
				<td align=center height="22">
					<a href="${root}/delete?num=${item.item_code}">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>
