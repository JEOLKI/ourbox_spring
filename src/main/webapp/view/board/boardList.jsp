<%@page import="ourbox.common.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<BoardVO> list = (List<BoardVO>) request.getAttribute("listvalue");
	int spage = (Integer) request.getAttribute("spage");
	int epage = (Integer) request.getAttribute("epage");
	int tpage = (Integer) request.getAttribute("tpage");
	int cpage = (Integer) request.getAttribute("cpage");

%>

{
	"tpage" : "<%= tpage %>",
	"epage" : "<%= epage %>",
	"spage" : "<%= spage %>",
	"cpage" : "<%= cpage %>",
	"data"  : 
	[
	<%
	for(int i =0 ; i<list.size() ; i++){
		BoardVO board = list.get(i);
		if(i >0 ) out.print(", ");
	%>
		{
			"board_seq"      : "<%= board.getBoard_seq() %>",			
			"board_title"    : "<%= board.getBoard_title() %>",			
			"board_date" 	  : "<%= board.getBoard_date() %>",
			"board_content" 	  : "<%= board.getBoard_content().replaceAll("\r", "").replaceAll("\n", "<br>")  %>",
			"board_date" 	  : "<%= board.getBoard_date() %>",
			"mem_id"	  : "<%= board.getMem_id() %>",		
			"board_status"	  : "<%= board.getBoard_status() %>"		
		}			
	<%
	}
	%>
	]
	
}    
