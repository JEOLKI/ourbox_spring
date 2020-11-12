<%@page import="ourbox.common.vo.QnaVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<QnaVO> qnaList = (List<QnaVO>) request.getAttribute("myQnaList");
	String mem_id = (String) request.getAttribute("mem_id");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/public.css">
<link rel="stylesheet" href="css/qna.css">
<script src="js/qnaAnswer.js"></script>
<script src="js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	$(function() {
		
		mem_id = '<%= mem_id %>'
		
		// 글쓰기 버튼을 클릭하면
		$('#insertQna').on('click', function() {
			
			$('#qnaList').empty();
			
			code = "<table>"
			code += "	<tr id='firstLine'>"
			code += "		<td id='inputTd'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			code += "		<input class='inputTitle' id='insertTitle' type='text' value=''></td>";
			code += "	</tr>";
			code += "	<tr>"
			code += "		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용 :&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea class='textareaContent' id='insertContent' rows='5' cols='150'></textarea></td>";
			code += "	</tr>";
			code += "</table>"
			code += "<br>"
			code += "<div id='but'>"
			code += "<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += '<input type="button" id="insert" value="글 등록">'		
			code += "</div>"
		
			$('#qnaList').append(code);
		
		})
		
		// 글 등록버튼 클릭하면
		$('#qnaList').on('click','#insert', function() {
			
			insertTitle = $('#insertTitle').val();
			insertContent = $('#insertContent').val();
			
			
			qna = {
						"insertTitle" : insertTitle,
						"insertContent" : insertContent,
						"mem_id" : mem_id
					 }
			
			insertQna(qna);
			
			
						
		})
		
		// 검색버튼 클릭하면
		$('#searchQna').on('click', function(){
			
			searchOption = $('#selectBox option:selected').val();
			searchKeyWord = $('#searchKeyWord').val();
			
			searchElement = { 
								"searchOption" : searchOption,
							 	"searchKeyWord" : searchKeyWord,
							 	"mem_id" : mem_id
							}
			
			searchMyQna(searchElement);
		})
		
		
		// 제목을 클릭하면 
		$('#qnaList').on('click','.qna_title',function() {
			
			qna_seq = $(this).attr('seq');
			
			detailMyQna(qna_seq);
			
			$('#menuBox').hide();
			
		})
		
		
		// 삭제버튼 누르면
		$('#qnaList').on('click', '#deleteQna', function() {
		
			qna_seq = $(this).attr('seq');
			
			deleteQna(qna_seq);
			
		})
		
		
		// 목록으로 버튼 누르면
		$('#qnaList').on('click', '.backlist', function() {
			
			location.href="/ourbox/MemberQnaPageListController?memId="+mem_id;
			
			$('#menuBox').show();
		})
		
		
		// 글 수정 버튼을 누르면
		$('#qnaList').on('click', '#updateQna', function() {
			
			qna_seq = $(this).attr('seq');
			qna_title = $(this).parents('#qnaList').find('#qna_title').text();
			qna_content = $(this).parents('#qnaList').find('#qna_content').html();
			
			qna_content = qna_content.replace(/<br>/g, "\n");
			
			$('#qnaList').empty();
			
			code = "<table>"
			code += "	<tr id='firstLine'>"
			code += "		<td id='inputTd'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			code += "		<input class='inputTitle' id='updateTitle' type='text' value='"+qna_title+"'></td>";
			code += "	</tr>";
			code += "	<tr>"
			code += "		<td id='conTd'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용 :&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			code += "		<textarea class='textareaContent' id='updateContent' rows='5' cols='150'>"+qna_content+"</textarea></td>";
			code += "	</tr>";
			code += "</table>"
			code += "<br>"
			code += "<div id='but'>"
			code += "<button class='backlist' type='button'>목록으로</button>&nbsp;&nbsp;&nbsp;&nbsp;"
			code += '<input type="button" seq='+qna_seq+' id="update" value="글 수정">'			
			code += "</div>"
			
		
			$('#qnaList').append(code);
			
		})
		
		
		// 수정 등록버튼을 누르면
		$('#qnaList').on('click', '#update', function() {
			
			qna_seq = $(this).attr('seq');
			updateTitle = $('#updateTitle').val();
			updateContent = $('#updateContent').val();
			
			qna = {
						"qna_seq" : qna_seq,
						"updateTitle" : updateTitle,
						"updateContent" : updateContent
					 }
			
			updateQna(qna);
			
			
			
		})
		
		
	})

</script>

<style type="text/css">
	

	.butgroup2{
		display: none;
	}
	
	

</style>

</head>
<body>

<div id='box'>

	<h1><img alt="관리자 Qna아이콘.png" src="/ourbox/images/관리자 Qna아이콘.png">QnA 게시판</h1>
	
	<div id="menuBox">
	<select id="selectBox">
		<option>글제목만</option>
		<option>글내용만</option>
		<option>글제목 + 내용</option>
	</select>
	
	<input id="searchKeyWord" type="text" value="">
	<button id="searchQna" type="button">검색</button>
	<button id="insertQna" type="button">글 쓰기</button>
	
	</div>
	
	<br><br><br>
	
	<div id="qnaList">
	
		<table class='list'>
			<tr id='tr1'>
			<td id='titleTd'class='bold'>제목</td>
			<td id='writerTd'class='bold'>작성자</td>
			<td  id='dateTd' class='bold'>작성일자</td>
			</tr>
<%
			if(qnaList.size() >0){
				for(int i=0 ; i < qnaList.size(); i++){
%>
				<tr class='trtab'>
					<td class='qna_title' seq='<%= qnaList.get(i).getQna_seq() %>'> <%= qnaList.get(i).getQna_title() %> </td>
			 		<td> <%= mem_id %> </td>
					<td> <%= qnaList.get(i).getQna_date() %></td>
				</tr>
<%
				}
			} else {
%>
				<tr>
				<td colspan="4">등록된 QnA가 없습니다.</td>
				</tr>
<%
			}
%>
		</table>
	</div>
</div>

</body>
</html>