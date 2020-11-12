<%@page import="ourbox.common.vo.RoomVO"%>
<%@page import="java.util.List"%>
<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! 
   		MemberVO mv; 
		List<RoomVO> roomList;
		RoomVO rv;
	%>
    <% 
    	mv =  (MemberVO)session.getAttribute("vo"); 
    	String mem_id = mv.getMem_id();
    
    	roomList = (List<RoomVO>)request.getAttribute("roomList");
   	%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/ourbox/css/rightmouse.css">
<link rel="stylesheet" href="/ourbox/css/public.css">	
<script src="/ourbox/js/rightmouse.js"></script>

<script type="text/javascript">

	$(function() {
		
			memId = "<%=mv.getMem_id()%>"
		
		$('.panel-title').mousedown(function() {
			event.stopPropagation()
			//$('.contextmenu').show();
			roomSeq = $(this).attr('roomSeq');
		})
		
		$('#inviteMember').on('click', function() {
			$('.contextmenu').hide();
			
			// 우클릭시 해당 그룹의 room_seq들고 보내야함
			event.stopPropagation()
			window.open("/ourbox/view/room/inviteForm.jsp?roomSeq="+roomSeq+"&memId="+memId, "회원초대", "width = 300, height = 350, top = 100, left = 200, location = no");

		})
		
		$('#deleteGroup').on('click', function() {
			
			$('.contextmenu').hide();
			
			event.stopPropagation()
			memId = "<%=mv.getMem_id()%>"
			location.href="/ourbox/RoomDeleteController?roomSeq=" + roomSeq+"&memId="+memId;
			parent.document.location.reload()// 새로고침
		})
	})
	
</script>

<style type="text/css">

	html, body {
		height: 100%;
		background: #E6E6E6;
	}
	
	 a:link, a:visited{
 		text-decoration: none;
 		color: black;
 	}
 	
 	img{
 		width: 20px;
 		height: 20px;
 		margin-right: 1px;
		vertical-align: middle;
 	}
 	
 	td{
 		height: 35px;
 	}

</style>



<title>Insert title here</title>
</head>
<body>
	<table>
		<%
			if(roomList !=null){ 
				for(int i = 0; i< roomList.size(); i++){ 
					rv = roomList.get(i);
		%> 
		<tr>
			<td>
			<a class = "panel-title" roomSeq="<%=rv.getRoom_seq()%>" href="/ourbox/view/ourbox/group.jsp?roomSeq=<%=rv.getRoom_seq()%>&memId=<%=mem_id %>" target="ifr">
			<img alt="그룹아이콘.png" src="/ourbox/images/그룹아이콘.png">
			<span class="bold"><%=rv.getRoom_name()%></span>
			</a></td>
		</tr>
		
		
		<%
				} 
			}else{ 
		%> 
		<tr>
			<td>생성한 그룹이 없습니다.</td>
		</tr>
		<%
			} 
		%> 
		<tr>
			<td></td>
		</tr>
	</table>
	
	
	<ul class="contextmenu">
	  <li><a href="#" id="inviteMember">초대하기</a></li>
	  <li><a href="#" id="deleteGroup">그룹탈퇴</a></li>
	</ul>
	
	
</body>
</html>