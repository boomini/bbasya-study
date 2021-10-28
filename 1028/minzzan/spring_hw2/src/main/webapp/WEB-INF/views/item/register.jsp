<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#registerBtn").click(function() {
			if (!$("#item_code").val()) {
				alert("상품번호 입력!!!!");
				return;
			} else if (!$("#item_name").val()) {
				alert("상품명 입력!!!!");
				return;
			} else if (!$("#item_price").val()) {
				alert("상품 가격 입력!!!!");
				return;
			} else if (!$("#item_corp").val()) {
				alert("제조사 입력!!!!");
				return;
			} else {
				$("#writeform").attr("action", "${root}/register").submit();
			}
		});
	});
</script>

<div class="container text-center mt-3">
	<div class="col-lg-8 mx-auto">
		<h2 class="p-3 mb-3 shadow bg-light">
			<mark class="sky">SSAFY 상품 관리</mark>
		</h2>
		<form id="writeform" class="text-left mb-3" method="post" action="">
			<div class="form-group">
				<label for="item_code">상품 번호:</label> <input type="text"
					class="form-control" id="item_code" name="item_code" placeholder="상품번호...">
			</div>
			<div class="form-group">
				<label for="item_name">상품명:</label> <input type="text"
					class="form-control" id="item_name" name="item_name" placeholder="상품명...">
			</div>
			<div class="form-group">
				<label for="item_price">상품 가격:</label> <input type="text"
					class="form-control" id="item_price" name="item_price" placeholder="가격...">
			</div>
			<div class="form-group">
				<label for="item_corp">제조사:</label> <input type="text"
					class="form-control" id="item_corp" name="item_corp" placeholder="제조사...">
			</div>
			<div class="text-center">
				<button type="button" id="registerBtn"
					class="btn btn-outline-primary">등록</button>
				<button type="reset" class="btn btn-outline-danger">취소</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
