<%@page import="ourbox.common.vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	NoticeVO detailNotice = (NoticeVO) request.getAttribute("detailNotice");
%>

{
	"notice_seq"      : "<%= detailNotice.getNotice_seq() %>",			
	"notice_title"    : "<%= detailNotice.getNotice_title() %>",			
	"notice_content"  : "<%= detailNotice.getNotice_content().replaceAll("\r", "").replaceAll("\n", "<br>") %>",			
	"notice_date" 	  : "<%= detailNotice.getNotice_date() %>",
	"manager_id"	  : "<%= detailNotice.getManager_id() %>"			
}   