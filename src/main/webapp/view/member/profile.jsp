<%@page import="ourbox.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	String mem_id = request.getParameter("memId");

%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="jquery-3.5.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<script type="text/javascript">

	function setThumbnail(event) {
		
	   var reader = new FileReader();
	   
	   reader.onload = function(event) {
	      var img = document.createElement('img');
	      img.setAttribute('src', event.target.result);
	      document.querySelector('div#image_container').appendChild(img);
	   };
	   
	   reader.readAsDataURL(event.target.files[0]);
	   

	   
	}
	
	$(function() {
	
		$('#close').on('click', function() {
			
			window.close();
			window.opener.location.reload();
			
		})
		
		$('.btn-file').on('click', function() {
			
			$('#image_container').empty();
			
		})
		
		
		$('#sendbtn').on('click', function() {
			
/* 			window.opener.location.reload();
			window.open("about:black","_self").close(); */
			
		})
		
	})



</script>
<style type="text/css">
	
body{
		background: #E6E6E6;
		font-family:'문제부 돋움체';
		}


img{
	width: 180px;
	height: 180px;
}
.btn-file{
	position: relative;
	overflow: hidden;
	width: 100%;
	
}
.btn-file input[type=file]{
	position: absolute;
	top: 0;
	right: 0;
	font-size: 100px;
	text-align: right;
	filter : alpha(opacity=0);
	opacity: 0;
	background: white;
	cursor: inherit;
	display: block;
}
#ff{

}

td{
	width: 80%;
	height: 40px;
}

table{
	width: 90%;
	margin: 5%;
}

#pimg{
	width: 30px;
	height: 30px;
	margin-right: 10px;
	vertical-align: middle;
	border: none;
}

span{
	font-weight: bold;
	font-size: 1.4em;
	vertical-align: middle; 
}

img{
	border: 1px solid #9E9D9D;
}

#sendbtn{
	width: 100%;
	background: white;
	border: 1px solid #B2B1B1;
	border-radius: 5px;
	font-weight: bold;
}

.modal-title{
	font-weight: bold;
	font-size: 0.95em;
}

#image_container{
	width: 100%;
	height: auto;
	padding: 10px;
	text-align: center;
}

#simg{
	width: 27px;
	height: 27px;
	margin-right: 10px;
	vertical-align: middle;
	border: none;

}

</style>
<title>ourbox : 프로필사진 수정</title>
</head>
<body onresize="parent.resizeTo(330,420)" onload="parent.resizeTo(330,420)">


	<form id="ff" action="<%=request.getContextPath()%>/MemberProfile?mem_id=<%=mem_id%>" method ="post" enctype="multipart/form-data">
	
		<table>
			<tr>
				<td><img id="pimg" alt="emoji_wink.png" src="/ourbox/images/emoji_wink.png"><span>프로필 사진 수정<span></td>
			</tr>
			<tr>
				<td>
					<span class="btn btn-default btn-file">
					이미지를 업로드 하세요<input type="file" id="image" name="userProfile" accept="image/*" onchange="setThumbnail(event);">
					</span>
   					<div id="image_container"></div>
				</td>
			</tr>
			<tr>
				<td>
					<button id="sendbtn" type="submit" data-toggle="modal" data-target="#myModal" >사진 등록</button>
				</td>
			</tr>
		</table>
			
			
			
			<!-- Modal -->
			<div id="myModal" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title"><img id="simg" alt="emoji_sunglasses.png" src="/ourbox/images/emoji_sunglasses.png">프로필 사진이 수정 되었습니다.</h4>
			      </div>
			      <div class="modal-footer">
			        <input type="submit" class="btn btn-default" id='close' value="Close">
			      </div>
			    </div>
			
			  </div>
			</div>
	</form>
	
	
</body>
</html>