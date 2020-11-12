<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <!-- 세션 선언부 -->
<%
	String msg = (String)request.getAttribute("msg");
%>
<%! String id,pass,name;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		id = (String)session.getAttribute("mem_id");
		pass = (String)session.getAttribute("mem_pass");
		name = (String)session.getAttribute("mem_name");
	%>
	<%=msg%><br>
	<%=name%>님 안녕하세요.<br>
	<a href = "modify.jsp">정보수정</a>
</body>
</html>