<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String board_content = (String) request.getAttribute("board_content");
	String board_title = (String) request.getAttribute("board_title");
	String mem_id = (String) request.getAttribute("mem_id");
	int room_seq = (Integer) request.getAttribute("room_seq");
	int board_seq = (Integer) request.getAttribute("board_seq");
	
	board_content= board_content.replaceAll("<br>", "\\n");

	String logId = (String) request.getAttribute("logId");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/ourbox/css/public.css">
<link rel="stylesheet" href="/ourbox/css/board.css">
<script src="js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	$(function() {
		
		mem_id = $('#logIdHidden').val()
		room_seq = $('#roomSeqHidden').val()
		
		// 목록으로 버튼 누르면
		$('.backlist').on('click', function() {
			
			location.href="BoardMainController?memId="+mem_id+"&roomSeq="+room_seq+"";
			
		})
		
	})

</script>

<style type="text/css">
	
	.hide{
		display: none;
	}
	

</style>


</head>
<body>

	<div id='box'>
		<form action="/ourbox/UpdateBoardController" method="post" enctype="multipart/form-data" >
			<table>
				<tr id='firstLine'>
					<td id='inputTd'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input class='inputTitle' id='insertTitle' name='board_title' type='text' value='<%=board_title%>'></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;첨부파일 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type='file' name='atchFile' ></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용 :&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea name='board_content' class='textareaContent' id='insertContent' rows='5' cols='150'><%=board_content%></textarea></td>
				</tr>
			</table>
			<input class='hide' type="text" name="mem_id" value="<%= mem_id %>" id="memIdHidden">
			<input class='hide' type="text" name="room_seq" value="<%= room_seq %>" id="roomSeqHidden">
			<input class='hide' type="text" name="board_seq" value="<%= board_seq %>" id="boardSeqHidden">
			<input class='hide' type="text"  value="<%= logId %>" id="logIdHidden">
			<div id='but'>
			<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="글 수정">
			</div>
		</form>
	</div>	
	
</body>
</html>