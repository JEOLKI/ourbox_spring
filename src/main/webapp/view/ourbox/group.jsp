<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String mem_id = request.getParameter("memId");
	int room_seq = Integer.parseInt(request.getParameter("roomSeq"));

%>

    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ourbox : 그룹 페이지</title>

<link rel="stylesheet" href="/ourbox/css/public.css">
<script src="/ourbox/js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	$(function() {
	
		$('#chatHidden').on('click', function() {
			
			$(this).hide();
			$('#chatOpen').show();
			
			$('#chat').hide();
			
			$('.container').css('width','97%');
			
		})
		
		$('#chatOpen').on('click', function() {
			
			$(this).hide();
			$('#chatHidden').show();
			$('#chat').show();
			$('.container').css('width','80%');
			
		})
		
		$('#driveOpen').on('click', function() {
		
			$('#driveIframe').show();
			$('#driveOpen').hide();
			$('#driveClose').show();
			$('#boardIframe').hide();
			
		})
		
		$('#driveClose').on('click', function() {
		
			$('#driveIframe').hide();
			$('#driveClose').hide();
			$('#driveOpen').show();
			$('#boardIframe').show();
			
		})
		
		$('#boardOpen').on('click', function() {
		
			$('#boardIframe').show();
			$('#boardClose').show();
			$('#boardOpen').hide();
			$('#driveIframe').hide();
			
			
		})
		
		$('#boardClose').on('click', function() {
		
			$('#boardIframe').hide();
			$('#boardClose').hide();
			$('#boardOpen').show();
			$('#driveIframe').show();
		})
		
		
		
		
		
	})

</script>


<style type="text/css">

	#chat{
		background: #EAEAEA;
		float: right;
		width: 16%;
		height: 650px;
		margin-left: 0px;
		margin-right: 0px;
		margin-top: 40px;
		border-radius: 5px;
	}
	
	.container{
		float: left;
		margin-right: 0px;
		width: 80%;
	}
	
	.drive, .board{
		background: #F6F6F6;
		width: 100%;
		height: 40px;
		border-radius: 10px;
		padding-top: 15px;
		padding-left: 15px;
		border: 1px solid #BDBDBD; 
	}
	
	
	button{
		float: right;
		vertical-align: middle;
		border: none;
		background: white;
		margin-top: 300px;
		margin-left: 0px;
	}
	
	
	button img{
		width: 20px;
		height: 90px;
	}
	
	#chatOpen, #driveClose, #boardClose{
		display: none;
	}
	
	.container iframe {
		width: 100%;
		margin: 5px;
		height: 600px;
		border: none;
	}
	
	#chatIframe{
		width: 100%;
		border: none;
		height: 600px;
		overflow: hidden;
	}
	
	#memListIframe{
		width: 95%;
		border: none;
		background: white;
		height: 60px;
		margin: 2%;
		margin-bottom: 0px;
		border-radius: 5px;
		overflow-y: hidden;
	}
	
	#boardIframe{
		display: none;
	}
	
</style>



</head>
<body>


	<div class="container">
	  
	  <div class="drive" id="driveOpen"><span class="bold">DRIVE</span></div>
	  <div class="drive" id="driveClose"><span class="bold">DRIVE</span></div>
	  
	  <iframe id="driveIframe" src="/ourbox/DriveListController?roomSeq=<%=room_seq%>"></iframe>

	  <hr>  
	  <div class="board" id="boardOpen"><span class="bold">BOARD</span></div>
	  <div class="board" id="boardClose"><span class="bold">BOARD</span></div>
	  
	  <iframe id="boardIframe" src="/ourbox/board/view?memId=<%=mem_id %>&roomSeq=<%=room_seq%>"></iframe>
	  
	</div>

	<button id="chatHidden"><img alt="chatRoomCloseBtn.jpg" src="/ourbox/images/chatRoomCloseBtn.jpg"></button>
	<button id="chatOpen"><img alt="chatRoomOpenBtn.jpg" src="/ourbox/images/chatRoomOpenBtn.jpg"></button>
	
	<div id="chat">
	  <iframe id="memListIframe" src="/ourbox/member/chatMemberList?roomSeq=<%=room_seq%>"></iframe>
	  <iframe id="chatIframe" src="/ourbox/view/chat/chatpage.jsp"></iframe>
	
	</div>
	




</body>
</html>