<%@page import="ourbox.common.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BoardVO detailBoard = (BoardVO) request.getAttribute("detailBoard");

%>

	{
		"board_seq"      : "<%= detailBoard.getBoard_seq() %>",			
		"board_title"    : "<%= detailBoard.getBoard_title() %>",			
		"board_date" 	  : "<%= detailBoard.getBoard_date() %>",
		"board_content" 	  : "<%= detailBoard.getBoard_content().replaceAll("\r", "").replaceAll("\n", "<br>") %>",
		"board_date" 	  : "<%= detailBoard.getBoard_date() %>",
		"atch_file_seq" 	  : "<%= detailBoard.getAtch_file_seq() %>",
		"mem_id"	  : "<%= detailBoard.getMem_id() %>",		
		"board_status"	  : "<%= detailBoard.getBoard_status() %>"		
	}		