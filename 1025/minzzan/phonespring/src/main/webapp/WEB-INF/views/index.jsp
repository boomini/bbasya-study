<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <div align="center">
      <h1>메인 페이지</h1>
      <c:if test="${empty userinfo}">
	      <a href="${root}/user/register">회원가입</a><br />
	      <a href="${root}/user/login">로그인</a><br />
      </c:if>
      <c:if test="${!empty userinfo}">
	      <strong>${userinfo.id} 님 로그인 되었습니다.</strong><br />
	      <a href="${root}/user/logout">로그아웃</a><br />
	      <a href="${root}/guestbook/register">핸드폰등록</a><br />
	      <a href="${root}/guestbook/list?pg=1&key=&word=">핸드폰목록</a><br />
      </c:if>
    </div>
  </body>
</html>
