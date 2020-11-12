<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	int room_seq = (Integer) request.getAttribute("room_seq");

	int result = (Integer) request.getAttribute("result");
	
	
	if(result > 0 ){
%>
	{ "result" : "성공" ,
	  "room_seq" : room_seq }
<%
	} else {
%>
	{ "result" : "실패" , 
	  "room_seq" : room_seq }

<%
	}	
%>