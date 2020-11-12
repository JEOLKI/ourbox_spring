<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String mem_id = request.getParameter("memId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/ourbox/css/public.css">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
   $('#subtn').on('click',function(){
      opener.parent.location.reload(); // 부모창 새로고침
   })
   $('#quit').on('click',function(){
      window.close();
   })
})
</script>
<style>
   
   body{
      background: #E6E6E6;
      font-family:'문제부 돋움체';
   }
   
   .la{
      width: 100px;
   }
   
   #room_name{
      width: 72%;
      height: 30px;
      border: 1px solid #8C8C8C;
      vertical-align: middle;
      border-radius: 10px;
      margin-bottom: 5px;
      margin-left: 4px;
   }
   
   #room_content{
      width: 72%;
      height: 30px;
      border: 1px solid #8C8C8C;
      vertical-align: middle;
      border-radius: 10px;
      margin-bottom: 5px;
   }
   
   #box{
      text-align: center;
      margin-top: 3%;
   }

   .ibut{
      width:47%;
      font-weight: bold;
      margin-top: 5px;
   }
   
   h4{   
      margin-left: 10px;
      font-weight: bold;
      text-align: left;
      display:inline-block; 
      float:left;
   }
   img{
      display: inline-block;
      width: 25px;
      height: 25px;
      float:left;
      position: relative;
      top : 5px;
      left: 5px;
   }


</style>
</head>
<body onresize="parent.resizeTo(450,250)" onload="parent.resizeTo(450,250)">

<div id='box'>

   <img src="/ourbox/images/door.png">
   <h4>GRUOP 생성</h4>
   <br><br>
   <form action="<%=request.getContextPath()%>/RoomInsertController" method="post" id = "form1">
      <div>
         <label class='la'>GROUP 이름 :</label><input type="text" id="room_name" name ="room_name"><br>
         <label class='la'>GROUP 설명 :</label>
         
         <input type = "text" id="room_content" name = "room_content"><br>
         <input type="hidden" id="mem_id" name = "mem_id" value = "<%=mem_id%>">
         
         <input class='ibut' type="submit" id ="subtn" value = "생성"> &nbsp;&nbsp;
         <input class='ibut' type="button" id ="quit" value="취소">
      </div>
   </form>
</div>   
   
</body>
</html>