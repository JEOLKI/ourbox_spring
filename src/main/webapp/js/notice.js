/**
 * 
 */


currentpage = 1;


var updateNotice = function(notice) {
	
	$.ajax({
		url : '/ourbox/updateNotice',
		type : 'get',
		data : notice,
		dataType : 'json',
		success : function(res) {
			
		}
		
	})
}

var deleteNoitce = function(notice_seq) {
	
	$.ajax({
		url : '/ourbox/deleteNotice',
		type : 'get',
		data : { "notice_seq" : notice_seq},
		dataType : 'json',
		success : function(res) {
			
		}
		
	})
	
}


var detailNotice = function(notice_seq) {
	
	$.ajax({
		url : '/ourbox/detailNotice',
		type : 'get',
		dataType : 'json',
		data : { "notice_seq" : notice_seq },
		success : function(res) {
			
			code = "<table id='contentTable'>"
			code += "<tr id='tr1'>"
			code += "<td class='tdMenu'>제목 :</td>"
			code += "<td id='notice_title' seq="+ res.notice_seq +" >"+ res.notice_title + "</td>\n"
			code += "<td class='tdMenu'>작성자 :</td>"
			code += "<td id='writer'> "+ res.manager_id + "</td>\n"
			code += "<td class='tdMenu'> 작성일자 : </td>"
			code += "<td id='date'> "+ res.notice_date + "</td>\n"
			code += "</tr>"
			code += "<tr>"
			code += "<td class='tdMenu content'>  내용 : </td>"
			code += "<td id='notice_content' colspan='5'>" +res.notice_content+ "</td>\n"
			code += "</tr>"
			code += "</table><br>"
			code += "<div id='butgrp3'>"
			code += "<button seq="+ res.notice_seq +" id='updateNotice' type='button'> 글 수정 </button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += "<button seq="+ res.notice_seq +" id='deleteNoitce' type='button'> 글 삭제 </button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += "<button class='backlist' type='button'>목록으로</button>"
			code += "</div>"
			
			$('#noticeList').empty();
			$('#noticeList').append(code);

		},
		error : function(xhr) {
			alert("상태 : " + xhr.status);
		}
	})
	
}


var searchNotice = function(searchElement) {
	
	$.ajax({
	
		url : '/ourbox/searchNotice',
		data : searchElement,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			
			
			code = "<table class='list'>"
			code += "  <tr id='tr1'>"
			code += "    <td id='titleTd'class='bold'>제목</td>"
			code += "    <td id='writerTd'class='bold'>작성자</td>"
			code += "	 <td id='dateTd' class='bold'>작성일자</td>"
			code += "  </tr>"
				
			$.each(res, function(i,v) {
				code += "<tr>";
				code += "<td class='notice_title' seq='"+ v.notice_seq +"'>" + v.notice_title + "</td>\n";
				code += "<td>" + v.manager_id + "</td>\n";
				code += "<td>" + v.notice_date + "</td>\n";
				code += "</tr>\n";
			})
			
			code += "</table>"
				
			$('#noticeList').empty();
			$('#noticeList').append(code);
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})

}

var insertNotice = function(notice) {
	
	$.ajax({
		url : '/ourbox/insertNotice',
		type : 'get',
		data : notice,
		dataType : 'json',
		success : function(res) {
			location.href="noticeMain.html";
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
		
	})
	
}

var noticePageList = function(cpage) {
	
	$.ajax({
		url : '/ourbox/noticePageList',
		type : 'get',
		data : { "page" : cpage },
		dataType : 'json',
		success : function(res) {
			
			$('#noticeList').empty();
			
			code = "<table class='list'>"
			code += "  <tr id='tr1'>"
			code += "    <td id='titleTd'class='bold'>제목</td>"
			code += "    <td id='writerTd'class='bold'>작성자</td>"
			code += "	 <td id='dateTd' class='bold'>작성일자</td>"
			code += "  </tr>"
				
			$.each(res.data, function(i, v) {
				code += "  <tr class='trtab'>"
				code += "    <td class='notice_title' seq='"+ v.notice_seq +"'>"+v.notice_title+"</td>"
				code += "    <td>"+v.manager_id+"</td>"
				code += "	 <td>"+v.notice_date+"</td>"
				code += "  </tr>"
				
			})
			code += "</table>"
				
			$('#noticeList').append(code);
			
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



/*var noticeList = function() {
	
	$.ajax({
		url : '/ourbox/noticeList',
		type : 'get',
		dataType : 'json',
		success : function(res) {
			code = "<table>"
			code += "  <tr>"
			code += "    <td>제목</td>"
			code += "    <td>작성자</td>"
			code += "	 <td>작성일자</td>"
			code += "  </tr>"
				
			$.each(res, function(i, v) {
				code += "  <tr>"
				code += "    <td class='notice_title' seq='"+ v.notice_seq +"'>"+v.notice_title+"</td>"
				code += "    <td>"+v.manager_id+"</td>"
				code += "	 <td>"+v.notice_date+"</td>"
				code += "  </tr>"
				
			})
			code += "</table>"
				
			$('#noticeList').append(code);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
		
	})	
	
}*/