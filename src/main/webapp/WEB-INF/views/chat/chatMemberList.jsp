<%@page import="ourbox.common.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<MemberVO> memprofilelist = (List<MemberVO>)request.getAttribute("list"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<title>Insert title here</title>

<style type="text/css">
	
	img{
	
		margin: 2%;
		margin-right: 0px;
		border: 1px solid #F6F6F6;
	
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

<div>
<%
	for(int i = 0; i<memprofilelist.size(); i++){
%>
	<img class="img-circle" alt="프로필사진" width="50" height="50" src="/ourbox/images/<%= memprofilelist.get(i).getMem_profile() %>">
  <%
	}
%>
</div>


</body>
</html>