/**
 * 
 */



var deleteBoard = function(board_seq) {
	
	$.ajax({
		url : '/ourbox/DeleteBoardController',
		type : 'get',
		data : { "board_seq" : board_seq},
		dataType : 'json',
		success : function(res) {
			
			if(res.result == "실패") {
				alert("댓글이 달려있어 삭제할 수 없습니다.");
				
			}else {
				
				location.href="/ourbox/BoardMainController?memId="+logId+"&roomSeq="+room_seq;
				
			}
			
		},
		error : function(xhr) {
		}
		
	})
	
}


var searchBoard = function(searchElement) {
	
	$.ajax({
		url : '/ourbox/SearchBoardController',
		data : searchElement,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			code = "<table class='list'>"
			code += "  <tr id='tr1'>"
			code += "    <td id='titleTd'>제목</td>"
			code += "    <td id='writerTd'>작성자</td>"
			code += "	 <td id='dateTd'>작성일자</td>"
			code += "  </tr>"
					
			$.each(res, function(i, v) {
				code += "  <tr class='trtab'>"
				code += "    <td class='board_title' seq='"+ v.board_seq +"'>"+v.board_title+"</td>"
				code += "    <td>"+v.mem_id+"</td>"
				code += "	 <td>"+v.board_date+"</td>"
				code += "  </tr>"
					
			})
			code += "</table><br>"
			code += "<div id='but'>"	
			code += "<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += "</div>"	
				
			$('#boardList').empty();
			$('#boardList').append(code);
			
		},
		error : function(xhr) {
			alert("상태22222 : " + xhr.status)
		}
	})
}


var insertUpdate = function(reply) {
	
	$.ajax({
		url : '/ourbox/UpdateReplyController',
		data : reply,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			replyList(board_seq);
		},
		error : function(xhr) {
			alert("상태333 : " + xhr.status)
		}
	})
	
}



var deleteReply = function(reply_seq) {
	
	$.ajax({
		url : '/ourbox/DeleteReplyController',
		data : {"reply_seq" : reply_seq},
		type : 'get',
		dataType : 'json',
		success : function(res) {
			replyList(board_seq);
		},
		error : function(xhr) {
			alert("상태111 : " + xhr.status)
		}
	})
}




var insertReply = function(reply) {
	
	$.ajax({
		url : '/ourbox/InsertReplyController',
		data : reply,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			replyList(reply.board_seq);
		},
		error : function(xhr) {
			alert("상태44 : " + xhr.status)
		}
	})
	
	
}

var replyList = function(board_seq) {
	
	$.ajax({
		url : '/ourbox/ReplyListController',
		data : {"board_seq" : board_seq},
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			code = "<table class='replyTable'>"
			$.each(res, function(i, v) {
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
			
			
			
		},
		error : function(xhr) {
			alert("상태11 : " + xhr.status)
		}
	})
	
}



currentpage = 1;

var insertBoard = function(board) {
	
	$.ajax({
		url : '/ourbox/InsertBoardController',
		data : board,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			//alert(res.result);
			location.href="/ourbox/BoardMainController?memId="+board.mem_id+"&roomSeq="+board.room_seq;
		},
		error : function(xhr) {
			alert("상태22 : " + xhr.status)
		}
	})
}




	
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






