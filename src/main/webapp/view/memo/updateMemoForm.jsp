<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	int memo_seq = Integer.parseInt(request.getParameter("memoSeq"));
	String mem_id = request.getParameter("memId");
	String memo_title = request.getParameter("memoTitle");
	String memo_content = request.getParameter("memoContent");

	
%>    
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="/ourbox/js/jquery-3.5.1.min.js"></script>
<script src="/ourbox/js/memo.js"></script>

<title>QUICK 메모 : 글수정</title>


<script type="text/javascript">

	$(function() {
		
			
		
		$('#updateMemo').on('click', function() {
			
			mem_id = $('#mem_id').val();
			memo_seq = $('#memo_seq').val();
			memoTitle = $('#memoTitle').val();
			memoContent = $('#memoContent').val();
			
			memo = {	
						"mem_id" : mem_id,
						"memo_seq" : memo_seq,
						"memoTitle" : memoTitle,
						"memoContent": memoContent
					}			
			
			
			updateMemo(memo);
			
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
	
	#updateMemo{
		width: 93%;
		height: 40px;
		margin: 5%;
	}
	

</style>

</head>
<body>
	
	<input type="hidden" id="mem_id" value="<%=mem_id%>">
	<input type="hidden" id="memo_seq" value="<%=memo_seq%>">
	<input type="text" id="memoTitle" name="memoTitle" value="<%=memo_title %>"><br>
	<textarea id="memoContent" name="memoContent" rows="" cols=""><%= memo_content.replaceAll("<br>", "\n") %></textarea>
	<input type="button" id="updateMemo" name="updateMemo"  value="저   장">

</body>
</html>