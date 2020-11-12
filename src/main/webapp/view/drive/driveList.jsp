<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ourbox.common.vo.DriveVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%		
		int room_seq = (Integer) request.getAttribute("roomSeq");
		

		

		// 드라이브 리스트
    	List<DriveVO> driveList = (List<DriveVO>) request.getAttribute("driveList");
    	
    	List<DriveVO> folderList = new ArrayList();
    	List<DriveVO> fileList = new ArrayList();
    	
    	for(int i=0; i < driveList.size(); i++){
    		
    		if(driveList.get(i).getDrive_status().equals("1")){
    			driveList.remove(i);
    		}
    	}
    	
    	for(int i=0; i<driveList.size(); i++ ){
    		
    		
    		if (driveList.get(i).getDrive_type().equals("0")) {
    			folderList.add(driveList.get(i));
    		} else {
    			fileList.add(driveList.get(i));
    		}
    	}
    	
    
    	
    	
   %>
   
   

<link rel="stylesheet" href="css/public.css">
<link rel="stylesheet" href="css/rightmouse.css">
<script src="js/drive.js"></script>
<link rel="stylesheet" href="css/drive.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/rightmouse.js"></script>



    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ourbox drive</title>
<script>

	/**
	* 쿠키값 세팅 (<p>테그 클릭시)
	*/
	function setPCookie(driveSeq,drivePath){
		drive_seq = driveSeq;
		drive_path  = drivePath;
		setPCookieDrive(drive_seq,drive_path);
	}
	
	/**
	* 쿠키값 세팅 (<div .Box>테그 클릭시)
	*/
	function setDivCookie(roomSeq,room_path){
		room_seq = roomSeq;
		room_path  = room_path;
		setDivCookieDrive(room_seq,room_path);
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
		
 		room_seq = $('#room_seqHidden').val();
		
		// 검색 버튼
		$('#searchDrive').on('click',function(){
			keyword = $('#searchKeyWord').val();
			searchDrive(keyword);
		})
 		
 		
		// 폴더 생성
		$('#createDirectory').on('click',function(){
			drive_name = $('#driveName').val();
			createFolder(drive_name,room_seq);
		})
		
		
		// 다운로드 버튼
		$('#download').on('click',function(){	
			drive_seq = getCookie("drive_seq");
			downloadDrive(drive_seq);				
		})
		
		// 삭제 버튼
		$('#deleteDrive').on('click',function(){
			drive_seq = getCookie("drive_seq");
			changeDriveStatus(drive_seq);
		})
		
		
		// 우클릭 다운로드
		$('#Rdownload').on('click',function(){	
			downloadDrive(drive_seq);	
		})

		
		// 우클릭 이름변경
		$('#Rrename').on('click',function(){
			drive_seq = getCookie("drive_seq");
			newName = prompt("변경할 이름을 입력하세요.");
			reName(drive_seq,newName);
		})
		
		
		// 우클릭 복사 
		$('#Rcopy').on('click',function(){
			drive_seq = getCookie("drive_seq");
			drive_path = getCookie("drive_path");
			drivePath = encodeURI(drive_path);
			$('#copyBox').empty();
			code = '<input type="hidden" value="'+drive_seq+'" id="copyDriveSeq">';
			code += '<input type="hidden"  value="'+drivePath+'" id="copyDrivePath">';
			$('#copyBox').append(code);
		})
		
		// 우클릭 붙여넣기
		$('#Rpaste').on('click',function(){
			room_seq = getCookie("room_seq");
			copy_path = $('#copyDrivePath').val();
			copyDrive_seq = $('#copyDriveSeq').val();
			
			pasteDrive(room_seq,copyDrive_seq);
		})
		
		// 우클릭 삭제
		$('#Rdelete').on('click',function(){
			drive_seq = getCookie("drive_seq");
			changeDriveStatus(drive_seq);
		})
		
		// 정렬버튼 클릭
// 		$('#sortDrive').on('click',function(){
// 			$('')
// 		})
		
		// 클릭시 보더주기 (드라이브, 휴지통)
		$('.fileList').on('click',function(){
			$('.fileList').css("background","none").css("border","none");
			this.style.backgroundColor = "#EBFAFF";
			this.style.border = "1px dotted #D5D5D5";
		})
		
		// 클릭시 보더주기 (검색결과)
		$(document).on('click','.sfileList', function(){
			$('.sfileList').css("background","none").css("border","none");
			this.style.backgroundColor = "#EBFAFF";
			this.style.border = "1px dotted #D5D5D5";
			
		})
	
	})
</script>
<style>
</style>
</head>
<body>
	
	<input type="hidden" value="<%=room_seq %>" id="room_seqHidden"> 
	
	<div id="driveMenu">
	
	
	<!-- 검색  -->
	<input id="searchKeyWord" type="text" >
	<button class="btn" id="searchDrive" type="button">검색</button>
	
	
	<!-- 파일 업로드 --> 
 	<form id ="form" method="post" action="DriveUploadController" enctype="multipart/form-data">
 		<input class="btn" type="hidden" value="<%=room_seq %>" name="roomSeq">
		<input id="uploadBtn" type="file" name="uploadFile"/>
		<input class="btn" id="upload" type="submit" value="UPLOAD">
	</form>
	
	
	<!-- 파일 다운로드 -->
	<button class="btn" id="download" type = "button" >DOWNLOAD</button>
	
	
	<!-- 파일 삭제 -->
	<button class="btn"  id="deleteDrive" type="button">삭제</button><br>
	
	<!-- 정렬 기준 선택 -->
<!-- 	<select id="selectBox"> -->
<!-- 		<option>파일명 오름차순</option> -->
<!-- 		<option>파일명 내림차순</option> -->
<!-- 		<option>생성일</option> -->
<!-- 		<option>용량</option> -->
<!-- 	</select> -->
<!-- 	<button id="sortDrive" type="button">정렬</button> -->


	<!-- 폴더 생성 -->
<!-- 	<input  id="driveName" type="text"> -->
<!-- 	<button class="btn" id="createDirectory" type="button">폴더생성</button> -->
	
	
	</div>
	
	<div class="driveBox" id="<%=room_seq %>"  onmousedown="setDivCookie(this.id,'<%=getServletContext().getRealPath("").replaceAll("\\\\", "//")%>')" >
<!-- 		<div class="driveBox drive" > -->
<!-- 			<h5>폴더</h5> -->
<%-- 			
 				for(int i=0; i<folderList.size(); i++){
 					System.out.println("폴더 출력 사이즈 : "+folderList.size()+"인덱스 : " + i);
<%-- 			%> --%>
<%-- 					<table class="fileList" id="<%=folderList.get(i).getDrive_seq()%>"  onmousedown="setPCookie(this.id,'<%=folderList.get(i).getDrive_path().replaceAll("\\\\", "//") %>' )" > --%>
<!-- 						<tr> -->
<!-- 							<td class='typeImg'> -->
<%-- 								<img src="/ourbox/images/<%= folderList.get(i).getDrive_type() %>.png" height="50px"> --%>
<!-- 							</td></tr> -->
<%-- 						<tr><td><%= folderList.get(i).getDrive_name() %></td> --%>
<!-- 						</tr> -->
<!-- 					</table>				 -->
<%-- 			<% 
// 				}
<%-- 			%> --%>
<!-- 		</div> -->
		<div class="driveBox file">
<!-- 			<h5>파일</h5> -->
			<%
				for(int i=0; i<fileList.size(); i++){
			%>		
					<table  class="fileList" id="<%=fileList.get(i).getDrive_seq()%>"  onmousedown="setPCookie(this.id,'<%=fileList.get(i).getDrive_path().replaceAll("\\\\", "/") %>' )"	>
						<tr class='typeImg'>
							<td  class='typeImg'><img src="/ourbox/images/<%= fileList.get(i).getDrive_type() %>.png" weight="50px" height="50px"></td>
						</tr>
						<tr class='driveName'><td class='driveName' style=" text-overflow:ellipsis; overflow:hidde">
							<%= fileList.get(i).getDrive_name() %></td></tr>
					</table>
			<%
				}
			%>
			
		</div>
	</div>

	
	
	<ul class="contextmenu">
	  <li><a id="Rcopy" href="#">복사</a></li>
	  <li><a id="Rpaste" href="#">붙여넣기</a></li>
	  <li><a id="Rdelete" href="#">삭제</a></li>
	  <li><a id="Rrename" href="#">이름변경</a></li>
	  <li><a id="Rdownload" href="#">다운로드</a></li>
	</ul>
	
	<!-- 복사할 대상 seq 저장용 -->
	<div id="copyBox"></div>
</body>
</html>