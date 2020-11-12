<%@page import="ourbox.common.vo.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<NoticeVO> noticeList = (List<NoticeVO>) request.getAttribute("noticeList");

%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/ourbox/css/public.css">

<script type="text/javascript">


</script>

<style type="text/css">


	#noticeIcon{
		position: relative;
	}
	
	.bold{
		vertical-align: middle;
	}
	
	#box{
		margin: 3%;
	}
	
	.container{
      padding-right: 0px;
       padding-left: 0;
         margin-right: 0;
       margin-left: 0;
       width : 100%;
   }
   hr{
	margin : 0px;
   }

</style>


<title>ourbox : start</title>
</head>
<body>
	
<div id='box'>

	<div class="container">
	  <div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading" >
				<h4 class="panel-title"">
			       <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
			       <img id='noticeIcon' alt="공지아이콘.png" src="/ourbox/images/공지아이콘.png">&nbsp;
			       <span class='bold'><%= noticeList.get(0).getNotice_title() %></span>
			       </a>
			    </h4>
			</div>
		<div id="collapse1" class="panel-collapse collapse">
			<div class="panel-body"><%= noticeList.get(0).getNotice_content().replaceAll("\\n", "<br>")%></div>
			</div>
		</div>
	  </div> 
	</div>
<hr>
	<img src="/ourbox/images/ourbox_main.png" style="margin-left: 120px; margin-top: 20px;">
	


</div>


</body>
</html>