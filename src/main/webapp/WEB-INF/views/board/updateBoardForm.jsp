<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/ourbox/css/public.css">
<link rel="stylesheet" href="/ourbox/css/board.css">
<script src="/ourbox/js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	$(function() {
		
		// 목록으로 버튼 누르면
		$('.backlist').on('click', function() {
			location.href="/ourbox/board/view?memId=${boardVO.mem_id}&roomSeq=${boardVO.room_seq}";
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
		<form action="/ourbox/board/update" method="post" enctype="multipart/form-data" >
			
			<table>
				<tr id='firstLine'>
					<td id='inputTd'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input class='inputTitle' id='insertTitle' name='board_title' type='text' value='${boardVO.board_title }'></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;첨부파일 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type='file' name='file'></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<textarea name='board_content' class='textareaContent' id='insertContent' rows='5' cols='150'>
							${boardVO.board_content }
						</textarea>
					</td>
				</tr>
			</table>
			
			<input class='hide' type="text" name="mem_id" value="${boardVO.mem_id }" id="memIdHidden">
			<input class='hide' type="text" name="room_seq" value="${boardVO.room_seq }" id="roomSeqHidden">
			<input class='hide' type="text" name="board_seq" value="${boardVO.board_seq }" id="boardSeqHidden">
			
			<div id='but'>
				<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="글 수정">
			</div>
			
		</form>
	</div>	
	
</body>
</html>