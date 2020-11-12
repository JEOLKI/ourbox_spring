<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberVO mv = (MemberVO)request.getAttribute("vo");
%>

{

"pw" : "<%=mv.getMem_pass() %>"

}