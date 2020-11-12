<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	String mem_id = request.getParameter("memId");
%>    
    
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="/ourbox/js/jquery-3.5.1.min.js"></script>
<script src="/ourbox/js/memo.js"></script>

<title>QUICK 메모 : 글쓰기</title>


<script type="text/javascript">

	$(function() {
		
			
		
		$('#insertMemo').on('click', function() {
			
			mem_id = $('#mem_id').val();
			memoTitle = $('#memoTitle').val();
			memoContent = $('#memoContent').val();
			
			
			newMemo = {
						"mem_id" : mem_id,
						"memoTitle" : memoTitle,
						"memoContent": memoContent
					}			
			
			console.log(newMemo);
			
			insertMemo(newMemo);
			
		})
		
		
		
		
	})

</script>


<style type="text/css">

	body{
		background: #E6E6E6;
		font-family:'문제부 돋움체';
		
	}
	
	input {
		width: 90%;
	}
	
	#memoTitle{
		height: 40px;
		margin: 5%;
	}
	
	#memoContent{
		height: 330px;
		margin: 0px 5%;
		width: 88%;
		padding-top: 3%;
		padding-left: 3%;
	}
	
	#insertMemo{
		width: 93%;
		height: 40px;
		margin: 5%;
	}
	

</style>

</head>
<body onresize="parent.resizeTo(300,580)" onload="parent.resizeTo(300,580)">
	
	<input type="hidden" id="mem_id" value="<%=mem_id%>">
	<input type="text" id="memoTitle" name="memoTitle" value="" placeholder="   제  목"><br>
	<textarea id="memoContent" name="memoContent" placeholder="내  용" rows="" cols=""></textarea>
	<input type="button" id="insertMemo" name="insertMemo"  value="저   장">

</body>
</html>