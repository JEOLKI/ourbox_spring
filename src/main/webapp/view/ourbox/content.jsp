<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
<link rel="apple-touch-icon" type="image/png" href="https://static.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" />
<meta name="apple-mobile-web-app-title" content="CodePen">

<link rel="shortcut icon" type="image/x-icon" href="https://static.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico" />

<link rel="mask-icon" type="" href="https://static.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111" />

  <title>메뉴</title>
  <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css'>
  

  <script>
  window.console = window.console || function(t) {};
</script>

  
  
  <script>
  if (document.location.search.match(/type=embed/gi)) {
    window.parent.postMessage("resize", "*");
  }
</script>


<style type="text/css">
.container{
 width: 180px;

}


</style>

</head>
<body>
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-3 sidenav">
    <p>님 안녕하세요</p>
      <p><img src="pro.png">
       <a href="logout.jsp" id="logout">로그아웃</a><br><br>
      
      <div class="container">
        <ul class="collapsible" data-collapsible="expandable">
            <li>
              <div class="collapsible-header"><i class="mdi-navigation-chevron-right"></i><a name="987"/>내 정보</a></div>
              <div class="collapsible-body"><p>내 정보 조회</p></div>
              <div class="collapsible-body"><p>내 정보 수정</p></div>
              <div class="collapsible-body"><p>회원 탈퇴</p></div>
            </li>
            <li>
              <div class="collapsible-header"><i class="mdi-navigation-chevron-right"></i>나의 요금제</div>
              <div class="collapsible-body"><p>구독신청</p></div>
              <div class="collapsible-body"><p>요금제 조회/변경</p></div>
            </li>
        </ul>
    </div>
      
      
      <hr>
      <p><img src="start.png"><a href="#">그룹추가</a></p>
    </div>
    
    <div class="col-sm-9 text-left"> 
    <div id="search"><input type="text"><input type="button" value="검색"></div>
      <p style="margin: 20px;">공지내용</p>
      <hr>
      <h1>시작하기</h1>
      <p>환영합니다. ourbox입니다.</p>
    </div>
  </div>
</div>
 <script src="https://static.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>
  <script src='https://code.jquery.com/jquery-2.0.0.js'></script>
 <script src='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js'></script>
  <script src="https://static.codepen.io/assets/editor/iframe/iframeRefreshCSS-e03f509ba0a671350b4b363ff105b2eb009850f34a2b4deaadaa63ed5d970b37.js"></script>
</body>
</html>