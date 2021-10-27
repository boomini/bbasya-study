<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>핸드폰 관리</title>
<script type="text/javascript">
function folderDeleteClick(){
	  var checkBoxArr = []; 
	  $("input:checkbox[name='folderCheckname']:checked").each(function() {
	  checkBoxArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
	  console.log(checkBoxArr);
	})
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<br>
	<table width=780 border=0 cellpadding=0 cellspacing=0>

		<!--contents-->
		<table width=590 border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>핸드폰 관리 -
						핸드폰 목록</b></td>
			</tr>
			<tr>
				<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>
				<a href="${root}/register">등록</a></b></td>
			</tr>
		</table>
		<br>
		<table border="0" cellpadding="0" cellspacing="1" width="590"
			bgcolor="BBBBBB">
			<tr>
				<td align=center bgcolor="E6ECDE" height="22">모델번호</td>
				<td align=center bgcolor="E6ECDE" height="22">모델이름</td>
				<td align=center bgcolor="E6ECDE" height="22">가격</td>
				<td align=center bgcolor="E6ECDE" height="22">제조사</td>
				<td align=center bgcolor="E6ECDE" height="22">비고</td>
			</tr>
			
			<c:forEach var="phone" items="${phones}">
			<tr>
				<td align=center height="22">${phone.num}</td>
				<td align=center height="22">
					<a href="${root}/delete?num=${phone.num}">${phone.model}</a>
				</td>
				<td align=center height="22">${phone.price}원</td>
				<td align=center height="22">${phone.vendor}</td>
				<td align=center height="22">
					<a href="${root}/delete?num=${phone.num}">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</table>

</body>

</html>