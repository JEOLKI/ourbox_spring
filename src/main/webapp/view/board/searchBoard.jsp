<%@page import="ourbox.common.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
%>

[
	<%
		for(int i=0; i<boardList.size();i++){
			
			BoardVO board = boardList.get(i);
			
			if(i>0) out.print(",");
	%>
			{
				"board_seq"      : "<%= board.getBoard_seq() %>",			
				"board_title"    : "<%= board.getBoard_title() %>",			
				"board_date" 	  : "<%= board.getBoard_date() %>",
				"board_content"   : "<%= board.getBoard_content().replaceAll("\r", "").replaceAll("\n", "<br>")  %>",
				"board_date" 	  : "<%= board.getBoard_date() %>",
				"mem_id"	       : "<%= board.getMem_id() %>",		
				"board_status"	  : "<%= board.getBoard_status() %>"		
			}	
	<%
		}
	%>
]
    
