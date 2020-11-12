<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! MemberVO mv; 
       String memId;
    %>
    <% mv = (MemberVO)request.getAttribute("vo"); 
       memId = (String)request.getParameter("memId");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/ourbox/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="/ourbox/css/public.css">


<title>Insert title here</title>
<style>
	
	a:link, a:visited{
 		text-decoration: none;
 		color: black;
 	}
	
	span{
		display: inline-block;
		margin-bottom: 8px;
	}
	
	.spanMenu{
		margin-left: 10px;
	}
	
	.sizeDown{
		font-size: 0.95em;
	}
	
	.tra{
		width: 20px;
		height: 20px;
	}
	
	.meimg{
		width: 23px;
		height: 23px;
		vertical-align: top;
		margin-right: 5px;
	}
	
</style>
<script>
	
	
</script>
</head>
<body>

<div>
		<img class="meimg" alt="emoji_mypage.png" src="/ourbox/images/emoji_mypage.png"><span class='bold'>내 정보 </span><img class="tra" alt="triangle.png" src="/ourbox/images/triangle.png">
		<br>
		<a href="<%=request.getContextPath()%>/MemberSelectServlet?memId=<%=memId%>" target="ifr"><span class="spanMenu">내 정보 조회</span></a><br>
		<a href="<%=request.getContextPath()%>/MemberUpdateController" target="ifr"><span class="spanMenu">내 정보 수정</span></a><br>
		<a href="<%=request.getContextPath()%>/MemberSendController" target="ifr"><span class="spanMenu">회원 탈퇴</span></a><br>
	
	<br>
	
		<img class="meimg" alt="emoji_dollor.png" src="/ourbox/images/emoji_dollor.png"><span class='bold'>나의 요금제</span><img class="tra" alt="triangle.png" src="/ourbox/images/triangle.png">
		<br>
		<a href="<%=request.getContextPath()%>/PlanListServlet?mem_id=<%=memId%>" target="ifr"><span class="spanMenu">구독신청</span></a><br>
		<a href="<%=request.getContextPath()%>/PlanSelectServlet?mem_id=<%=memId%>" target="ifr"><span class="spanMenu sizeDown">나의 요금제 변경/해지</span></a><br>
	
	<br>

</div>

</body>
</html>