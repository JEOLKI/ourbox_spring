<%@page import="java.util.List"%>
<%@page import="ourbox.common.vo.PlanUseVO"%>
<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! MemberVO mv; 
    List<PlanUseVO> planlist; 
	%>
    <% mv =  (MemberVO)session.getAttribute("vo"); 
    planlist =(List<PlanUseVO>)request.getAttribute("result");
    %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="jquery-3.5.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/plan.css">
<link rel="stylesheet" href="css/public.css">
  
  
<title>Insert title here</title>
<style type="text/css">
span{
	color : red;
}
</style>
</head>

<body>
<div id="box">
<img id="icon" src="/ourbox/images/emoji_dollor.png">
<h1>내 이용권 /결제 정보</h1>

<input type="hidden" id = "mem_id" name = "mem_id" value="<%=mv.getMem_id()%>">
	<br><br><br>
	<table >
		<tr id="title">
			<td>NO</td>
			<td>ourbox 이용권</td>
			<td>이용가능 서비스</td>
			<td colspan="2">다음 결제 안내</td>
		</tr>
		
<%
		for(int i = 0; i < planlist.size(); i++){
%>	
		<tr>
			<td><%=i+1 %></td>
			<td><%=planlist.get(i).getPlan_name() %></td>
			<td><%=planlist.get(i).getPlan_content() %><br><%=planlist.get(i).getPlan_price() %></td>
			<% if(planlist.get(i).getUse_status().equals("1")){ %>
			<td class="note" colspan="2">
				<span>서비스 이용이 종료되었습니다.</span>
			</td>
			<%}else{ %>
			<td class="note">
				<%=planlist.get(i).getUse_start() %>
			</td>
			<%} %>
			<% if(planlist.get(i).getUse_status().equals("0")){ %>
			<td class="note">
				<button id="but1" type="button" class="btn"><a href="<%=request.getContextPath()%>/PlanListServlet">변경</a></button>
				<button id="but1" type="button" class="btn"><a href="<%=request.getContextPath()%>/PlanCheckServlet?mem_id=<%=mv.getMem_id()%>">해지</a></button>
			</td>
			<%}%>
				
		</tr>
		
		
			<%} %>
		
	</table>
	</div>


</body>
</html>