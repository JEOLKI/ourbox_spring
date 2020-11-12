<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/ourbox/css/public.css">

<script src="/ourbox/js/jquery-3.5.1.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>ourbox</title>

<script type="text/javascript">
	$(function(){
		$('#logout').on('click',function(){
			window.location.href='/ourbox/view/main/logout.jsp'
		})
	})

</script>


<style type="text/css">

	body{
		min-width: 1400px;
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
		height: 31px;
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
		margin-top: 10px;
		margin-left: 15px;
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
	
	section{
		width : calc(85% - 1%);
		height : 780px;
		float : right;
		margin: 5px;
		margin-right: 0px;
	}
	
	section #mainFrame{
		width: 100%;
		height: 780px;
		border: none;
	}
	
	#right{
		width : calc(16% - 1%);
		height : 700px;
		background: #E6E6E6;
		float : left;
		margin-left: 0px;
		padding: 0px;
		display: inline-block;
	}
	
	#chatButton{
		width : 30px;
		background: yellow;
		float: right;
		display: inline-block;
	}
	
	#chat{
		width: 80%;
		height: 650px;
		border: 1px dotted red;
		
	}
	
	
	footer{
		height: 70px;
		border-top : 1px solid #A4A4A4;
		background: white;
		clear : both;
		text-align: center;
		font-size: 1.5em;
		padding-top: 10px;
	}
 	
 	
 	a:link, a:visited{
 		text-decoration: none;
 		color: black;
 	}
 	
 	.bold{
 		vertical-align: middle;
 		
 	}
 	
 	.img-circle{
 		float: left;
 		margin: 10px;
 	}

</style>


</head>
<body>
   
    <nav>
    	<div id="navLeft">
    		<a href="/ourbox/view/ourbox/ourboxmanager.jsp"><img alt="ourbox.png" src="/ourbox/images/ourbox_logo.png"></a>
    	</div>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<div id="navMid">
    		<input type="text">
    		<button><img alt="돋보기.png" src="/ourbox/images/돋보기.png"></button>
    	</div>
    	<div id="navRight">
    		<a href="#" ><img alt="알림이모티콘.png" src="/ourbox/images/알림이모티콘.png"></a>
    		<a href="#" ><img alt="퀵메모메뉴버튼.png" src="/ourbox/images/퀵메모메뉴버튼.png"></a>
    		<a href="#" ><img alt="톱니.png" src="/ourbox/images/톱니.png"></a>
    	</div>
    
    </nav>
    
    <aside id="left">
		
		<div id="memProfile">
		
		<img src="/ourbox/images/관리자 프로필사진.png" class="img-circle" alt="관리자 프사" width="70" height="70"> 
		<br>
		관리자 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button id = "logout"type="button" class="btn btn-default btn-xs">logout</button>
		ourbox@ourbox.com
		</div>	
		
		<br>
		
        <div id="leftMenu">
			
       		<a href="<%=request.getContextPath()%>/MemberListServlet" target="ifr"><img alt="회원관리아이콘.png" src="/ourbox/images/회원관리아이콘.png"><span class="bold sizeUp">회원관리</span></a><br><br>
        	<a href="<%=request.getContextPath()%>/PlanManagerList" target="ifr"><img alt="요금제관리 아이콘.png" src="/ourbox/images/요금제관리 아이콘.png"><span class="bold sizeUp">요금제관리</span></a><br><br>
        	<a href="/ourbox/view/notice/noticeMain.html" target="ifr"><img alt="공지아이콘.png" src="/ourbox/images/공지아이콘.png"><span class="bold sizeUp">공지관리</span></a><br><br>
        	<a href="/ourbox/view/qna/managerQnaMain.html" target="ifr"><img alt="관리자 Qna아이콘.png" src="/ourbox/images/관리자 Qna아이콘.png"><span class="bold sizeUp">QnA 게시판관리</span></a><br><br>
        </div>
    </aside>
   
   
    <section id="main">
        <article id="article1">
        
        <iframe id="mainFrame" src="<%=request.getContextPath()%>/MemberListServlet" name="ifr"> </iframe>
        
        </article>
        
        
    </section>
   
	   
	<footer>
	<span class='footerspan'>ourbox</span>
	</footer>






</body>
</html>