<%@page import="ourbox.common.vo.DriveVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<DriveVO> searchList = (List<DriveVO>) request.getAttribute("searchList");
%>
[
	<%
		for(int i =0; i<searchList.size(); i++){
			if(searchList.get(i).getDrive_status().equals("1")){
				searchList.remove(i);
			}else {
				DriveVO vo = searchList.get(i);
				if(i>0) out.print(",");				
	%>
	{
	    "drive_seq"      : "<%=vo.getDrive_seq()%>",
	    "drive_name"     : "<%=vo.getDrive_name()%>",
	    "drive_path"     : "<%=vo.getDrive_path().replaceAll("\\\\", "/")%>",
	    "drive_size"     : "<%=vo.getDrive_size()%>",
	    "drive_type"     : "<%=vo.getDrive_type()%>",
	    "drive_status"   : "<%=vo.getDrive_status()%>",
	    "room_seq"       : "<%=vo.getRoom_seq()%>",
	    "drive_date"     : "<%=vo.getDrive_date()%>"
	}
	
	<%		
				
			}//else문 닫기
		}	// for문 닫기
	%>



]
