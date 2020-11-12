<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>요금제 등록</title>
<script>
function FormSubmit() {
	if ($('#planname').val()==''){
		alert("요금제 이름을 입력해주세요.");
		return false;
	} else if($('#planprice').val()==''){
		alert("요금제 가격을 입력해주세요.");
		return false;
	} else if($("#planContent").val()==''){
		alert("요금제 설명을 입력해주세요.");
		return false;
	} else if($("#planstocap").val()==''){
		alert("저장 공간 용량을 입력해주세요.");
		return false;
	} 
}


</script>
<style type="text/css">
	label {
	   width: 80px;
	   margin-top : 5px;
	   text-align:left;
	}
	.box{
		text-align: center;
	}
	h3{
		text-align: center;
	}
	#tb{
		margin: 20px;
	}
	#quit{
		margin-left: 35%;
	}
	
	
	
</style>
</head>
<body>
<div id="box">
<form id = "form01" action="<%=request.getContextPath()%>/PlanInsertServlet" name="join" method="post">
<h3>등록할 요금제의 정보를 <br>입력해주세요.</h3>
	 <table id="tb">
					<tr>
					<td><label for="planname"><b>요금제 이름</b></label></td>
					<td><INPUT type="text" id = "planname" name="planname"/></td>
					</tr>
					
					<tr>
					<td><label for = "planprice"><b>가 격</b></label></td>
					<td><input type="text" name ="planprice" id = "planprice"></td>
					</tr>
					
					<tr>
					<td><label for="planContent"><b>요금제 설명</b></label></td>
					<td> <input type="text" name="planContent" id="planContent"></td>
					</tr>
					
					<tr>
					<td><label for="planstocap"><b>저장 공간<br>용량</b></label></td>
					<td> <input type="text" name="planstocap" id="planstocap"></td>
					</tr>
				</table>
				<br>
      <input class="btn btn-default" id="quit" type="button" value="취소">
      <input class="btn btn-default" id="subtn" type="submit" value="등록" onclick="FormSubmit()">
   </form>
  </div>
</body>
</html>