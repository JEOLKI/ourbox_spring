<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cnt = (Integer) request.getAttribute("cnt");
	
	if(cnt > 0 ){
%>
	{ "sw" : "결제가 완료되었습니다." }
<%
	} else {
%>
	{ "sw" : "결제가 실패하였습니다." }

<%
	}	
%>