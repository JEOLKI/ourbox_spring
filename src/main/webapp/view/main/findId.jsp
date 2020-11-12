<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberVO mv = (MemberVO)request.getAttribute("vo");
String msg1 = (String) request.getAttribute("message1");
String msg2 = (String) request.getAttribute("message2");
%>

{
"id" :"<%=mv.getMem_id() %>"

}