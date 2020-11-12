<%@page import="ourbox.common.vo.DriveVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	String mem_id = (String) request.getAttribute("mem_id");
	List<DriveVO> garbageList = (List<DriveVO>) request.getAttribute("garbageList");
	 	
%> 


<link rel="stylesheet" href="css/public.css">
<link rel="stylesheet" href="css/rightmouse.css">
<script src="js/drive.js"></script>
<!-- <script src="js/rightmouse.js"></script> -->
<script src="js/jquery-3.5.1.min.js"></script>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>휴지통</title>
<style>
	#title{
		width: 30px;
		height: 30px;
		margin-right: 10px;
		display: inline; 
		position: relative;
		top : 3px;
	}
	h1{
		display: inline; 
	}
	.garbageBox{
		margin : 5%;
	}
	.garbage{
		padding : 10px;
		margin : 10px 5px;
	}
	button{
		float : right;
		margin : 5px;
	}
	table{
		display: inline-block;
		align-content : center;
		text-overflow: ellipsis;
		margin : 15px;
		margin-bottom : 30px;
		text-align: center;
		table-layout: fixed;
	}
	.typeImg{
		display : inline;
		width: 30px;
		height: 30px;
	}
	.btn{
		display : inline;
		height: 23px;
		vertical-align: top;
	}
	.driveName{
		overflow:hidden;
		white-space : nowrap;
		text-overflow: ellipsis;
/* 		font-size : 0.9em; */
		max-width: 70px;
		max-height : 50px;
		vertical-align: top;
	}
	.typeImg{
		width: 70px;
		height: 70px;
		display : inline;
	}
</style>
<script>
	/**
	* 쿠키값 세팅 (<p>테그 클릭시)
	*/
	function setPCookie(driveSeq,drivePath){
		drive_seq = driveSeq;
		drive_path  = drivePath;
		console.log(drive_seq);
		console.log(drive_path);
		setPCookieDrive(drive_seq,drive_path);
	}
	
	
	/**
	  * 쿠키값 추출
	  * @param cookieName 쿠키명
	  */
	function getCookie( cookieName ) {
	var search = cookieName + "=";
	var cookie = document.cookie;

		if( cookie.length > 0 ) {	 // 현재 쿠키가 존재할 경우
		   	startIndex = cookie.indexOf( cookieName );	// 해당 쿠키명이 존재하는지 검색한 후 존재하면 위치를 리턴.
		  	if( startIndex != -1 ) {		// 만약 존재한다면
		   		startIndex += cookieName.length;	// 값을 얻어내기 위해 시작 인덱스 조절
	    		endIndex = cookie.indexOf( ";", startIndex );	// 값을 얻어내기 위해 종료 인덱스 추출
		  		if( endIndex == -1) endIndex = cookie.length;	// 만약 종료 인덱스를 못찾게 되면 쿠키 전체길이로 설정
	   		 	return unescape( cookie.substring( startIndex + 1, endIndex ) );	// 쿠키값을 추출하여 리턴
	    	} else  {	// 쿠키 내에 해당 쿠키가 존재하지 않을 경우
		   		return false;
		   	}	  
		} else  {	// 쿠키 자체가 없을 경우
			return false;
		}
	}





	$(function(){
		
		mem_id = $('#mem_idHidden').val();
		
		$('#deleteGarbages').on('click',function(){
// 			mem_id = mem_id;
			$.ajax({
				url : '/ourbox/DeletegarbagesController',
				data : {"mem_id" : mem_id}, 
				type : 'get',
				datatype : 'json',
				success : function(res){
					location.href="/ourbox/GargabegeListController?memId="+mem_id+"";	// 멤버 아이디 넣어서 열어야됨
				},
				error : function(xhr){
					//alert("상태 : " + xhr.status);
					location.href="/ourbox/GargabegeListController?memId="+mem_id+"";	// 멤버 아이디 넣어서 열어야됨
				}
			})
		})
		
		
		$('#recycleGarbage').on('click',function(){
			drive_seq = getCookie("drive_seq");
// 			mem_id = mem_id;
			$.ajax({
				url : '/ourbox/RecycleGarbagesController',
				type : 'get',
				data : { "drive_seq" : drive_seq},
				datatype : 'json',
				success : function(res){
					location.href="/ourbox/GargabegeListController?memId="+mem_id+"";	// 멤버 아이디 넣어서 열어야됨
				},
				error : function(xhr){
					location.href="/ourbox/GargabegeListController?memId="+mem_id+"";	// 멤버 아이디 넣어서 열어야됨
				}
			})
		})
		
		// 클릭시 보더주기
		$('.fileList').on('click',function(){
         $('.fileList').css("background","none").css("border","none");
         this.style.backgroundColor = "#EBFAFF";
         this.style.border = "1px dotted #D5D5D5";
      })
		
		
	})
</script>
</head>
<body>
	<input type="hidden" value="<%=mem_id %>" id="mem_idHidden">
<div class="garbageBox">
	<img id="title" src="/ourbox/images/trash.png">
	<h1>휴지통</h1>
	<button class="btn" id="recycleGarbage" type="button">복원</button>
	<button class="btn" id="deleteGarbages" type="button">휴지통 비우기</button>
	<br>
	<div class="garbage">
	<%
		for(int i=0; i<garbageList.size(); i++){
	%>
		<table class="fileList" id="<%=garbageList.get(i).getDrive_seq()%>"  onmousedown="setPCookie(this.id,'<%=garbageList.get(i).getDrive_path().replaceAll("\\\\", "//") %>' )" >
						<tr>
							<td class='typeImg'>
								<img class='typeImg' src="/ourbox/images/<%= garbageList.get(i).getDrive_type() %>.png" >
							</td></tr>
						<tr class="driveName"><td class="driveName"><%= garbageList.get(i).getDrive_name() %></td>
						</tr>
					</table>				
			<%
				}
			%>
	</div> 
	
</div>
</body>
</html>