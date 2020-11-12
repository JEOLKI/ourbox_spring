<%@page import="ourbox.common.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    	String room_seq = (String)request.getParameter("roomSeq");
    	List<MemberVO> otherList = (List<MemberVO>)request.getAttribute("otherList");
    	String msg= "";
    	if(otherList ==null){
    		msg = "";
    	}else if(otherList.size() == 0){
    		msg= "일치하는 사용자가 존재하지 않습니다.";
    	}
    	MemberVO ol;
    	if(room_seq ==null){
    		room_seq = "";
    	}
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/ourbox/js/jquery-3.5.1.min.js"></script>
<script>
$(function(){
	
	$('#findbtn').on('click',function(){
		roomSeq = $('#roomSeq').val();
		other = $('#other').val();
		
		$.ajax({
			url:'/ourbox/MemberOtherListController',
			type:'get',
			data:{
				"roomSeq": roomSeq,
				"other" : other
			},
			success: function(res){
				code = "";
				
				$.each(res, function(i, v) {
					code += "<tr>";
					code += '<td>회원 ID : '+ v.mem_id+'/td>';
					code += '<td><input id = "sendbtn" type="submit" value="초대" ></td>';
					code += "</tr>";
				})
			
				$('#ohterList').append(code);
				
			},
			error: function(xhr){
				alert("상테" + xhr.status)
			},
			dataType:'json'
		})
		
	})
	
})

</script>
</head>
<body>

</body>
</html>