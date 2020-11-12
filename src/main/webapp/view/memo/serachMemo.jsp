<%@page import="ourbox.common.vo.MemoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<MemoVO> memoList = (List<MemoVO>) request.getAttribute("memoList");
%>

[
	<%
		for(int i=0; i<memoList.size();i++){
			
			MemoVO memo = memoList.get(i);
			
			if(i>0) out.print(",");
	%>
			{
				"memo_seq"	: "<%= memo.getMemo_seq() %>",
				"memo_title" : "<%= memo.getMemo_title() %>",
				"memo_content" : "<%= memo.getMemo_content().replaceAll("\\n", "<br>") %>",
				"memo_date" : "<%= memo.getMemo_date() %>",
				"mem_id" : "<%= memo.getMem_id() %>"
			}
	<%
		}
	%>
]
