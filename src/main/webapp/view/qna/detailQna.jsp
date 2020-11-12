<%@page import="ourbox.common.vo.QnaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	QnaVO detailQna= (QnaVO) request.getAttribute("detailQna");
%>

{
	"qna_seq"      : "<%= detailQna.getQna_seq() %>",			
	"qna_title"    : "<%= detailQna.getQna_title() %>",			
	"qna_content"  : "<%= detailQna.getQna_content().replaceAll("\r", "").replaceAll("\n", "<br>") %>",			
	"qna_date" 	  : "<%= detailQna.getQna_date() %>",
	"mem_id"	  : "<%= detailQna.getMem_id() %>"			
}  
