<%@page import="ourbox.common.vo.PlanVO"%>
<%@page import="ourbox.common.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<MemberVO> list = (List<MemberVO>)request.getAttribute("list");
%>    
	<%
		int memSize = list.size();
		if(memSize > 0){
			for(int i =0; i < memSize; i++){
	%>
		<tr>
			<td><%=list.get(i).getMem_id() %></td>
			<td><%=list.get(i).getMem_pass()%></td>
			<td><%=list.get(i).getMem_nickname() %></td>
			<td><%=list.get(i).getMem_name() %></td>
			<td><%=list.get(i).getMem_bir() %></td>
			<td><%=list.get(i).getMem_tel() %></td>
			<td><%=list.get(i).getMem_zip() %></td>
			<td><%=list.get(i).getMem_addr1() %>	<%=list.get(i).getMem_addr2() %></td>
			<td>
			<% if(Integer.parseInt(list.get(i).getMem_status()) == 0){%>
				이용중
			<%}else{%>
				탈퇴
				<% }%>
			</td>
		</tr>
	
	
	<% 
			}
		}else{ // 회원정보가 존재하지 않을 경우...
	%>
		<tr>
			<td colspan="9">회원정보가 존재하지 않습니다.</td>
		</tr>
			
	<%		
		}
	%>
