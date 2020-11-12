/**
 * 
 */
currentpage = 1;


var updateQna = function(qna) {
	
	$.ajax({
		url : '/ourbox/UpdateQnaController',
		type : 'get',
		data : qna,
		dataType : 'json',
		success : function(res) {
			answerList(qna_seq);
			$('#qnaList').empty();
			detailMyQna(qna_seq);
		}
		
	})
}



var deleteQna = function(qna_seq) {
	
	$.ajax({
		url : '/ourbox/DeleteQnaController',
		type : 'get',
		data : { "qna_seq" : qna_seq},
		dataType : 'json',
		success : function(res) {
			
			if(res.result == "실패") {
				alert("답변이 달려있어 삭제할 수 없습니다.");
				
			}else {
				
				location.href="/ourbox/MemberQnaPageListController?memId="+mem_id;
				
			}
			
		},
		error : function(xhr) {
		}
		
	})
	
}




var searchMyQna = function(searchElement) {
	
	$.ajax({
		url : '/ourbox/searchQna',
		data : searchElement,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			code = "<table class='list'>"
			code += "  <tr id='tr1'>"
			code += "    <td id='titleTd'class='bold'>제목</td>"
			code += "    <td id='writerTd'class='bold'>작성자</td>"
			code += "	 <td  id='dateTd' class='bold'>작성일자</td>"
			code += "  </tr>"
					
			$.each(res, function(i, v) {
				code += "  <tr class='trtab'>"
				code += "    <td class='qna_title' seq='"+ v.qna_seq +"'>"+v.qna_title+"</td>"
				code += "    <td>"+v.mem_id+"</td>"
				code += "	 <td>"+v.qna_date+"</td>"
				code += "  </tr>"
					
			})
			code += "</table><br>"
			code += "<div id='butgrp3'>"
			code += "<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += "</div>"
				
			$('#qnaList').empty();
			$('#qnaList').append(code);
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
	
	
	
	
}


var insertQna = function(qna) {
	
	$.ajax({
		url : '/ourbox/InsertQnaController',
		data : qna,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			//alert(res.result);
			location.href="/ourbox/MemberQnaPageListController?memId="+qna.mem_id;
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
	
	
	
}



var memberQnaPageList = function(cpage) {
	
	$.ajax({
		url : '/ourbox/MemberQnaPageListController',
		type : 'get',
		data : { "page" : cpage },
		dataType : 'json',
		success : function(res) {
			
			$('#qnaList').empty();
			
			code = "<table class='list'>"
			code += "  <tr id='tr1'>"
			code += "    <td id='titleTd'class='bold'>제목</td>"
			code += "    <td id='writerTd'class='bold'>작성자</td>"
			code += "	 <td  id='dateTd' class='bold'>작성일자</td>"
			code += "  </tr>"
					
			$.each(res.data, function(i, v) {
				code += "  <tr class='trtab'>"
				code += "    <td class='qna_title' seq='"+ v.qna_seq +"'>"+v.qna_title+"</td>"
				code += "    <td>"+v.mem_id+"</td>"
				code += "	 <td>"+v.qna_date+"</td>"
				code += "  </tr>"
					
			})
			
			code += "</table>"
				
			$('#qnaList').append(code);
			
			totalpage = res.tpage;
			startpage = res.spage;
			endpage = res.epage;
			currentpage = res.cpage;
			
			//이전 버튼 출력
			$('#btngroup1').empty();
			
			if ( startpage > 1 ) {
				pager = '  <span class="previous"><a href="#">Previous</a></span>&nbsp;&nbsp;&nbsp;&nbsp;';
				$(pager).appendTo('#btngroup1');
			}
			
			
			//페이지 번호 출력
			pager = '';
			for (i = startpage ; i <= endpage; i++) {
				
				if (currentpage == i) {
					pager += '<a class="paging" href="#">'+i+'</a>&nbsp;&nbsp;';
				}else {
					pager += '<a href="#" class="paging">'+i+'</a>&nbsp;&nbsp;';
				}
			}
			
			$(pager).appendTo('#btngroup1');	
			
			//다음버튼 출력
			if(endpage < totalpage) {
				pager = '  <span class="next" ><a href="#">Next</a></span>';
				$(pager).appendTo('#btngroup1');	
			}
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
		
	})		
	
	
	
}


var insertUpdate = function(answer) {
	
	$.ajax({
		url : '/ourbox/updateAnswerController',
		data : answer,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			answerList(qna_seq);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
	
}


var deleteAnswer = function(ans_seq) {
	
	$.ajax({
		url : '/ourbox/deleteAnswerController',
		data : {"ans_seq" : ans_seq},
		type : 'get',
		dataType : 'json',
		success : function(res) {
			answerList(qna_seq);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
}




var insertAnswer = function(answer) {
	
	$.ajax({
		url : '/ourbox/insertAnswerController',
		data : answer,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			answerList(answer.qna_seq);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
	
	
}



var answerList = function(qna_seq) {
	
	$.ajax({
		url : '/ourbox/answerList',
		data : {"qna_seq" : qna_seq},
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			code = "<table class='answerTable'>"
			
			$.each(res, function(i, v) {
			
				code += "  <tr>"
				code += "    <td class='answerMana' rowspan='2'>"+v.manager_id+"</td>"
				code += "    <td class='answerDate'>"+v.ans_date+"</td>"
				code += "    <td class='butgroup2' rowspan='2'>"
				code += "		<button class='updateAnswer' seq='"+v.ans_seq+"' " +
					"				qna='"+v.qna_seq+"' type='button'>수 정</button>"
				code += "		<button class='insertUpdate' id='insertUpdate"+v.ans_seq+"' seq='"+v.ans_seq+"' " +
					"				qna='"+v.qna_seq+"' type='button'>등 록</button><br>"
				code += "		<button class='deleteAnswer' seq='"+v.ans_seq+"' " +
					"				qna='"+v.qna_seq+"' type='button'>삭 제</button>"
				code += "    </td>"
				code += "  </tr>"
				code += "  <tr>"
				code += "    <td class='answerContent' id='"+v.ans_seq+"'>"+v.ans_content+"<br><br></td>"
				code += "  </tr>"
						
			})
			
			code += "</table>"
				
			$('#answerList').empty();
			$('#answerList').append(code);
			
			
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
	
	
}



var searchQna = function(searchElement) {
	
	$.ajax({
		url : '/ourbox/searchQna',
		data : searchElement,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			code = "<table class='list'>"
				code += "  <tr id='tr1'>"
				code += "    <td id='titleTd'class='bold'>제목</td>"
				code += "    <td id='writerTd'class='bold'>작성자</td>"
				code += "	 <td  id='dateTd' class='bold'>작성일자</td>"
				code += "  </tr>"
					
			$.each(res, function(i, v) {
				code += "  <tr class='trtab'>"
				code += "    <td class='qna_title' seq='"+ v.qna_seq +"'>"+v.qna_title+"</td>"
				code += "    <td>"+v.mem_id+"</td>"
				code += "	 <td>"+v.qna_date+"</td>"
				code += "  </tr>"
					
			})
			code += "</table><br>"
			code += "<div id='butgrp3'>"	
			code += "<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += "<br>"	
				
			$('#qnaList').empty();
			$('#qnaList').append(code);
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
	
	
}

var detailMyQna = function(qna_seq) {
	
	$.ajax({
		url : '/ourbox/detailQna',
		type : 'get',
		dataType : 'json',
		data : { "qna_seq" : qna_seq },
		success : function(res) {
			
			code = "<table id='contentTable'>"
			code += "<tr id='tr1'>"
			code += "<td class='tdMenu'> 제목 : </td>"
			code += "<td id='qna_title' seq="+ res.qna_seq +" >"+ res.qna_title + "</td>\n"
			code += "<td class='tdMenu'> 작성자 : </td>"
			code += "<td id='writer'> "+ res.mem_id + "</td>\n"
			code += "<td class='tdMenu'> 작성일자 : </td>"
			code += "<td id='date'> "+ res.qna_date + "</td>\n"
			code += "</tr>"
			code += "<tr>"
			code += "<td class='tdMenu content'>  내용 : </td>"
			code += "<td id='qna_content' colspan='5'>" +res.qna_content+ "</td>\n"
			code += "</tr>"
			code += "<tr id='tr2'>"
			code += "<td class='content'>  답변 : </td>"
			code += "<td id='answerList' colspan='5'></td>\n"	
			code += "</tr>"
			code += "</table><br>"
			code += "<div id='but'>"
			code += "<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += "<button seq="+ res.qna_seq +" id='updateQna' type='button'> 글 수정 </button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += "<button seq="+ res.qna_seq +" id='deleteQna' type='button'> 글 삭제 </button>"
			code += "</div>"
			
			$('#qnaList').empty();
			$('#qnaList').append(code);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status);
		}
	})
	
	answerList(qna_seq);

}


var detailQna = function(qna_seq) {
	
	$.ajax({
		url : '/ourbox/detailQna',
		type : 'get',
		dataType : 'json',
		data : { "qna_seq" : qna_seq },
		success : function(res) {
			
			code = "<table id='contentTable'>"
			code += "<tr id='tr1'>"
			code += "<td class='tdMenu'> 제목 : </td>"
			code += "<td id='qna_title' seq="+ res.qna_seq +" >"+ res.qna_title + "</td>\n"
			code += "<td class='tdMenu'> 작성자 : </td>"
			code += "<td id='writer'> "+ res.mem_id + "</td>\n"
			code += "<td class='tdMenu'> 작성일자 : </td>"
			code += "<td id='date'> "+ res.qna_date + "</td>\n"
			code += "</tr>"
			code += "<tr>"
			code += "<td class='tdMenu content'>  내용 : </td>"
			code += "<td id='qna_content' colspan='5'>" +res.qna_content+ "</td>\n"
			code += "</tr>"
			code += "<tr id='tr2'>"
			code += "<td class='content'>  답변 : </td>"
			code += "<td id='answerList' colspan='5'></td>\n"	
			code += "</tr>"
			code += "<tr>"
			code += "<td colspan='6'> <textarea id='answerContent' rows='5' cols='70'></textarea>"
			code += "&nbsp;&nbsp;&nbsp;<button id='insertAnswer' type='button' seq="+ res.qna_seq +" >등 록</button>"
			code += "</td>"
			code += "</tr>"
			code += "</table><br>"
			code += "<div id='butgrp3'>"
			code += "<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += "</div>"
				
				
			$('#qnaList').empty();
			$('#qnaList').append(code);
			
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status);
		}
	})
	
	answerList(qna_seq);
}




var ManagerQnaPageList = function(cpage) {
	
	$.ajax({
		url : '/ourbox/ManagerQnaPageList',
		type : 'get',
		data : { "page" : cpage },
		dataType : 'json',
		success : function(res) {
			
			$('#qnaList').empty();
			
			code = "<table class='list'>"
			code += "  <tr id='tr1'>"
			code += "    <td id='titleTd'class='bold'>제목</td>"
			code += "    <td id='writerTd'class='bold'>작성자</td>"
			code += "	 <td  id='dateTd' class='bold'>작성일자</td>"
			code += "  </tr>"
				
			$.each(res.data, function(i, v) {
				code += "  <tr class='trtab'>"
				code += "    <td class='qna_title' seq='"+ v.qna_seq +"'>"+v.qna_title+"</td>"
				code += "    <td>"+v.mem_id+"</td>"
				code += "	 <td>"+v.qna_date+"</td>"
				code += "  </tr>"
				
			})
			code += "</table>"
				
			$('#qnaList').append(code);
			
			totalpage = res.tpage;
			startpage = res.spage;
			endpage = res.epage;
			currentpage = res.cpage;
			
			//이전 버튼 출력
			$('#btngroup1').empty();
			
			if ( startpage > 1 ) {
				pager = '  <span class="previous"><a href="#">Previous</a></span>&nbsp;&nbsp;&nbsp;&nbsp;';
				$(pager).appendTo('#btngroup1');
			}
			
			
			//페이지 번호 출력
			pager = '';
			for (i = startpage ; i <= endpage; i++) {
				
				if (currentpage == i) {
					pager += '<a class="paging" href="#">'+i+'</a>&nbsp;&nbsp;';
				}else {
					pager += '<a href="#" class="paging">'+i+'</a>&nbsp;&nbsp;';
				}
			}
			
			$(pager).appendTo('#btngroup1');	
			
			//다음버튼 출력
			if(endpage < totalpage) {
				pager = '  <span class="next" ><a href="#">Next</a></span>';
				$(pager).appendTo('#btngroup1');	
			}
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
		
	})	
	
}