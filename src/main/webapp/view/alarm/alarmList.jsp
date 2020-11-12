<%@page import="ourbox.common.vo.AlarmVO"%>
<%@page import="ourbox.common.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <% 
    	List<AlarmVO> alarmList = (List<AlarmVO>) request.getAttribute("alarmList");
    %>
    

    [
    	<%
    	for(int i=0; i<alarmList.size(); i++){
	    	AlarmVO alarm = alarmList.get(i);
	    		if(i>0) out.print(",");
	    	%>
	    	{
	    		"alarm_seq" : "<%= alarm.getAlarm_seq() %>",
	    		"alarm_content" : "<%= alarm.getAlarm_content() %>",
	    		"mem_id" : "<%= alarm.getMem_id() %>"
	    	}
	    <%
	    }
	    %>
	]

    	