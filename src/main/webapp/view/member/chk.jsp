<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! MemberVO mv; %>
    <%
    mv = (MemberVO)session.getAttribute("vo"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">





<title>Insert title here</title>
</head>
<style>
	.mem_pass{
		display : block;
	}
	
	#deleteimg{
		width: 40px;
		height: 40px;
		vertical-align: top;
		display: inline-block;
	}
	
	h1{
		font-weight: bold;
		vertical-align: middle;
		display: inline-block;
	}
	
	#input_pass{
		width: 250px;
		height: 30px;
		border-radius: 10px
	}
	
	body{
		text-align: center;
	}
	
	#box{
		margin: 20%;
	}
	
	.chckpass{
		width: 120px;
		font-weight: bold;
		font-size: 1.2em;
	}
	
	#ok{
		margin-left: 20px;
		height: 30px;
		width: 70px;
	}
	
	#la2{
		font-size: 2em;
	}
	
</style>
<script>
$(function(){
	$('#result').hide();
	$('#ok').on('click',function(){
		if($('#input_pass').val()!='<%=mv.getMem_pass()%>'){
			$('#result').show();
// 			$('#btn').attr('data-toggle','click');
		}else if($('#input_pass').val()=='<%=mv.getMem_pass()%>'){
			$('#myModal').modal('toggle');
			$('#result').hide();
		}
	})
	
	$('#no').on('click',function(){
		$('#myModal').modal('hide');
	})
	
	$('#yes').on('click',function(){
		window.parent.location.href="/ourbox/view/main/login.jsp"
	})
	
	
})
//모달부분
</script>
<body>


<div id='box'>
	<form id = "delete" action="<%=request.getContextPath()%>/MemberDeleteContoller" method="post">
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
	
	<h1><img id="deleteimg" alt="회원탈퇴아이콘 (1).png" src="/ourbox/images/회원탈퇴아이콘 (1).png">회원 탈퇴</h1>
	
	<br>
	<br>
	
	<label class="chckpass">비밀번호 확인</label>
	
		<input type="password" id ="input_pass" name = "input_pass">
		
		<input type = "button" id="ok" value = "확 인">
		
		<div id = "result" style="color:red">비밀번호가 일치하지 않습니다.</div>
</div>	
	
	
	
	<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
	    
	<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h2 class="modal-title"><img id="deleteimg" alt="회원탈퇴아이콘 (1).png" src="/ourbox/images/회원탈퇴아이콘 (1).png">회원탈퇴</h2>
			</div>
			<div class="modal-body">
				<label id="la2" for="tel">탈퇴하시겠습니까?</label><br><br>
				<button type="submit" id="yes">예</button>
				<!-- 수정요망 -->
				<button type="button" id="no">아니오</button>
				<br><br>
				<div id="result1"></div>
			</div>
			</div>
		</div>
		</div>
	</form>
</body>
</html>