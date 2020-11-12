<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<%! MemberVO mv; 
		String msg;
	%>
    <% mv =  (MemberVO)session.getAttribute("vo"); 
    	msg = (String)request.getAttribute("msg"); 
    %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-3 sidenav">
	    <input type="hidden" id = "mem_id" name = "mem_id" value="<%=mv.getMem_id()%>">
	    <input type="hidden" id = "mem_pass" name = "mem_pass" value="<%=mv.getMem_pass()%>">
	    <input type="hidden" id = "mem_nickname" name = "mem_nickname" value="<%=mv.getMem_nickname()%>">
	    <input type="hidden" id = "mem_name" name = "mem_name" value="<%=mv.getMem_name()%>">
	    <input type="hidden" id = "mem_bir" name = "mem_bir" value="<%=mv.getMem_bir()%>">
	    <input type="hidden" id = "mem_tel" name = "mem_tel" value="<%=mv.getMem_tel()%>">
	    <input type="hidden" id = "mem_zip" name = "mem_zip" value="<%=mv.getMem_zip()%>">
	    <input type="hidden" id = "mem_addr1" name = "mem_addr1" value="<%=mv.getMem_addr1()%>">
	    <input type="hidden" id = "mem_addr2" name = "mem_addr2" value="<%=mv.getMem_addr2()%>">
	    <input type="hidden" id = "mem_status" name = "mem_status" value="<%=mv.getMem_status()%>">
	    <input type="hidden" id = "mem_profile" name = "mem_profile" value="<%=mv.getMem_profile()%>">
    <p><%=mv.getMem_name()%>님 안녕하세요</p>
      <a href="logout.jsp" id="logout">로그아웃</a><br><br>
      <hr>
      <p><img src="start.png"><a href="#">그룹추가</a></p>
      <p><img src="pro.png"><a href="<%=request.getContextPath()%>/MemberSelectServlet?mem_id=<%=mv.getMem_id()%>" target="ifr">내정보 조회</a></p>
      <p><img src="start.png"><a href="<%=request.getContextPath()%>/MemberUpdateController" target="ifr">정보수정</a></p>
      <p><img src="start.png"><a href="../member/profile.jsp" target="ifr">프로필사진 수정</a></p>
      <p><img src="start.png"><a href="<%=request.getContextPath()%>/MemberSendController" target="ifr">회원탈퇴</a></p>
      <p><img src="start.png"><a href="<%=request.getContextPath()%>/PlanListServlet?mem_id=<%=mv.getMem_id()%>" target="ifr">구독신청</a></p>
      <p><img src="start.png"><a href="<%=request.getContextPath()%>/PlanSelectServlet?mem_id=<%=mv.getMem_id()%>" target="ifr">나의 요금제 변경/해지</a></p>
    </div>
	    <div id="search"><input type="text"><input type="button" value="검색"></div>
	    <div class="col-sm-9 text-left">
      <p style="margin: 20px;">공지내용</p>
      <hr>
      <h1>시작하기</h1>
      <p>환영합니다. ourbox입니다.</p>
      <iframe id = "fn"src="" style="width:100%; height:500px; border: px solid gray" name = "ifr">
      </iframe>
    </div>
  </div>
</div>
</body>
</html>