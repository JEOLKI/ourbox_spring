<%@page import="ourbox.common.vo.AnswerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	List<AnswerVO> answerList = (List<AnswerVO>) request.getAttribute("answerList");

%>

[
	<%
		for(int i=0; i< answerList.size();i++){
			
			AnswerVO answer = answerList.get(i);
			
			if(i>0) out.print(",");
	%>
			{
				"ans_seq"      : "<%= answer.getAns_seq() %>",			
				"ans_content"    : "<%= answer.getAns_content().replaceAll("\r", "").replaceAll("\n", "<br>") %>",			
				"ans_date" 	  : "<%= answer.getAns_date() %>",
				"manager_id"	  : "<%= answer.getManager_id() %>",
				"qna_seq"	  : "<%= answer.getQna_seq() %>"
							
			}  

	<%
		}
	%>
]	
