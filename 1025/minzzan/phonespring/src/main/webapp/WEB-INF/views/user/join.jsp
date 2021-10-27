<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
        	var isId = true;
        	
        	// 회원가입
            $("#registerBtn").click(function () {
                if (!isId) {
                    alert("아이디 확인!!!");
                    return;
                } else if (!$("#pw").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if ($("#pw").val() != $("#pwdcheck").val()) {
                    alert("비밀번호 확인!!!");
                    return;
                } else {
                    $("#memberform").attr("action", "${root}/user/register").submit();
                }
            });
        });
    </script>

    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="orange">회원가입</mark></h2>
            <form id="memberform" class="text-left mb-3" method="post" action="">
                <div class="form-group">
                    <label for="id">아이디</label>
                    <input type="text" class="form-control" id="id" name="id" placeholder="아이디입력...">
                    <div id="idresult" class="mt-1"></div>
                </div>
                <div class="form-group">
                    <label for="pw">비밀번호</label>
                    <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호입력...">
                </div>
                <div class="form-group">
                    <label for="pwdcheck">비밀번호재입력</label>
                    <input type="password" class="form-control" id="pwdcheck" name="pwdcheck" placeholder="비밀번호재입력...">
                </div>
                <div class="form-group text-center">
                    <button type="button" id="registerBtn" class="btn btn-outline-primary">회원가입</button>
                    <button type="reset" class="btn btn-outline-danger">초기화</button>
                </div>
            </form>
        </div>
    </div>
   
</body>
</html>