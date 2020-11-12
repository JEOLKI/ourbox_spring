<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% MemberVO vo = (MemberVO)request.getAttribute("mv");
	String mem_id = (String)request.getAttribute("memId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/public.css">
<script>
	
</script>
<style>
	#icon{
		width: 45px;
		height: 45px;
		vertical-align:top;
	}
	h1{
		font-weight: bold;
	}
	#print{
		background: #F6F6F6;
		margin: 20px;
	}
/* 	#left{ */
/* 		float: left; */
/* 	} */
/* 	#profile{ */
/* 		width:250px; */
/* 		height: 200px; */
/* 		margin : 10px; */
/* 	} */
/* 	label{ */
/* 		width: 80px; */
/* 	} */
	table{
		margin : 30px;
		margin-left: 150px;
		border-collapse : collapse;
		border : 1px solid #F6F6F6;
		border-bottom: 2px solid #D5D5D5;
	}


	.title{
		width:  150px;
		height: 50px;
		padding-left: 10px;
		font-weight: bold;
		font-size: 1.3em;
	}
	.content{
		width: 700px;
		font-size: 1.2em;
	}
	
	#proimg{
		border: 1px solid #D5D5D5;
		margin: 10px;
		width: 150px;
		height: 150px;
	}

</style>
<title>회원 정보 수정</title>
</head>
<body>
	<div id="box">
	
	<h1><img id="icon" src="/ourbox/images/내정보조회아이콘.png">내정보 조회</h1>
	<br>
	<div id = "print">
		<br>
		<form action="<%=request.getContextPath()%>/MemberSelectServlet" method ="post">
		
<!-- 			<section id = "left"> -->
<%-- 				<img id = "profile" src="/ourbox/images/<%=vo.getMem_profile()%>"> --%>
<!-- 			</section> -->
			
			<section id = "information">
<!-- 				<label>아이디</label> -->
<%-- 				<label><%=vo.getMem_id()%></label> --%>
				<table border="1">
					<tr>
						<td rowspan="3"><img id="proimg"  class="img-circle"  src="/ourbox/images/<%= vo.getMem_profile()%>"></td>
						<td class="title">아이디</td>
						<td class="content"><%=vo.getMem_id()%></td>
					</tr>
					<tr>
						<td class="title">이름</td>
						<td class="content"><%=vo.getMem_name()%></td>
					</tr>
					<tr>
						<td class="title">닉네임</td>
						<td class="content" ><%=vo.getMem_nickname()%></td>
					</tr>
					<tr>
						<td class="title">전화번호</td>
						<td class="content" colspan="2"><%=vo.getMem_tel()%></td>
					</tr>
					<tr>
						<td class="title">생년월일</td>
						<td class="content" colspan="2"><%=vo.getMem_bir()%></td>
					</tr>
					<tr >
						<td class="title">주소</td>
						<td class="content" colspan="3"><%=vo.getMem_zip()%></td>
					</tr>
					<tr>
						<td class="title"></td>
						<td class="content" colspan="2"><%=vo.getMem_addr1() %><%=vo.getMem_addr2() %></td>
					</tr>
					<tr>
						<td class="title">전화번호</td>
						<td class="content" colspan="2"><%=vo.getMem_tel()%></td>
					</tr>
				</table>
				<br>
			</section>
		</form>
	</div>
	</div>
</body>
</html>