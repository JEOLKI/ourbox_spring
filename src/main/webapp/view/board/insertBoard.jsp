<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
	int roomSeq = (Integer) request.getAttribute("roomSeq");
	String memId = (String) request.getAttribute("memId");

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/public.css">
<script src="js/boardReply.js"></script>
<script src="js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	$(function() {
		
		$('#insertBoard').on('click', function() {
			
			memId = $(this).attr('memId');
			roomSeq = $(this).attr('roomSeq');
			boardTitle = $('#insertBoardTitle').val();
			boardContent = $('#insertBoardContent').val();
			
			console.log(boardTitle);
			console.log(boardContent);
			
			board = {
						"memId" : memId,
						"roomSeq" : roomSeq,
					 	"boardTitle" : boardTitle,
					 	"boardContent" : boardContent
					}
			
			insertBoard(board);
			
		})
		
	})
	

</script>

<style>
	tr:nth-child(1), input{
		background: #E6E8E8;
	}
	
	input{
		border: none;
	}
	
	textarea {
		border: none;
		vertical-align: top;
	}
</style>

</head>
<body>
	
	
	<table>
		<tr>
			<td><label>제목 : </label><input id="insertBoardTitle" type="text" value=""></td>
		<tr>
		<tr>
			<td><label>내용 : </label><textarea id="insertBoardContent" rows="30" cols="50"></textarea></td>
		</tr>
	</table>
	
	<input type="button" id="insertBoard" roomSeq="<%= roomSeq %>" memId="<%= memId %> "value="글 등록">
	
</body>
</html>