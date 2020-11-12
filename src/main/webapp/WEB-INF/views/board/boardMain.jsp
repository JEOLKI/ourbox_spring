<%@page import="ourbox.common.vo.AtchFileVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/ourbox/css/public.css">
<link rel="stylesheet" href="/ourbox/css/rightmouse.css">
<link rel="stylesheet" href="/ourbox/css/board.css">
<script src="/ourbox/js/jquery-3.5.1.min.js"></script>
<script src="/ourbox/js/boardReply.js"></script>
<script src="/ourbox/js/rightmouse.js"></script>

<script type="text/javascript">

	$(function() {
		
		mem_id = $('#memIdHidden').val()
		room_seq = $('#roomSeqHidden').val()
		
		boardPageList(1)
		
		// 페이지 번호 클릭 하면 이벤트 설정
		$('#btngroup1').on('click','.paging', function() {
			currentpage = $(this).text().trim();
			boardPageList(currentpage);
		})
		
		// 이전버튼 클릭하면
		$('#btngroup1').on('click','.previous a', function() {
			currentpage = parseInt($('.paging:first').text().trim())-1;
			boardPageList(currentpage);
		})
		
		// 다음버튼 클릭하면
		$('#btngroup1').on('click','.next a', function() {
			currentpage = parseInt($('.paging:last').text().trim()) + 1;
			boardPageList(currentpage);
		})
	
		
		// 게시글 제목을 누르면
		$('#boardList').on('click','.board_title',function() {
			board_seq = $(this).attr('seq');
			log_id =  $('#memIdHidden').val();
			
			console.log(log_id);
			
			location.href="/ourbox/board/Detail?board_seq="+board_seq+"&mem_id=${param.memId}";
		})
		
		// 검색버튼 누르면
		$('#searchBoard').on('click', function(){
			searchOption = $('#selectBox option:selected').val();
			searchKeyWord = $('#searchKeyWord').val();
			searchElement = { 
								"searchOption" : searchOption,
							 	"searchKeyWord" : searchKeyWord
							}
			searchBoard(searchElement);
			$('#btngroup1').hide();
		})
		
		// 목록으로 버튼 누르면
		$('#boardList').on('click', '.backlist', function() {
			
			location.href="/board/view?memId="+mem_id+"&roomSeq="+room_seq;
			
			$('#btngroup1').show();
			
		})
	})

var boardPageList = function(cpage) {
	$.ajax({
		url : '/ourbox/board/list',
		type : 'get',
		data : { "page" : cpage, 
				"room_seq" : room_seq},
		dataType : 'json',
		success : function(res) {
			
			$('#boardList').empty();
			
			code = "<table class='list'>"
			code += "  <tr id='tr1'>"
			code += "    <td id='titleTd'>제목</td>"
			code += "    <td id='writerTd'>작성자</td>"
			code += "	 <td id='dateTd'>작성일자</td>"
			code += "  </tr>"
				
			$.each(res.boardList, function(i, v) {
				code += "  <tr class='trtab'>"
				code += "    <td class='board_title' seq='"+ v.board_seq +"'>"+v.board_title+"</td>"
				code += "    <td>"+v.mem_id+"</td>"
				code += "	 <td>"+v.board_date+"</td>"
				code += "  </tr>"
				
			})
			code += "</table>"
				
			$('#boardList').append(code);
			
			totalpage = res.tpage;
			startpage = res.spage;
			endpage = res.epage;
			currentpage = res.cpage;
			
			//이전 버튼 출력
			$('#btngroup1').empty();
			
			pager = "";
			if ( startpage > 1 ) {
				pager += '  <span class="previous"><a href="#">Previous</a></span>&nbsp;&nbsp;&nbsp;&nbsp;';
			}
			
			//페이지 번호 출력
			for (i = startpage ; i <= endpage; i++) {
				
				if (currentpage == i) {
					pager += '<a class="paging" href="#">'+i+'</a>&nbsp;&nbsp;';
				}else {
					pager += '<a href="#" class="paging">'+i+'</a>&nbsp;&nbsp;';
				}
			}
			
			//다음버튼 출력
			if(endpage < totalpage) {
				pager += '  <span class="next" ><a href="#">Next</a></span>';
			}
			
			$(pager).appendTo('#btngroup1');
			
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

	<input type="hidden" value="${param.memId}" id="memIdHidden">
	<input type="hidden" value="${param.roomSeq}" id="roomSeqHidden">
	
	<div id="box">
		<div id="menuBox">
		<select id="selectBox">
			<option>글제목만</option>
			<option>글내용만</option>
			<option>글제목 + 내용</option>
			<option>작성자</option>
		</select>
		
		<input id="searchKeyWord" type="text" value="">
		<button id="searchBoard" type="button">검색</button>
		<button id="insertBoard" type="button" onclick = "location.href='/ourbox/board/insert?mem_id=${param.memId}&room_seq=${param.roomSeq}'">글 쓰기</button>
		
		</div>
		
		<br><br>
		
		<div id="boardList">
		
		</div>
		
		<br>
		
		<div id="btngroup1"></div>
	</div>

</body>
</html>