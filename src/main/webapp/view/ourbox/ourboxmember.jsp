<%@page import="java.util.List"%>
<%@page import="ourbox.common.vo.RoomVO"%>
<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! MemberVO mv; 
		String msg;
		List<RoomVO> roomList;
		RoomVO rv;
	%>
    <% mv =  (MemberVO)session.getAttribute("vo");
    	String mem_id = mv.getMem_id();
    
    	msg = (String)request.getAttribute("msg");
    	roomList = (List<RoomVO>)request.getAttribute("roomList");
    %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/ourbox/css/public.css">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="/ourbox/js/alarm.js"></script>

<title>ourbox</title>

<script type="text/javascript">
	$(function(){
		if(<%=msg%> == "사용자 자신은 초대 할 수 없습니다."){
			alert("사용자 자신은 초대 할 수 없습니다.");
		}
	})

	$(function() {
	
		$('#alarmModal').on('click', function() {
			
			memId = $(this).attr("memId");
			
			alarmList(memId);		
			
		})
		
		
		$('.modal-body').on('click', '.alarmDelete', function() {
		
			alarm_seq = $(this).attr('id');
			
			deleteAlarm(alarm_seq);
			
			$(this).parents('.alarmContent').remove();
		
			
		})
		
		
		$('#alarmAllDelete').on('click', function() {
			
			memId = $(this).attr("memId");
			
			deleteAllAlarm(memId);
			
			
		})
		
		$('#groupadd').on('click',function(){
			
			window.open("<%=request.getContextPath()%>/view/room/insertRoomForm.jsp?memId=<%=mem_id%>", "그룹추가", "width = 450, height = 200, top = 100, left = 200, location = no");
			
		})
		
		
		
		
	})
	
	function resizeIframe(obj) {
    	obj.style.height = '0px';
    	obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
  	}	
	

</script>

<style type="text/css">

	body{
		min-width: 1500px;
	}
	
	aside, section, article, footer{
		box-sizing: border-box;
	}

	nav{
		border-bottom: 1px solid #A4A4A4;
		min-width: 1400px;
		background: #E6E6E6;
		height: 65px;
	}
	
	nav img{
		margin: 12px;
	}
	
	nav div{
		display: inline-block;
		vertical-align: middle;
	}
	
	nav input{
		width: 500px;
		height: 30px;
		border: none;
		vertical-align: middle;
		border-radius: 10px 0px 0px 10px;
	}
	
	nav div button{
		border: none;
		position : absolute;
		height: 30px;
		width : 40px;
		background: white;
		padding: 0px;
		vertical-align: middle;
		border-radius: 0px 10px 10px 0px;
	}	
	
	nav div button img {
		margin : 0px;
		width: 15px;
		height: 15px;
	}
	
	#navRight{
		float: right;
		margin: 19px;
		padding: 0px;
	}
	
	#navRight img{
		margin-top: 0px;
		margin-bottom: 0px;
		width: 28px;
		height: 28px;
	}
	
	#left{
		width : calc(16% - 1%);
		height : 800px;
		background: #E6E6E6;
		float : left;
		padding: 10px;
	}
	
	#memProfile{
		width: 100%;
		height: 100px;
	}

 	#leftMenu{
		margin-top: 10px;
 		background: #E6E6E6;
 		padding: 10px;
 	}
 	
 	#leftMenu img{
 		width: 20px;
 		height: 20px;
 		margin-right: 10px;
		vertical-align: middle;
 	}
 	
 	
 	#leftBottom{
 		position: absolute;
 		left: 20px;
 		top: 700px;
 		width: 14%;
 	}
	
	#capacity{
		width: 95%;
		height: 100px;
		border: none;
	}
	
	section{
		width : calc(85% - 1%);
		height : 780px;
		float : right;
		margin: 5px;
		margin-right: 0px;
	}
	
	section #mainFrame{
		width: 99%;
		height: 780px;
		border: none;
	}
	
	footer{
		height: 70px;
		border-top : 1px solid #A4A4A4;
		background: white;
		clear : both;
		text-align: center;
		font-size: 1.5em;
	}
 	
 	a:link, a:visited{
 		text-decoration: none;
 		color: black;
 	}
 	
 	#mainFrame{
 		border: none;
	}
	
	#profile{
		width: 100%;
		height: 100px;
		border: none;
	}
	
	#mygroup{
		width: 100%;
		border: none;
		height: 100%;
	
	}
	
	 #groupinput{
	 	width: 100%;
		border: none;
		height: 100%;
	 }
	 
	 .modal-title img{
	 	width: 28px;
	 	height: 28px;
	 }
	 
	 .alarmDelete{
	 	float: right;
	 	width : 100px;
	 	height: 100%;
	 	border: none;
	 	background: none;
	 	border-left: 1px solid #D5D5D5;
	 	color: #D5D5D5;
	 }
	 
	 .alarmContent{
	 	margin: 5px;
	 	padding: 8px;
	 	height: 40px;
	 }
	 
	 #groupadd{
	 	position: absolute;
	 }
	 
	 a:link, a:visited{
 		text-decoration: none;
 		color: black;
 	}
 	
 	.fdiv{
 		margin-top: 23px;
 		text-align: center;
 	}
 	
 	#footerimg{
 		width: 80px;
 		height: 18px;
 	}
 	
 	.fspan{
 		font-size: 0.7em;
 		font-style: italic;
 	}
 	.fspan2{
 		margin-top: 0px;
 	}
	
</style>




</head>
<body>
   
   <nav>
    	<div id="navLeft">
    		<a href="/ourbox/view/ourbox/ourboxmember.jsp"><img alt="ourbox.png" src="/ourbox/images/ourbox_logo.png"></a>
    		
    	</div>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<div id="navMid">
    	
    		<input type="text">
    		<button><img alt="돋보기.png" src="/ourbox/images/돋보기.png"></button>
    		
    	</div>
    	
    	<div id="navRight">
    		<a id="alarmModal" memId="<%=mem_id%>" data-toggle="modal" data-target="#myModal"><img alt="알림이모티콘.png" src="/ourbox/images/알림이모티콘.png"></a>
    		
    		<a href="/ourbox/MemoListController?memId=<%=mem_id%>" 
    			onclick="window.open(this.href, '_blank', 'width=330px,height=420px'); return false;">
			<img alt="퀵메모메뉴버튼.png" src="/ourbox/images/퀵메모메뉴버튼.png"></a>
    		
    		<a href="#" ><img alt="setting.png" src="/ourbox/images/setting.png"></a>
    		
    	</div>
    
    </nav>
    
    <aside id="left">
		 
		<div id="memProfile">
			<iframe src ="/ourbox/MemProfileController?memId=<%=mem_id%>" name="profile" id="profile"></iframe>
		</div>	

        <div id="leftMenu">

       		<a href="/ourbox/noticeList" target="ifr"><img alt="시작하기아이콘.png" src="/ourbox/images/시작하기아이콘.png"><span class="bold sizeUp">ourbox</span></a><br><br>
        	
        	<!-- mem_id 받아서 리스트서블렛으로 보냄 -->
        	<iframe id="mygroup" src="<%=request.getContextPath()%>/RoomListController?memId=<%=mv.getMem_id() %>" target="group" name="mygroup" frameborder="0" scrolling="no" onload="resizeIframe(this)"></iframe><br>
			
        	<!-- 그룹추가버튼  -->
        	<span id="groupadd" class="bold sizeUp"> + GROUP 생성</span>
        	
        </div>
        
        <div id="leftBottom">
     	
     		&nbsp;&nbsp;&nbsp;&nbsp;<a href="/ourbox/GargabegeListController?memId=<%=mem_id%>" target="ifr"><span class='bold' >휴지통</span></a><br>
     		&nbsp;&nbsp;&nbsp;&nbsp;<a href="/ourbox/MemberQnaPageListController?memId=<%=mem_id%>" target="ifr"><span class='bold'>도움말 및 QnA</span></a>
        	<br><br>
	        <iframe src="<%=request.getContextPath()%>/DriveSizeController?memId=<%=mv.getMem_id() %>" name="capacity" id="capacity" >
	        
	        
	        </iframe>

        </div>
        
    </aside>
   
   
    <section id="main">
        <article id="article1">
        
        <iframe id="mainFrame" src="/ourbox/noticeList" name="ifr"> </iframe>
        
        </article>
        
    </section>
   
	   
	<footer>
	
	
	<div class='fdiv'>
	
		<img id='footerimg' alt="ourbox.png" src="/ourbox/images/ourbox_logo.png">
		
		<br>
		<br>
		<span class="fspan">드라이브, 게시글, 채팅, 메모를 위한 올인원 워크스페이스입니다.<span>
		<br>
		<span class="fspan2">© 2020 ourbox Labs, Inc.<span>
		<br>
		<span >dev. jeonggi-Hong, taekyeong-Kang, myungho-Lee, dongju-Lee, yunji-Choi <span>
		<br>
		<span >thanks. ddit's professor<span>
		
		<br>
		<br>
	
	</div>
	
	
	
	</footer>




<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><img alt="알람아이콘" src="/ourbox/images/알림이모티콘.png"> ALARM</h4>
        </div>
        
        <div class="modal-body">
        
          
        </div>
        
        
        <div class="modal-footer">
          <button id="alarmAllDelete" type="button" class="btn btn-default" memId="<%=mem_id%>">전체지우기</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>




</body>
</html>