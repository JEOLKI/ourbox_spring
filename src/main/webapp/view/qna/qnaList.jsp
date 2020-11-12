<%@page import="java.util.List"%>
<%@page import="ourbox.common.vo.QnaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	List<QnaVO> qnaList = (List<QnaVO>) request.getAttribute("qnaList");

%>

[
	<%
		for(int i=0; i<qnaList.size();i++){
			
			QnaVO qna = qnaList.get(i);
			
			if(i>0) out.print(",");
	%>
			{
				"qna_seq"      : "<%= qna.getQna_seq() %>",			
				"qna_title"    : "<%= qna.getQna_title() %>",			
				"qna_date" 	  : "<%= qna.getQna_date() %>",
				"mem_id"	  : "<%= qna.getMem_id() %>"			
			}  

	<%
		}
	%>
]	    