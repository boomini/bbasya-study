<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.*,com.ssafy.backend.dto.Book"%>

<%
   String root = request.getContextPath();

   Book book=null;
   if(request.getParameter("isbn")!=null){
      book = new Book();
      book.setIsbn(request.getParameter("isbn"));
      book.setTitle(request.getParameter("title"));
      book.setAuthor(request.getParameter("author"));
      book.setPrice(Integer.parseInt(request.getParameter("price")));
      book.setDesc(request.getParameter("desc"));
   }
  
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
   border-collapse: collapse;
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
        location.href = "<%= root %>/regist.html";
    });
});
</script>
</head>
<body>
   <div class="container text-center mt-3">
      <div class="col-lg-8 mx-auto">
         <h2 class="p-3 mb-3 shadow bg-light">
            <mark class="sky">도서 등록 결과</mark>
         </h2>
         <%
            if (book != null) {
         %>
         <h3>등록 도서 정보</h3>
         <table class="table table-active text-left">
            <tr class="table-info text-center">
               <th>항목</th>
               <th>내용</th>
            </tr>
            <tr class="table-info">
               <td>도서번호</td>
               <td><%=book.getIsbn()%></td>
            </tr>
            <tr class="table-info">
               <td>도서명</td>
               <td><%=book.getTitle()%></td>
            </tr>
            <tr class="table-info">
               <td>저자</td>
               <td><%=book.getAuthor()%></td>
            </tr>
            <tr class="table-info">
               <td>가격</td>
               <td><%=book.getPrice()%></td>
            </tr>
            <tr class="table-info">
               <td>설명</td>
               <td><%=book.getDesc()%></td>
            </tr>
         </table>
         <%
            } else {
         %>
         <table class="table table-active text-left">
            <tbody>
               <tr>
                  <td class="table-danger"><strong>등록된 도서가 없습니다.</strong></td>
               </tr>
            </tbody>
         </table>
         <%
            }
         %>
         <div class="m-3 text-left">
            <button type="button" id="mvRegisterBtn" class="btn btn-link">추가등록</button>
         </div>
      </div>
   </div>
</body>
</html>