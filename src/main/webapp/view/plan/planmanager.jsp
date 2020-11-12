<%@page import="ourbox.common.vo.PlanVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  	List<PlanVO> planlist = (List<PlanVO>)request.getAttribute("planlist");
  	List<PlanVO> list = (List<PlanVO>)request.getAttribute("list");
  
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>요금제</title>
<script type="text/javascript">
	

</script>
<style type="text/css">
.box{
	width : 200px;
	float: left;
	padding: 10px;
	margin : 20px;
	border: 1px solid gray;
	height: auto;
	border-radius: 0.5em;
	border-top-left-radius: 0.5em;
	border-top-right-radius: 0.5em;
	border-bottom-left-radius: 0.5em;
	border-bottom-right-radius: 0.5em;
}
.in{
	margin: 10px;
	margin-left: 5%;
	margin-top: 3%
}
h3{
	text-align: center;
}
#id2{
	color : blue;
	text-align: center;
}
#dd{
	border : 1px solid gray;
	margin-left :40px;
	padding:5px;
	width: 100px;
	text-align: center;
	border-radius: 0.5em;
	border-top-left-radius: 0.5em;
	border-top-right-radius: 0.5em;
	border-bottom-left-radius: 0.5em;
	border-bottom-right-radius: 0.5em;
}
#img1{
	width: 50px;
	height: 40px;
	margin-right: 10px;
	margin-left: 50px;
}
#deleteplan{
		margin-left: 50px; 
		
}
h2{
margin-top:5%;
margin-left: 2%;
}

p{
	clear : both;
}
#free{
	margin-top: 20px;
}
#id2{
	color : #79ABFF;
	text-align: center;
}
#dd{
	background : #B2CCFF;
	border: none;
}
</style>
</head>
<body>
<h2><img id="img1"alt="요금제관리 아이콘.png" src="/ourbox/images/요금제관리 아이콘.png"><b>요금제관리</b></h2>
<div class="in">
<form id = "freebox" action="<%=request.getContextPath()%>/PlanInsertServlet" method="post">
	<div class = "box" >
			<div id="free">
					<label for="planname"><b>요금제 이름</b></label>
					<INPUT type="text" id = "planname" name="planname"/>
					<br><br>
					<label for = "planprice"><b>가 격</b></label>
					<input type="text" name ="planprice" id = "planprice">
					<br>
				<hr>
					<label for="planContent"><b>요금제 설명</b></label>
					 <input type="text" name="planContent" id="planContent">
					<br><br>
					<label for="planstocap"><b>저장 공간 용량</b></label>
					<input type="text" name="planstocap" id="planstocap">
				<div id="note">
				</div>
			<br>
			<p></p>
			<input class="btn btn-default" id="subtn" type="submit" value="등록" onclick="FormSubmit()">
			</div>
	</div>
</form>



<%
if(list==null){
	
	for(int i = 0; i<planlist.size(); i++){
%>
	
<form id = "deleteplan" action="" method="post">
	<div class = "box" seq="<%= planlist.get(i).getPlan_seq() %>">
		<div>
			<h3><%= planlist.get(i).getPlan_sto_cap() %>GB</h3>
			<div id="dd"><p>$ <%= planlist.get(i).getPlan_price() %>/월</p></div><br>
			 또는 연 단위로 선불 가능 : <br><br>
			 <p id="id2">$ <%= planlist.get(i).getPlan_price()*10 %>/년</p>
			<hr>
			제공혜택<br><br>
			스토리지 : <%= planlist.get(i).getPlan_sto_cap() %>GB<br>
			<%= planlist.get(i).getPlan_Content() %><br>
		</div>
		<br>
		<button class="btn btn-default"><a href="<%=request.getContextPath()%>/PlanDeleteServlet?planseq=<%=planlist.get(i).getPlan_seq()%>">삭제</a>
</div>
</form>
	
<%}%>

<%
	}else{
	for(int i = 0; i<list.size(); i++){
%>
<form id = "deleteplan" action="" method="get">
	<div class = "box" seq="<%= list.get(i).getPlan_seq() %>">
		<div>
			<h3><%= list.get(i).getPlan_sto_cap() %>GB</h3>
			<div id="dd"><p>$ <%= list.get(i).getPlan_price() %>/월</p></div><br>
			 또는 연 단위로 선불 가능 : <br><br>
			 <p id="id2">$ <%= list.get(i).getPlan_price()*10 %>/년</p>
			<hr>
			제공혜택<br><br>
			스토리지 : <%= list.get(i).getPlan_sto_cap() %>GB<br>
			<%= list.get(i).getPlan_Content() %><br>
		</div>
		<br>
		<button class="btn btn-default"><a href="<%=request.getContextPath()%>/PlanDeleteServlet?planseq=<%=planlist.get(i).getPlan_seq()%>">삭제</a>
</div>
</form>

<%} %>
<%} %>
</div>
	


</body>
</html>