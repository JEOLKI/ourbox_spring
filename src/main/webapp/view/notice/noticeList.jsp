<%@page import="ourbox.common.vo.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<NoticeVO> noticeList = (List<NoticeVO>) request.getAttribute("noticeList");
%>

[
	<%
		for(int i=0; i<noticeList.size();i++){
			
			NoticeVO notice = noticeList.get(i);
			
			if(i>0) out.print(",");
	%>
			{
				"notice_seq"      : "<%= notice.getNotice_seq() %>",			
				"notice_title"    : "<%= notice.getNotice_title() %>",			
				"notice_date" 	  : "<%= notice.getNotice_date() %>",
				"manager_id"	  : "<%= notice.getManager_id() %>"			
			}
	<%
		}
	%>
]
