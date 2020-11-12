<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String) request.getAttribute("message");
%>

{
"id" : "<%=msg %>",
"pw" : "<%=msg %>"

}