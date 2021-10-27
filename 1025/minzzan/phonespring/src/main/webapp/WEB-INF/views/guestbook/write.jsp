<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<c:if test="${empty userinfo}">
	<script type="text/javascript">
		alert("로그인 후 이용 가능한 페이지입니다.");
		location.href = "${root}/user/login";
	</script>
</c:if>
<c:if test="${!empty userinfo}">
<strong>${userinfo.id} 님 로그인 되었습니다.</strong><br />
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#registerBtn").click(
							function() {
								if (!$("#num").val()) {
									alert("번호 입력!!!!");
									return;
								} else if (!$("#model").val()) {
									alert("모델명 입력!!!!");
									return;
								} else if (!$("#price").val()) {
									alert("가격 입력!!!!");
									return;
								} else {
									$("#writeform").attr("action", "${root}/guestbook/register").submit();
								}
							});
				});
	</script>
	<table width=780 border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td width="20"></td>
			<td>
				<!--contents-->
				<table width=590 border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>핸드폰 관리 -
								핸드폰 등록</b></td>
					</tr>
				</table> <br> <!-- write Form  -->
				<form id="writeform" method="post" action="">
					<table border="0" cellpadding="0" cellspacing="1" width="590"
						bgcolor="BBBBBB">
						<tr>
							<td width=100 align=center bgcolor="E6ECDE" height="22">모델번호</td>
							<td width=490 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 150" name="num" id="num" /></td>
						</tr>
						<tr>
							<td width=100 align=center bgcolor="E6ECDE" height="22">모 델
								명</td>
							<td width=490 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 150" name="model" id="model" /></td>
						</tr>
						<tr>
							<td width=100 align=center bgcolor="E6ECDE" height="22">가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;격</td>
							<td width=490 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 240" name="price" id="price" />원</td>
						</tr>
						<tr>
							<td width=100 align=center bgcolor="E6ECDE" height="22">제 조
								사</td>
							<td width=490 bgcolor="ffffff" style="padding-left: 10"><select
								NAME="vcode" id="vcode" tabindex="5" style="width: 90px">
									<option value="10">삼성</option>
									<option value="20">엘지</option>
									<option value="30">애플</option>
							</select></td>
						</tr>
					</table>

					<br>

					<div class="text-center">
						<button type="button" id="registerBtn"
							class="btn btn-outline-primary">핸드폰등록</button>
						<button type="reset" class="btn btn-outline-danger">취소</button>
					</div>
				</form>
			</td>
		</tr>
	</table>

	</body>

	</html>
</c:if>