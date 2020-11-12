<%@page import="ourbox.common.vo.PlanVO"%>
<%@page import="ourbox.common.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<PlanVO> planlist = (List<PlanVO>)request.getAttribute("planlist");	
List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원목록</title>

<script type="text/javascript">

$(function(){
	
	$('.select').mousedown(function() {
		
		
		planseq = $(this).attr('seq');
		console.log(planseq)
		
	});
	$('.select').on('click',function(){
		$.ajax({
			url : '/ourbox/MemberPlanServlet',
			type : 'get',
			data : { "planseq" : planseq},
			success : function(res){
				$('#tt').empty();
				$('#tt').html(res);
			},
			error : function(xhr){
				alert(xhr.status);
			}
		});
	});
});

</script>
<style type="text/css">
#img1{
	width: 40px;
	height: 30px;
	margin-right: 10px;
	margin-left: 50px;
}
.table{
	margin : 20px;
}
.container{
 	float: right; 
 	width: 200px; 
}
h2{
height: 20px;
	}
	#td1{
		background-color: #D5D5D5;
	}
	#tr1{
		
	}
	#title{
		margin-top:30px;
	}
	.dropdown{
		float: right;
		width: 100px;
	}
	.box{
		width: 81%;
		margin-left: 5%
	
	}
</style>
</head>
<body>
<div class="box">
<div id="title"><h2><img id="img1" alt="회원관리아이콘.png" src="/ourbox/images/회원관리아이콘.png"><b>회원 관리</b></h2></div>
<div class="container">
  <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">요금제기준
    <span class="caret"></span></button>
    <ul class="dropdown-menu" role= "menu" aria-labelledby="menu1">
<%
	for(int i = 0; i<planlist.size(); i++){
%>
      <li role="presentation">
      <a class="select" seq="<%=planlist.get(i).getPlan_seq()%>" role="menuitem" tabindex="-1" >
      <%= planlist.get(i).getPlan_name() %></a></li>
 <%
	}
%>
    </ul>
  </div>
</div>

	<table class="table table-hover">
	<thead>
		<tr id="tr1">
			<td id="td1"><b>아이디</b></td>
			<td id="td1"><b>비밀번호</b></td>
			<td id="td1"><b>닉네임</b></td>
			<td id="td1"><b>이름</b></td>
			<td id="td1"><b>생년월일</b></td>
			<td id="td1"><b>전화번호</b></td>
			<td id="td1"><b>우편번호</b></td>
			<td id="td1"><b>주소</b></td>
			<td id="td1">회원상태</td>
		</tr>
		</thead>
		<tbody id="tt">
	<%
		int memSize = memList.size();
		if(memSize > 0){
			for(int i =0; i < memSize; i++){
	%>
		<tr>
			<td><%=memList.get(i).getMem_id() %></td>
			<td><%=memList.get(i).getMem_pass()%></td>
			<td><%=memList.get(i).getMem_nickname() %></td>
			<td><%=memList.get(i).getMem_name() %></td>
			<td><%=memList.get(i).getMem_bir() %></td>
			<td><%=memList.get(i).getMem_tel() %></td>
			<td><%=memList.get(i).getMem_zip() %></td>
			<td><%=memList.get(i).getMem_addr1() %>	<%=memList.get(i).getMem_addr2() %></td>
			<td>
			<% if(Integer.parseInt(memList.get(i).getMem_status()) == 0){%>
				이용중
			<%}else{%>
				탈퇴
				<% }%>
			</td>
			
		</tr>
		<% }%>
		
		
		<% }%>
	
	
		
			</tbody>
		</table>
	</div>
</body>
</html>