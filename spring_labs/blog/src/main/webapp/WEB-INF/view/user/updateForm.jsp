<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>


<h1>회원수정 페이지</h1>
<hr/>
<form action="/update" method="post">
<input type="password" name="password" placeholder="Enter password"><br/>
<input type="email" name="email" placeholder="Enter email"><br/>
<button type="submit">회원수정</button>
</form> 

    <%@ include file="../layout/footer.jsp" %>