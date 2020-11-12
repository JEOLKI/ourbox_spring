<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String result = (String)request.getAttribute("result");
	if(result.equals("중복")){
%>
	{ "sw" : "중복된 아이디 입니다."}	

<%}else{%>
	{ "sw" : "사용 가능한 아이디입니다."}
	<%
	}
	%>