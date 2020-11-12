<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	MemberVO mv = (MemberVO) request.getAttribute("member");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<script src="/ourbox/js/jquery-3.5.1.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$('.mypage').on('click',function(){ // 프로필 칸 클릭 시 
			if($('#mygroup',parent.document).attr('src')=='mypage.jsp?memId='+'<%=mv.getMem_id()%>'){
				$('#mygroup',parent.document).attr('src','/ourbox/RoomListController?memId=<%=mv.getMem_id()%>')			
			}else{
				$('#mygroup',parent.document).attr('src','mypage.jsp?memId='+'<%=mv.getMem_id()%>')
			}
		})
		$('#logout').on('click',function(){
			window.parent.location.href='/ourbox/view/main/logout.jsp'
		})
		
		$('#proimg').on('click', function() {
		
			window.open('/ourbox/view/member/profile.jsp?memId='+'<%=mv.getMem_id()%>', '_blank','width=300px,height=500px');
			
			
		})
	})
</script>

<style type="text/css">

	 .img-circle{
 		float: left;
 		margin: 10px;
 	}
	
	body {
		background: #E6E6E6;
	}

</style>


</head>
<body>
	<div id="memProfile" class="mypage">
	
   		<img id='proimg' src="/ourbox/images/<%= mv.getMem_profile() %>"class="img-circle" alt="프로필사진" width="70" height="70"> 
   		
		<br>
		<%=mv.getMem_name() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-default btn-xs"  id="logout">logout</button><br>
		<%=mv.getMem_id() %>
		
	</div>	
</body>
</html>