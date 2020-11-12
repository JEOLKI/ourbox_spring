<%@page import="ourbox.common.vo.QnaVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<QnaVO> list = (List<QnaVO>) request.getAttribute("listvalue");
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
			QnaVO qna = list.get(i);
			if(i >0 ) out.print(", ");
		%>
			{
				"qna_seq"      : "<%= qna.getQna_seq()%>",			
				"qna_title"    : "<%= qna.getQna_title() %>",			
				"qna_date" 	  : "<%= qna.getQna_date() %>",
				"mem_id"	  : "<%= qna.getMem_id() %>"		
			}			
		<%
		}
		%>
    ]
	
}    