<%@page import="ourbox.common.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ page import="java.net.*, java.io.*"%>
<%
	
	String mem_id = (String)request.getAttribute("memId");
    // 생성된 토큰 받음
    String g_recaptcha_response = request.getParameter("g-recaptcha-response");
    System.out.println(g_recaptcha_response);
    
    // 토큰과 보안키를 가지고 성공 여부를 확인 함
    HttpURLConnection conn = (HttpURLConnection) new URL("https://www.google.com/recaptcha/api/siteverify").openConnection();
    String params = "secret=6LdMtcsZAAAAACbaoSoojdeHqYK3i641-YqgQwFs" + "&response=" + g_recaptcha_response;
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    wr.writeBytes(params);
    wr.flush();
    wr.close();
    
    // 결과코드 확인(200 : 성공)
    int responseCode = conn.getResponseCode();
    StringBuffer responseBody = new StringBuffer();
    if (responseCode == 200) {
        
        // 데이터 추출
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
        String line;
        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        bis.close();
        
        // JSON으로 변환 하여야 하지만 기본 모듈에서 처리하기위하여 아래와 같이 진행 합니다
        if(responseBody.toString().indexOf("\"success\": true") > -1)
            out.println("인증 되었습니다...");
    }
    
%>
<!DOCTYPE html>
<head>
<script src="jquery-3.5.1.min.js"></script>
<title>회원가입</title>
<meta http-equiv="Content-Type" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		url : "join.jsp",
		dataType:"json",
		success : function(data) {
			console.log(data.key);
			$("#key").val(data.key);
			$("#div01").html("<img src='captchaImage/"+data.captchaImageName+"'>");
		}
	});
	$("#btn01").on("click",function(){
		var form01Data = $("#form01").serialize();
		console.log(form01Data);
		$.ajax({
			url : "join.jsp",
			data : form01Data,
			dataType:"json",
			success : function(data) {
			}
		});
	});
});

// 아이디 중복체크
$(function(){
	$('#dupchk').on('click',function(){
		id1 = $('#EmailID1').val().trim();
		id2 = $('#EmailDomain1').val().trim();
		$.ajax({
			url:'/ourbox/MemberIdCheckController',
			type:'get',
			data : {"EmailID1" : id1,
				"EmailDomain1" : id2},
			success : function(res){
				$('#idchk').html(res.sw).css('color','red');
				if(res.sw == "중복된 아이디 입니다."){
					$('#certibtn').attr('disabled','disabled');
				}else if(res.sw == "사용 가능한 아이디입니다."){
					$('#certibtn').removeAttr("disabled");
				}
			},
			error : function(xhr){
			},
			dataType:'json'
			
		})
	})
})
$("#EmailID1")
var correctCaptcha = function(response) {
    console.log();
};
var onloadCallback = function() {
    grecaptcha.render('html_element', {
        'sitekey' : '6LdMtcsZAAAAAPvuhKeQI0O2y6ikT826n6reK1Nq',
        'callback' : correctCaptcha
    });
};

$(function(){
	//비밀번호 불일치 시 경고
	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("input").keyup(function(){
		var pwd1=$("#pass1").val();
		var pwd2=$("#pass2").val();
		if(pwd1 != "" || pwd2 != ""){
			if(pwd1 == pwd2){
				$("#alert-success").show();
				$("#alert-danger").hide();
				$("#sendbtn").removeAttr("disabled");
			}else{
				$("#alert-success").hide(); 
				$("#alert-danger").show(); 
				$("#sendbtn").attr("disabled", "disabled");
			} 
		}
	});
	//인증번호 이메일 전송
	$('#emailchk').on('click', function(){
		id1 = $('#EmailID1').val().trim();
		id2 = $('#EmailDomain1').val().trim();
		$.ajax({
			url:'/ourbox/MailServlet',
			type : 'get',
			data : {"EmailID1" : id1,
				"EmailDomain1" : id2},
			success : function(res){
				$('#num').html(res.sw).css('color','red');
			},
			error : function(xhr){
				alert("상태" + xhr.status);
			},
			datatype : 'json'
		})
	})
	//인증번호 입력 확인 및 경고
	$("#certi-success").hide();
	$("#certi-fail").hide();
	$('#certibtn').on('click',function(){
		var inputcerti = $('#certification').val(); //인증번호 입력
		if(inputcerti != ""){ // 입력을 안했을 때
			if(inputcerti == 12345){  // 일치했을 때
				$("#certi-success").show();
				$("#certi-fail").hide();
				$("#sendbtn").removeAttr("disabled");
			}else{
				$("#certi-success").hide(); 
				$("#certi-fail").show(); 
				$("#sendbtn").attr("disabled", "disabled");
			} 
		}
	})

	$('#quit').on('click',function(){
		location.href='/ourbox/MemberLoginController'
	})
});
// 회원가입 입력란 미 기입시 경고
function FormSubmit() {
	if (grecaptcha.getResponse() == ""){
		alert("Captcha 확인을 체크해주세요.");
		return false;
	} else if($("input:checkbox").is(":checked") == false){
		alert("이용약관을 체크해주세요.");
		return false;
	} else if($('#EmailID1').val()==''){
		alert("ID를 입력해주세요.");
		return false;
	} else if($('#EmailDomain1').val()==''){
		alert("도메인을 입력해주세요.");
		return false;
	} else if($(".pass").val()==''){
		alert("비밀번호를 입력해주세요.");
		return false;
	} else if($("#name").val()==''){
		alert("이름을 입력해주세요.");
		return false;
	} else if($("#nickname").val()==''){
		alert("닉네임을 입력해주세요.");
		return false;
	} else if($("#bir").val()==''){
		alert("생년월일을 입력해주세요.");
		return false;
	} else if($("#tel").val()==''){
		alert("전화번호를 입력해주세요.");
		return false;
	} else if($("#zip").val()==''){
		alert("우편번호를 입력해주세요.");
		return false;
	} else if($("#add1").val()==''){
		alert("기본주소를 입력해주세요.");
		return false;
	} else if($("#add2").val()==''){
		alert("상세주소를 입력해주세요.");
		return false;
	}
}

</script>
<script language="javascript">
// 이메일 선택
function changeSelect(s,no){
    form = document.join; // 폼 위치
    sel = s[s.selectedIndex].value; // 선택된 값
    dis = 1;
 
    if(sel=="user"){
        sel = "";
        dis = 0;
    }
    if(no==1){
        form.EmailDomain1.value = sel;
        form.EmailDomain1.readOnly = true;
    }else{
        form.EmailDomain2.value = sel;
        form.EmailDomain2.readOnly = true;
    }
}

</script>
</head>
<style>
	body{
	
	background: linear-gradient(90deg, white 70%, #E4E4E4 30%);
	
	}

label {
	   width: 100px;
	   margin-top : 5px;
	   text-align:left;
	}	
	.box{
	margin-top:30px; 
	
	}
	#img1{
/* 		float: left; */
		width : 1200px;
/* 		margin-left: 90px; */
/* 		padding-left: 100px; */
	
	}
	.certi,.alert{
		color : red;
		height : 20px;
	}
	#div1{
		display: inline-block;
		margin-left: 50px;
	}
	#left{
		float: left;
	}
	#right{
		float: right;
		margin-right: 20px;
	}
	#alert-danger{
		vertical-align: middle;
	}
	#im{
		float: left;
		width: 800px;
		padding: 10px;
		margin-top: 74px;
		margin-left: 74px;
	}
	input{
		border : 1px solid #8C8C8C;
		border-radius: 6px; 
	}
	#add{
		margin-left: 105px;
	}
</style>
<body>
	<div class="box" style="height:10px;">
		<section id ="left">
			<div id=im>
				<img id="img1" alt="" src="images/ourbox_main2.PNG">
			</div>
		</section>
		<section id ="right" style="margin-top: 50px;">
			<div id="div1">
				<form id = "form01" action="MemberInsertController" name="join" method="post"onsubmit="return FormSubmit();">
     				<label for="use">이용약관</label><br>
					<textarea name="usecontent" rows="10" cols="65">제 1 장 총칙
제 1 조 (목 적)
이 약관은 'ourbox' (이하 '회사'라 합니다.)가 제공하는 서비스의 이용조건 및 절차, 기타 필요한 사항을 규정함을 목적으로 합니다.
제 2 조 (약관의 효력 및 변경) 1. 이 약관의 내용은 서비스 화면에 게시하거나 기타의 방법으로 회원에게 공지함으로써 효력이 발생합니다.
2. 회사는 합리적인 사유가 발생될 경우에는 이 약관을 변경할 수 있으며, 약관이 변경되는 경우에는 최소한 7일전에 1항과 같은 방법으로 공시합니다.
3. 본 사이트 안에 새로운 서비스가 개설될 경우 별도의 명시된 설명이 없는 한 이 약관에 따라 제공됩니다.
제 3 조 (약관 외 준칙)
이 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법 및 기타 관련법령의 규정에 따릅니다.
제 4 조 (정의)
1. 이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
① 회 원 : 회사와 서비스 이용계약을 체결한 개인이나 법인 또는 법인에 준하는 단체
② 운영자 : 서비스의 전반적인 관리와 원활한 운영을 위하여 회사가 선정한 사람
③ 아이디(ID): 회원식별과 회원의 서비스 이용을 위하여 회원이 선정하고 회사가 승인하는 문자와 숫자의 조합
④ 비밀번호 : 회원의 정보 보호를 위해 회원 자신이 설정한 문자와 숫자의 조합
⑤ 서비스 중지: 정상이용 중 회사가 정한 일정한 요건에 따라 일정 기간 동안 서비스의 제공을 중지하는 것
⑥ 해지 : 회사 또는 회원이 서비스 개통 후 이용계약을 해약하는 것
2. 본 약관에서 사용하는 용어의 정의는 제1항에서 정하는 것을 제외하고는 관계법령 및 서비스별 안내에서 정하는 바에 의합니다.
제 2장 이용계약 체결
제 5 조 (서비스의 구분)
1. 회사가 회원에게 제공하는 서비스는 기본서비스와 부가서비스 등으로 구분합니다.
2. 서비스의 종류와 내용 등은 회사가 공지나 서비스 이용안내에서 별도로 정하는 바에 의합니다.
제 6 조 (이용계약의 성립)
아래 ' 위의 이용약관에 동의하십니까? ' 라는 물음에 회원이 '동의' 버튼을 누르면 이 약관에 동의하는 것으로 간주됩니다. 약관 변경 시에도 이와 동일하며, 변경된 약관에 동의하지 않을 경우 회원 등록 취소가 가능합니다.
제 7 조 (서비스 이용신청)
1. 이용신청은 이용 신청자가 회사가 지정한 회원가입 신청서를 온라인으로 작성하여 제출함으로써 신청할 수 있습니다.
2. 이용계약은 회원의 회원가입에 대하여 회사의 이용승낙으로 성립합니다.
3. 다음과 같은 경우 회사는 이용 신청자의 이용 신청 승낙을 제한할 수 있고, 이 사유가 해소될 때까지 승낙을 유보할 수 있습니다.
① 서비스 관련 설비 용량이 부족한 경우
② 기술상 문제가 있는 경우
③ 기타 부득이한 사정이 있는 경우
④ 이용신청자는 다음 사항을 준수하여야 합니다.
가입신청 양식에는 실명을 사용해야 합니다.다른 사람의 명의를 사용하여 기재하지 않아야 합니다.
회원가입 양식의 내용은 현재의 사실과 일치해야 합니다.사회의 안녕, 질서 또는 미풍양속을 저해할 목적으로 신청해서는 아니 됩니다.
등록 양식 또는 그 내용에 변경이 생긴 경우 현재의 사실과 일치하는 완전한 정보로 갱신해야 합니다.
이용신청자가 제공한 정보가 부정확하거나 현재의 사실과 일치하지 않는 경우, 또는 그러하다고 의심할 수 있는 합리적인 이유가 있는 경우 서비스 이용을 제한할 수 있습니다. ⑤ 만 14세 미만의 어린이는 부모 등 법정 대리인의 동의를 얻은 후에 서비스 이용을 신청하여야 합니다. 또한 만 20세 미만의 이용자가 유료 서비스를 이용하고자 하는 경우에도 이와 같습니다.
제 8 조 (회원 아이디의 변경)
1. 다음의 경우 회사는 직권으로 회원 아이디를 변경하도록 요구하거나, 회원의 신청에 의해 회원 아이디를 변경할 수 있습니다.
① 회원 아이디가 회원의 전화번호, 주민등록번호 등으로 등록되어 회원 사생활 침해의 우려가 있는 경우
② 타인에게 혐오감을 주거나 미풍양속에 저해되는 경우
③ 기타 회사 소정의 합리적인 이유가 있는 경우
제 9 조 (회원 정보사용에 대한 동의)
1. 회사가 이용 신청서에 기재를 요구하는 회원 정보는 본 이용계약을 이행하고 이용계약상 서비스 제공을 위한 목적으로 이용합니다.
2. 회원들이 회사 및 제휴업체의 서비스를 편리하게 사용할 수 있도록 하기 위해 회원 정보는 회사 및 제휴 업체에 제공될 수 있습니다. 이 경우 회사는 사전에 공지하며 이에 동의하지 않는 회원은 등록을 취소할 수 있습니다. 다만 계속 이용하는 경우 동의하는 것으로 간주합니다.
3. 전기통신사업법 등 법률 규정에 의해 국가 기관의 요구가 있는 경우, 수사상 목적이 있거나 정보 통신 윤리 위원회의 요청이 있는 경우 또는 기타 관계법령 절차에 따른 정보제공 요청이 있는 경우 회원 정보가 제공 될 수 있습니다.
4. 회사는 업무와 관련하여 회원 전체 또는 일부의 개인정보에 관한 통계자료를 작성하여 사용하거나 이를 다른 기관에 제공할 수 있습니다.
5. 회원은 언제든지 본인의 개인정보를 열람하고 변경사항을 정정할 수 있습니다.
회원이 약관의 내용에 동의하지 않고 등록을 취소하는 경우 회사는 회원의 정보를 삭제합니다.
6. 회사는 서비스를 통해 회원의 컴퓨터에 쿠키를 전송할 수 있습니다. 회원은 쿠키 수신을 거부하거나 쿠키 수신에 대해 경고하도록 브라우저 설정을 변경할 수 있습니다.
제 10 조 (개인정보 수집 및 이용목적)
1. 회원의 서비스 질 향상과 사이트 이벤트시 이용합니다.
2. 회원 정보를 이용한 비용 청구
3. 회원 정보를 이용한 마케팅
제 11 조 (개인정보 보유 및 이용기간)
1. 개인 정보는 회원이 해지 전까지 보유하며, 해지시까지 회원 정보를 회사에서 보유합니다. 단, 홈페이지에 로그인 한 후 1년간 사용하지 않으면 자동 삭제됩니다.
2. 회원은 본인이 해지 전까지 이용할 수 있습니다.
제 3 장 서비스 이용
제 12 조 (서비스의 이용시간)
1. 회사는 회원의 이용신청을 승낙한 때부터 즉시 서비스를 개시합니다. 단, 회사의 업무상 또는 기술상의 장애로 인하여 서비스를 개시하지 못하는 경우, 서비스에 공지하거나 회원에게 즉시 이를 통지합니다.
2. 서비스의 이용은 연중무휴 1일 24시간을 원칙으로 합니다. 다만 회사가 업무상 또는 기술상의 이유로 서비스의 전부 또는 일부가 일시 중지 되거나, 운영상의 목적으로 회사가 정한 기간에는 서비스의 전부 또는 일부가 일시 중지 될 수 있습니다. 이러한 경우 회사는 사전 또는 사후 이를 공지합니다.
3. 회사는 서비스 별로 이용 가능한 시간을 별도로 정할 수 있으며 이 경우 그 내용을 사전에 공지합니다.
제 13 조 (서비스 제공 및 변경)
1. 회사는 회사가 제공하는 서비스에서 진행하는 컨텐츠와 이벤트 등의 모든 서비스를 회원에게 제공합니다.
2. 회사에서 제공하는 서비스는 무료서비스와 유료서비스가 있습니다. 서비스 추가 시 사전 공지하며 이용에 대한 사항은 회사가 별도로 정한 서비스 약관 및 정책 또는 운영규칙에 따릅니다.
3. 회사는 서비스 변경 시 그 변경될 서비스의 내용 및 제공일자를 사이트 초기화면에 게시하거나 전자메일 등을 통하여 회원에게 통지합니다.
제 14 조 (서비스의 요금)
1. 회사가 제공하는 배송대행 및 구매대행 서비스는 유료이며 각 서비스에서 표시된 요금을 지불하여야 이용할 수 있습니다.
제 15 조 (정보의 제공 및 광고의 게재)
1. 회사는 서비스의 운용과 관련하여 서비스 화면, 홈페이지, 이메일 등의 회사에서 지정하는 위치에 광고 등을 게재할 수 있습니다. 이는 광고주와의 계약관계에 의하거나 제공받은 컨텐츠의 출처를 밝히기 위한 조치입니다.
2. 회원은 회사에서 제공하는 홈페이지 배너 광고에 대한 임의의 삭제, 비방 기타 홈페이지 배너 광고 방해 행위 등을 할 수 없습니다.
3. 서비스 내에 포함되어 있는 링크를 클릭하여 타 사이트의 페이지로 옮겨갈 경우 해당 사이트의 개인정보보호정책은 회사와 무관합니다.
제 16 조 (게시물 또는 내용물의 삭제)
회사는 서비스의 게시물 또는 내용물이 규정에 위반되거나 게시기간을 초과하는 경우 사전 통지나 동의 없이 이를 보완 또는 삭제 할 수 있습니다.
제 17 조 (서비스 제공의 중지)
무료 서비스의 경우, 회사는 회사의 필요에 따라 언제든지 본 서비스의 전부 또는 일부를 수정하거나 중단 할 수 있으며, 이 경우 회사는 전자우편 또는 인터넷 홈페이지 등을 통하여 회원에게 즉시 이를 고지합니다.
제 4 장 권리와 의무
제 18 조 (회사의 의무)
1. 회사는 특별한 사유가 없는 한 서비스 제공설비를 항상 운용 가능한 상태로 유지 보수하여야 하며, 안정적으로 서비스를 제공할 수 있도록 최선의 노력을 다하여야 합니다.
2. 회사는 서비스와 관련한 회원의 불만사항이 접수되는 경우 이를 즉시 처리하여야 하며, 즉시 처리가 곤란한 경우 그 사유와 처리일정을 서비스 또는 전자우편을 통하여 동 회원에게 통지하여야 합니다.
제 19 조 (회원의 의무)
1. 회원은 관계 법령, 본 약관의 규정, 이용안내 및 서비스상에 공지한 주의사항, 회사가 통지하는 사항을 준수하여야 하며, 기타 회사의 업무에 방해되는 행위를 하여서는 안됩니다.
2. 회원은 회사의 사전 동의 없이 서비스를 이용하여 어떠한 영리행위도 할 수 없으며, 법에 저촉되는 자료를 배포 또는 게재할 수 없습니다.
3. 회원은 자신의 아이디와 비밀번호를 유지 관리할 책임이 있으며 자신의 아이디와 비밀번호를 사용하여 발생하는 모든 결과에 대해 전적인 책임이 있습니다. 또한 자신의 아이디와 비밀번호가 자신의 승낙 없이 사용되었을 경우 즉시 회사에 통보하여야 합니다.
4. 회원은 서비스와 관련하여 다음 사항을 하여서는 아니 됩니다.
① 서비스를 이용하여 얻은 정보를 회사의 사전 승낙 없이 복사, 복제, 변경, 번역, 출판, 방송 기타의 방법으로 사용하거나 이를 타인에게 제공하는 행위
② 자신의 홈페이지와 게시판에 음란물을 게재 또는 음란사이트를 링크하거나, 유포 등 사회질서를 해치는 행위
③ 타인의 명예를 훼손하거나 모욕하는 행위, 타인의 지적재산권 등의 권리를 침해하는 행위
④ 해킹 또는 컴퓨터 바이러스를 유포하는 일, 타인의 의사에 반하여 광고성 정보 등 일정한 내용을 지속적으로 전송하는 행위
⑤ 다른 회원의 아이디를 부정 사용하는 행위
⑥ 다른 사용자의 개인 정보를 수집, 저장하는 행위
⑦ 회사 직원, 포럼 리더 등을 포함한 타인을 사칭하는 행위
⑧ 서비스를 통해 전송된 컨텐츠의 발신인을 위조하는 행위
⑨ 타인을 스톡(stalk)하거나, 괴롭히는 행위
⑩ 서비스의 운영에 지장을 주거나 줄 우려가 있는 일체의 행위, 기타 관계 법령에 위배되는 행위
제 20 조 (양도 금지)
회원은 서비스의 이용권한, 기타 이용 계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다.
제 21 조 (게시물에 대한 권리 및 책임)
1. 회사는 회원의 게시물을 소중하게 생각하며 변조, 훼손, 삭제되지 않도록 최선을 다하여 보호합니다. 다만, 다음 각 호에 해당하는 게시물이나 자료의 경우 사전통지 없이 삭제하거나 이동 또는 등록거부를 할 수 있으며, 해당 회원의 자격을 제한, 정지 또는 상실시킬 수 있습니다.
① 다른 회원 또는 제3자에게 심한 모욕을 주거나 명예를 손상시키는 내용인 경우
② 공공질서 및 미풍양속에 위반되는 내용을 유포하거나 링크시키는 경우
③ 불법복제 또는 해킹을 조장하는 내용인 경우
④ 영리를 목적으로 하는 광고일 경우
⑤ 범죄적 행위에 결부된다고 인정되는 내용인 경우
⑥ 회사나 다른 회원의 저작권 혹은 제3자의 저작권 등 기타 권리를 침해하는 내용인 경우
⑦ 회사에서 규정한 게시물 원칙에 어긋나거나, 게시판 성격에 부합하지 않는 경우
⑧ 스팸성 게시물인 경우
⑨ 기타 관계법령에 위배된다고 판단되는 경우
2. 회사가 작성한 저작물에 대한 저작권 및 기타 지적재산권은 회사에 귀속됩니다.
3. 회원이 서비스 화면 내에 게시한 게시물의 저작권은 게시한 회원에게 귀속됩니다. 또한 회사는 게시자의 동의 없이 게시물을 상업적으로 이용할 수 없습니다. 다만, 비영리 목적인 경우는 그러하지 아니하며, 또한 본 사이트 내에서의 게재권을 갖습니다.
제 22 조 (계약해지 및 이용제한)
1. 회원이 약관의 내용을 위반할 경우 회사는 소정의 기간 이내에 이를 해소할 것을 통보하거나 즉시 해지할 수 있습니다.
2. 회사는 제 1항에 의해 해지 된 회원이 다시 이용신청을 하는 경우 일정기간 그 승낙을 제한할 수 있습니다.
3. 회원이 이용계약을 해지하고자 하는 때에는 회원 본인이 서비스 또는 전자우편을 통하여 해지신청을 하여야 합니다.
제 23 조 (손해배상)
회사가 제공하는 서비스와 관련하여 회원에게 어떠한 손해가 발생하더라도 회사의 중대한 과실에 의한 경우를 제외하고 이에 대하여 책임을 부담하지 않습니다.
제 24 조 (면책 조항)
1. 회사는 천재지변, 전쟁 및 기타 불가항력, 회사의 합리적인 통제 범위를 벗어난 사유로 인하여 서비스를 제공할 수 없는 경우에는 그에 대한 책임이 면제됩니다.
2. 회사는 기간통신 사업자가 전기통신 서비스를 중지하거나 정상적으로 제공하지 아니하여 손해가 발생한 경우 책임이 면제됩니다.
3. 회사는 회원이 서비스를 통해 게재 또는 전송한 정보, 자료, 사실의 정확성, 신뢰성 등 내용에 관하여 어떠한 보증도 하지 아니하며 회원의 서비스 자료에 대한 취사선택 또는 이용으로 발생하는 손해 등에 대해 책임을 지지 아니합니다.
4. 회사는 회원이 서비스를 이용하여 기대하는 손익이나 서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지 아니합니다.
5. 회사는 회원 상호간 또는 회원과 제 3자 상호간에 서비스를 매개로 발생한 분쟁에 대해서는 개입할 의무가 없으며 이로 인한 손해를 배상할 책임도 없습니다.
6. 회사는 회원의 귀책사유로 인하여 서비스 이용의 장애가 발생한 경우에는 책임이 면제 됩니다.
7. 본 약관의 규정을 위반함으로 인하여 회사에 손해가 발생하게 되는 경우, 이 약관을 위반한 회원은 회사에 발생되는 모든 손해를 배상하여야 하며, 동 손해로부터 회사를 면책시켜야 합니다.
8. 회사는 서비스에서 제공되는 전자우편의 메시지, 게시판의 게시물 등이 보유되는 최대일수, 송수신 할 수 있는 전자우편 메시지의 최대크기, 회원에게 할당되는 최대 디스크 공간 등 일반 사용에 대한 제한을 할 수 있습니다. 또한 일정 기간 동안 활동이 없는 계정을 해지할 수 있습니다.
9. 어떠한 경우를 불문하고 회사는 배송지연으로 인한 손해에 대해서 책임지지 않습니다.
10. 회사에서 배송 및 구매대행 신청서 작성시 주의사항 안내에 따르지 않은 회원에 손실에 대해서는 책임지지 않습니다.
부칙
이 약관은 2020년 09월 01일부터 시행합니다.</textarea><br>
	 이용약관에 동의합니다.
	 				<input type="checkbox"><br>
	 				<span id ="usespan"></span><br>
	 				<label for="mem_id">아이디</label>
	    			<input type="Hidden" name="EditValue1" />
	    			<INPUT type="text" id = "EmailID1" name="EmailID1" size="8" />@
	    			<INPUT type="text" id = "EmailDomain1" name="EmailDomain1" size="8" />
	     
	    			<SELECT name="SelEmailDomain1" onchange="changeSelect(this,1);">
				        <option value="">메일주소선택</option>
				        <option value='naver.com'>네이버</option>
				        <option value='gmail.com'>구글</option>
				        <option value='nate.com'>네이트</option>
				        <option value='yahoo.co.kr'>야후코리아</option>
				        <option value='hanmail.net'>한메일</option>
				        <option value="user">직접입력</option>
	    			</SELECT>
	    			
      				<button id="dupchk" type="button">중복체크</button><br>
      				<div id = "idchk"></div>
   					<label for = "certification">인증번호</label>
   					<input type="text" name ="certification" id = "certification">
    				<button id="emailchk" type="button">인증번호 전송</button>
   					<input type="button" id = "certibtn" value = "인증번호 확인">
   					<!-- 수정해야할 부분 -->
   					<div class="certi" id="certi-success" >인증번호가 일치합니다.</div>
      				<div class="certi" id="certi-fail">인증번호가 일치하지 않습니다.</div>
      
      				<span id ="emailspan"></span><br>
					
					<div id="pwinput" style="float: left">
	      				<label for="pass">비밀번호</label>
						<input type="password" name="mem_pass1" id="pass1" class= "pass"><br>
						<label for="pass">비밀번호 확인</label>
	      				<input type="password" name="mem_pass2" id="pass2" class= "pass"><br>
					</div>
					
      				<div id="pwoutput" style="float: right;">
	      				<div class="alert alert-success" id="alert-success" style="height:50px; width:150px; margin-right: 20px;">비밀번호 일치</div>
	      				<div class="alert alert-danger" id="alert-danger" style="height:50px; width:150px; margin-right: 20px;">비밀번호 불일치</div><br>
      				</div>
      				<br>
      				<br>
      				<br>
      
					<label for="nickname">닉네임</label>
					<input type="text" name="mem_nickname" id="nickname"><br>
									
					<label for="name">이름</label>
					<input type="text" name="mem_name" id="name"><br>
									
					<label for="bir">생년월일</label>
					<input type="date" name="mem_bir" id="bir"><br>
									
					<label for="tel">휴대폰 번호</label>
					<input type="text" placeholder="010-1234-5678" name="mem_hp" id="tel"><br>
									
					<label for="zip">주소</label>
					<input type="text" name="mem_zip" id="zip">
					<button class="but" type="button" data-toggle="modal" data-target="#myModal">우편번호 찾기</button><br>
				      
				    <div id = "add">
					<label for="addr1"><input type="text" size="15.7" name="mem_add1" id="add1" ></label><br>
					<label for="addr2"><input type="text" size="15.7" name="mem_add2" id="add2" ></label><br><br>
				    </div>
				      
					<div id="html_element"></div>
					<br>
					<div id="btns" style="float: right; margin-right: 200px;">
						<input id="sendbtn" type="submit" value="회원가입">
						<input id="quit" type="button" value="취소">
					</div>
				</form>
			</div>
		</section>	
	</div>





<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">우편 번호 찾기</h4>
        </div>
        <div class="modal-body">
          <p>찾고자 하는 동을 입력하세요</p>
		   <input type="text" id="dong">
		   <input id="zipchk" type="button" value="확인">
		   <br><br>
		   <div id="result1"></div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

</body>
</html>