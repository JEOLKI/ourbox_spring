<%@page import="ourbox.common.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<ReplyVO> replyList = (List<ReplyVO>) request.getAttribute("replyList");

%>

[
	<%
		for(int i=0; i< replyList.size();i++){
			
			ReplyVO reply = replyList.get(i);
			
			if(i>0) out.print(",");
	%>
			{
				"reply_seq"      : "<%= reply.getReply_seq() %>",			
				"reply_content"    : "<%= reply.getReply_content().replaceAll("\r", "").replaceAll("\n", "<br>") %>",			
				"reply_date" 	  : "<%= reply.getReply_date() %>",
				"board_seq"	  : "<%= reply.getBoard_seq() %>",
				"mem_id"	  : "<%= reply.getMem_id() %>"
							
			}  

	<%
		}
	%>
]	