<%@page import="ourbox.common.vo.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<NoticeVO> list = (List<NoticeVO>) request.getAttribute("listvalue");
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
			NoticeVO notice = list.get(i);
			if(i >0 ) out.print(", ");
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
	
	    
}    