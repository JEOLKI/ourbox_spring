<%@ page import="ourbox.common.vo.AtchFileVO"%>
<%@ page import="ourbox.common.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<%
	BoardVO detailBoard = (BoardVO) request.getAttribute("detailBoard");
	AtchFileVO atchFile = (AtchFileVO) request.getAttribute("atchFile");
	String logId = (String) request.getAttribute("logId");
	
	int board_seq = detailBoard.getBoard_seq();
	int room_seq = detailBoard.getRoom_seq();
	String board_title = detailBoard.getBoard_title();
	String board_content = detailBoard.getBoard_content().replaceAll("\r", "").replaceAll("\n", "<br>");
	String mem_id = detailBoard.getMem_id();
	String board_date = detailBoard.getBoard_date();
%>      
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/ourbox/css/public.css">
<link rel="stylesheet" href="/ourbox/css/rightmouse.css">
<script src="/ourbox/js/jquery-3.5.1.min.js"></script>
<script src="/ourbox/js/rightmouse.js"></script>
<link rel="stylesheet" href="/ourbox/css/board.css">
<script type="text/javascript">

	$(function() {
		
		mem_id = $('#memIdHidden').val()
		room_seq = $('#roomSeqHidden').val()
		board_seq = $('#boardSeqHidden').val()
		board_title = $('#titleHidden').val()
		board_content = $('#contentHidden').val()
		logId = $('#logIdHidden').val()
		
		replyList(board_seq);
		
		// 목록으로 버튼 누르면
		$('.backlist').on('click', function() {
			location.href="/ourbox/board/view?memId="+logId+"&roomSeq="+room_seq+"";
		})
		
		//답변 등록버튼 클릭하면
		$('#insertReply').on('click', function() {
			
			replyContent = $('#replyContent').val();
			
			$.ajax({
				url : '/ourbox/InsertReplyController',
				data : {board_seq : board_seq,
						reply_content : replyContent,
						mem_id : logId
						},
				type : 'get',
				dataType : 'json',
				success : function(res) {
					replyList(board_seq);

				}
			})	
			
			$('#replyContent').val("");
		})
		
		// 답변의 삭제버튼을 클릭하면
		$('#boardList').on('click', '.deleteReply', function() {
			
			reply_seq = $(this).attr('seq');
			
			$.ajax({
				url : '/ourbox/DeleteReplyController',
				data : {reply_seq : reply_seq},
				type : 'get',
				dataType : 'json',
				success : function(res) {
					replyList(board_seq);
				}
			})
			
		})
		
		
		// 답변의 수정버튼을 클릭하면
		$('#boardList').on('click', '.updateReply', function() {
			
			reply_seq = $(this).attr('seq');
			
			$(this).hide();
			$('#insertUpdate'+reply_seq+'').show();
			
			content = $('#'+reply_seq+'').html().replace(/<br>/g, "\n");
			
			code = "<textarea id='newReply' rows='5' cols='30'>"+content+"</textarea>"
			
			$('#'+reply_seq+'').empty();
			$('#'+reply_seq+'').append(code);
			
		})
	
		// 답변수정후 등록버튼 클릭하면
		$('#boardList').on('click', '.insertUpdate', function() {
			
			reply_seq = $(this).attr('seq');
			
			reply_content = $('#newReply').val();
			
			reply = {
						board_seq : board_seq,
						reply_content : reply_content,
						reply_seq : reply_seq,
					}
			
			$.ajax({
				url : '/ourbox/UpdateReplyController',
				data : reply,
				type : 'get',
				dataType : 'json',
				success : function(res) {
					replyList(board_seq);
				}
			})
			
		})
		
		
		// 삭제버튼 누르면
		$('#deleteBoard').on('click', function() {

			$.ajax({
				url : '/ourbox/board/delete',
				type : 'get',
				data : {board_seq : board_seq},
				dataType : 'json',
				success : function(res) {
					
					if(res.result == 0) {
						alert("댓글이 달려있어 삭제할 수 없습니다.");
					}else {
						location.href="/ourbox/board/view?memId="+logId+"&roomSeq="+room_seq+"";
					}
					
				}
			})
		})
		
		// 글 수정 버튼을 누르면
		$('#updateBoard').on('click', function() {
			location.href="/ourbox/board/update?board_seq=" + board_seq + "";
		})
		
		
		$('#print').on('click', function() {
			$('.contextmenu').hide();
			window.print();
			$('.contextmenu').show();
		})
		
		
	})

var replyList = function(board_seq) {
	$.ajax({
		url : '/ourbox/ReplyListController',
		data : {board_seq : board_seq},
		type : 'get',
		dataType : 'json',
		success : function(data) {
			
			code = "<table class='replyTable'>"
			$.each(data.replyList, function(i, v) {
				code += "  <tr>"
				code += "    <td class='replyMem' rowspan='2'>"+v.mem_id+"</td>"
				code += "    <td class='replyDate'>"+v.reply_date+"</td>"
				code += "    <td class='butgroup2' rowspan='2'>"
						
						if( v.mem_id == logId ){
							
							code += "<button class='updateReply' seq='"+v.reply_seq+"' " +
							"				qna='"+v.board_seq+"' type='button'>수 정</button>"
							code += "<button class='insertUpdate' id='insertUpdate"+v.reply_seq+"' seq='"+v.reply_seq+"' " +
							"				qna='"+v.board_seq+"' type='button'>등 록</button><br>"
							code += "<button class='deleteReply' seq='"+v.reply_seq+"' " +
							"				qna='"+v.board_seq+"' type='button'>삭 제</button>"
						}
				
				code += "    </td>"
				code += "  </tr>"
				code += "  <tr>"
				code += "    <td class='replyContent' id='"+v.reply_seq+"' colspan='4'>"+v.reply_content+"</td>"
				code += "  </tr>"
						
			})
			
			code += "</table>"
				
			$('#replyList').empty();
			$('#replyList').append(code);
			
		}
	})
	
}

</script>

<style type="text/css">

	.insertUpdate{
		display: none;
	}


</style>


</head>
<body>
<div id='box'>
	<div id="boardList">
		<table id='contentTable'>
			<tr id='tr1'>
				<td class='tdMenu'> 제목 : </td>
				<td id='board_title' seq="${detailBoard.board_seq }" >${detailBoard.board_title }</td>
				<td class='tdMenu'> 작성자 : </td>
				<td id='writer'>${detailBoard.mem_id }</td>
				<td class='tdMenu'> 작성일자 : </td>
				<td id='date'>${detailBoard.board_date }</td>
			</tr>
			<tr>
				<td class='tdMenu content'>  내용 : </td>
				<td id='board_content' colspan='5'><%= board_content %></td>
			</tr> 
			
			<% if(atchFile.getAtch_file_seq() != 0){ %>
			<tr>
				<td class='tdMenu'>첨부파일:</td>
				<td id='atch'>
					<a href="${pageContext.request.contextPath }/board/fileDownload?atch_file_seq=<%= atchFile.getAtch_file_seq()%>">
							<%= atchFile.getAtch_file_name()%>
					</a>
				</td>
			</tr>
			<% } %>
			
			<tr id='tr2'>
				<td class='tdMenu' id="reMenu">  댓    글 : </td>
				<td id='replyList' colspan='5'></td>
			</tr>
			<tr>
				<td colspan='6'> <textarea id='replyContent' rows='5' cols='70'></textarea>
				<button id='insertReply' type='button' >등 록</button>
				</td>
			</tr>
		</table><br>
	</div>
	
		<div id="but">
			<button class='backlist' type='button' >목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;
			
			<% if(detailBoard.getMem_id().equals(logId)){ %>
				<button id='updateBoard' type='button'> 글 수정 </button>&nbsp;&nbsp;&nbsp;&nbsp;
			<% } %>
			
			<button id='deleteBoard' type='button'> 글 삭제 </button>
		</div>
		
</div>			
	<input type="hidden" value="<%= mem_id %>" id="memIdHidden">
	<input type="hidden" value="<%= room_seq %>" id="roomSeqHidden">		
	<input type="hidden" value="<%= board_seq %>" id="boardSeqHidden">		
	<input type="hidden" value="<%= board_title %>" id="titleHidden">		
	<input type="hidden" value="<%= board_content %>" id="contentHidden">		
	<input type="hidden" value="<%= logId %>" id="logIdHidden">		
			
	<ul class="contextmenu">
	  <li><a href="#" id="print">내보내기</a></li>
	</ul>		
			
			
</body>
</html>

