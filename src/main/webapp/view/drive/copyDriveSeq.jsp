<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int copyDrive_seq = Integer.parseInt(request.getParameter("drive_seq"));
	System.out.println(copyDrive_seq);
%>
 {	"copyDrive_seq" : copyDrive_seq }