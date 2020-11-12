<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    // 1: 기존의 세션 데이터를 모두 삭제
    session.invalidate();
//     session.setMaxInactiveInterval(0); // 세션의 유효시간을 0으로 하여 삭제 	
    // 2: 로그인 페이지로 이동시킴.
%>
<script>
	window.parent.location.href="/ourbox/view/main/login.jsp";
</script>

