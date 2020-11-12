<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="ourbox.common.vo.PlanVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  String memId = (String)request.getAttribute("memid");
  List<PlanVO> planlist = (List<PlanVO>)request.getAttribute("planlist");
  
  // 원화 단위 표시
  NumberFormat numf = NumberFormat.getInstance(Locale.getDefault());
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/ourbox/js/jquery-3.5.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/public.css">
<link rel="stylesheet" href="css/plan.css">
<title>요금제</title>
<script type="text/javascript">

$(function(){
	
	
	$('.in').on('click','.box' ,function(){
		$('.box').css('border','1px solid gray');
		
		$(this).css('border','2px solid #6799FF');
	})
	
	$('.box').mousedown(function() {
		
		planseq = $(this).attr('seq');
		mem_id = $('#mem').val();
		console.log(mem_id)
		console.log(planseq)
		
	})
	//결제버튼 클릭 이벤트
	$('#btn').on('click',function(){
		$.ajax({
			url : '/ourbox/PlanPayMemberServlet',
			type : 'get',
			data : { "planseq" : planseq,
					"memid" : mem_id},
			success : function(res){
				alert(res.sw);
			},
			error : function(xhr){
    			alert("상태 : " + xhr.status);
    		},
    		dataType : 'json'
		})
	})
	
})

</script>
<style type="text/css">
.box{
	width : 200px;
	float: left;
	padding:10px;
	margin : 10px;
	border: 1px solid gray;
	height: auto;
	border-radius: 0.5em;
	border-top-left-radius: 0.5em;
	border-top-right-radius: 0.5em;
	border-bottom-left-radius: 0.5em;
	border-bottom-right-radius: 0.5em;
}
/* .box:hover { */
/*   border:  2px solid #6799FF; */
/* } */
.in{
	margin: 10px;
	height: 100%;
}
h3{
	text-align: center;
}
#id2{
	color : #79ABFF;
	text-align: center;
}
#dd{
	background : #B2CCFF;
	margin-left :50px;
 	
	width: 100px;
	text-align: center;
/* 	border-radius: 05em; */
	height: 30px;
	margin-top : 10px;
	padding-top : 8px;
	vertical-align: middle;
	font-size: 0.9em;
}
p{
	clear : both;
}
span{
	display : block;
	text-align: center;
	font-size: 0.8em;
}
#free{
	margin-left: 45px;
}
#free #dd{
	background: white;
	border: 1px solid #E6E6E6;
	height: 30px;
	margin-top : 10px;
	padding-top : 13px;
	vertical-align: middle;
	font-size: 0.8em;
}
#btn{
		display : block;
		height: 23px;
		padding : 0px 20px;
		vertical-align: top;
		clear : both;
	}
	#note{
		padding-left: 5px;
	}
	#planBox{
		margin-left: 30px; 
		
	}
	button {
		margin-left: 41%;
		width : 220px;
	}
	
	#mimg{
		width: 40px;
		height: 40px;
		vertical-align: bottom;
		margin-right: 10px;
	}
	
</style>
</head>
<body>
<div id="box">
<h1><img id="mimg" alt="요금제관리 아이콘.png" src="/ourbox/images/요금제관리 아이콘.png">요금제 구독신청</h1>
<form action="<%=request.getContextPath()%>/PlanPayMemberServlet" method="get">
<input type="hidden" id="mem" name = "memid" value="<%=memId%>">
<br>
<div class="in">

<div id="free" class = "box" >
		<div >
			<h3>15 GB</h3>
			<span>무료</span>
			<div id="dd">현재 요금제</div><br>
			 <br><br><span>&nbsp;&nbsp;</span><span>&nbsp;&nbsp;</span>
			 <p id="id2"></p>
			<hr>
			<div id="note">
			제공혜택<br><br>
			스토리지 :15GB<br><br><br>
			</div>
		</div>
	</div>

<%
	for(int i = 0; i<planlist.size(); i++){
		
%>
	<div id="planBox" class = "box" seq="<%= planlist.get(i).getPlan_seq() %>">
		<div >
			<h3><%= planlist.get(i).getPlan_name() %></h3>
			<br>
			<div id="dd"> <%= NumberFormat.getCurrencyInstance(Locale.KOREA).format(planlist.get(i).getPlan_price()) %>/월</div><br>
			 또는 연 단위로 선불 가능 : <br><br>
			 <p id="id2"> <%= NumberFormat.getCurrencyInstance(Locale.KOREA).format(planlist.get(i).getPlan_price()*10) %>/년</p>
			<hr>
			<div id="note">
			제공혜택<br><br>
			스토리지 : <%= planlist.get(i).getPlan_name() %><br>
			<%= planlist.get(i).getPlan_Content() %><br>
			</div>
		</div>
	</div>
<%
	}
%>
	<p>&nbsp;</p>
	<button id="btn" type="button" class="btn btn-info">결 제</button>
</div>
</form>

</div>

</body>
</html>