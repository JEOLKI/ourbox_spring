<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! MemberVO mv; %>
    <% mv =  (MemberVO)session.getAttribute("vo"); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="jquery-3.5.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<script type="text/javascript">
$(function(){
	//비밀번호 불일치 시 경고
	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("input .pass").keyup(function(){
		var pwd1=$("#mem_pass1").val();
		var pwd2=$("#mem_pass2").val();
		if(pwd1 != "" || pwd2 != ""){
			if(pwd1 == pwd2){
				$("#alert-success").show();
				$("#alert-danger").hide();
				$("#sendbtn").removeAttr("disabled");
			}else{
				$("#alert-success").hide(); 
				$("#alert-danger").show(); 
				$("#sendbtn").attr("disabled", "disabled");
			} 
		}
	});
	//내용 미입력시 경고
	if($(".pass").val()==''){
		alert("비밀번호를 입력해주세요.");
		return false;
	} else if($("#mem_nickname").val()==''){
		alert("닉네임을 입력해주세요.");
		return false;
	} else if($("#mem_tel").val()==''){
		alert("전화번호를 입력해주세요.");
		return false;
	} else if($("#mem_zip").val()==''){
		alert("우편번호를 입력해주세요.");
		return false;
	} else if($("#mem_addr1").val()==''){
		alert("기본주소를 입력해주세요.");
		return false;
	} else if($("#mem_addr2").val()==''){
		alert("상세주소를 입력해주세요.");
		return false;
	}
	$('#sendbtn').on('click',function(){
		window.parent.location.href="/ourbox/view/ourbox/ourboxmember.jsp";
	})
});
</script>
<title>회원 정보 수정</title>

<style type="text/css">

	h1{
		font-weight: bold;
	}
	
	.himg{
		width: 45px;
		height: 45px;
		vertical-align: middle;
		display: inline-block;
		margin-right: 10px;
	}
	
	#box{
		margin: 5%;
		margin-left: 30%;
	}
	
	span{
		display: inline-block;
	}
	
	table {
	
	}
	
	td{
		padding-left: 10px;
		font-size: 1.2em;
	}
	
	.td1 {
		height: 50px;
		width: 150px;
		text-align: center;
		font-size: 1.2em;
		font-weight: bold;
		vertical-align: middle;
		/* background: #E6E8E8; */
	}
	
	.td2{
		width: 400px;
		padding: 5px;
	}
	
	.inpu1{
		width: 400px;
		height: 30px;
		border-radius: 7px;
		border: 1px solid #A6A6A6;
	}
	
	#sendbtn{
		width: 200px;
		height: 30px;
		margin-left: 152px;
	}
	
	.tx1{
		width: 100%;
		height: 50px;
		resize: none;
		border-radius: 7px;
		padding: 2px;
	}
	
	::-webkit-scrollbar {
   		display: none;
 }
  	
 ::-webkit-scrollbar-thumb {
    	background-color: #EAEAEA;
    	border-radius: 10px;
    	background-clip: padding-box;
    	border: 2px solid transparent;
 }
 	
 ::-webkit-scrollbar-track {
    	background-color: #EAEAEA;
    	border-radius: 10px;
    	box-shadow: inset 0px 0px 5px #EAEAEA;
   }

</style>



</head>
<body>



<div id="box">
	<h1><img class="himg" alt="eomogi_girinning.png" src="/ourbox/images/eomogi_girinning.png"><span>회원 정보 수정</span></h1>

	<br>
	
	<form action="<%=request.getContextPath()%>/MemberUpdateController" method ="post"onsubmit="return FormSubmit();">
	<input type="hidden" name = "mem_id" value = "<%=mv.getMem_id()%>">
<%--     <input type="hidden" value = "<%=mv.getMem_pass()%>"> --%>
    <input type="hidden" name = "mem_name"value = "<%=mv.getMem_name()%>">
<%--     <input type="hidden" value = "<%=mv.getMem_nickname()%>"> --%>
    <input type="hidden" name = "mem_bir"value = "<%=mv.getMem_bir()%>">
<%--     <input type="hidden" value = "<%=mv.getMem_tel()%>"> --%>
<%--     <input type="hidden" "value = "<%=mv.getMem_zip()%>"> --%>
<%--     <input type="hidden" value = "<%=mv.getMem_addr1()%>"> --%>
<%--     <input type="hidden" value = "<%=mv.getMem_addr2()%>"> --%>
<%--     <input type="hidden" name = "mem_status"value = "<%=mv.getMem_status()%>"> --%>
<%--     <input type="hidden" value = "<%=mv.getMem_profile()%>"> --%>
	
		<table>
			<tr>
				<td class='td1'>아이디 </td>
				<td class='td2'><span id = "mem_id"><%=mv.getMem_id()%></span></td>
			</tr>
			<tr>
				<td class='td1'>닉네임 </td>
				<td class='td2'><input class='inpu1' id = "mem_nickname" type ="text"name="mem_nickname" value = "<%=mv.getMem_nickname()%>"></td>
			</tr>
			<tr>
				<td class='td1'>비밀번호 </td>
				<td class='td2'><input class='inpu1' id = "mem_pass1" type ="text" name = "mem_pass1" value = "<%=mv.getMem_pass()%>"></td>
			</tr>
			<tr>
				<td class='td1'>비밀번호 확인 </td>
				<td class='td2'><input class='inpu1' id = "mem_pass2" type ="text" name="mem_pass2" value = "<%=mv.getMem_pass()%>"></td>
			</tr>
			<tr>
				<td><div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div></td>
      			<td><div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div></td>
			</tr>
			<tr>
				<td class='td1'>이름 </td>
				<td class='td2'><span id = "mem_name"><%=mv.getMem_name()%></span></td>
			</tr>
			<tr>
				<td class='td1'>생년월일 </td>
				<td class='td2'><span id = "mem_bir"><%=mv.getMem_bir() %></span></td>
			</tr>
			<tr>
				<td class='td1'>전화번호 </td>
				<td class='td2'><input class='inpu1' id = "mem_tel" type ="text"name="mem_tel" value = "<%=mv.getMem_tel()%>"></td>
			</tr>
			<tr>
				<td class='td1'>우편번호 </td>
				<td class='td2'><textarea class="tx1" id = "mem_zip" name="mem_zip"><%=mv.getMem_zip()%></textarea></td>
			</tr>
			<tr>
				<td class='td1'>기본주소 </td>
				<td class='td2'><textarea class="tx1" rows="" cols="" id="mem_addr1" name="mem_addr1"><%=mv.getMem_addr1()%></textarea></td>
			</tr>
			<tr>
				<td class='td1'>상세주소 </td>
				<td class='td2'><textarea class="tx1" rows="" cols="" id="mem_addr2" name="mem_addr2"><%=mv.getMem_addr2()%></textarea></td>
			</tr>
		</table>
		<br>
		<input id ="sendbtn" type="submit" value="회원 정보 수정">
	</form>
</div>	
	
	
</body>
</html>