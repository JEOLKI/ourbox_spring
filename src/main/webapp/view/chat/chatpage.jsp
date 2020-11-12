<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DevEric Chatting</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" href="/ourbox/css/public.css">

<style type="text/css">
	
	div::-webkit-scrollbar {
    	width: 1px;
  	}
  	
  	div::-webkit-scrollbar-thumb {
    	background-color: #EAEAEA;
    	border-radius: 10px;
    	background-clip: padding-box;
    	border: 2px solid transparent;
 	}
 	
  	div::-webkit-scrollbar-track {
    	background-color: #EAEAEA;
    	border-radius: 10px;
    	box-shadow: inset 0px 0px 5px #EAEAEA;
	}

	#inputMessage{
		width:70%;
		height:60px;
		margin-right: 0px;
	}
	
	#btn-submit{
		background: #EAEAEA;
		width:20%;
		height:63px;
	}
	
	#chat-container{
		vertical-align: bottom;
		border: none;
		margin:5px;
		margin-top: 0px;
		height: 490px;
		overflow: scroll;
		overflow-x:hidden;
	}
	
	.chat{
		font-size: 15px;
		color:black;
		margin: 5px;
		min-height: 20px;
		padding: 5px;
		min-width: 50px;
		text-align: left;
        height:auto;
        word-break : break-all;
        background: #E4F7BA;
        width:auto;
        display:inline-block;
        border-radius: 10px 10px 10px 10px;
	}
	
	.notice{
		font-size: 10px;
		color:#607080;
		font-weight: bold;
		border : none;
		text-align: center;
		background-color: #EAEAEA;
		display: block;
	}

	.my-chat{
		text-align: right;
		background: #FAECC5;
		border-radius: 10px 10px 10px 10px;
	}
	
	#bottom-container{
		margin:5px;
		width : 100%;
	}
	
	.chat-info{
		color:#556677;
		font-size: 10px;
		text-align: right;
		padding: 5px;
		padding-top: 0px;

	}
	
	.chat-box{
		text-align:left;
	}
	.my-chat-box{
		text-align: right;
	}
</style>
</head>
<body>
		<div id="chat-container">
			
		</div>
		<div id="bottom-container">
			<input id="inputMessage" type="text">
			<input id="btn-submit" type="submit" value="전송" >
		</div>
</body>



<script type="text/javascript">
	
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket('ws://localhost/ourbox/webChatServer');
	
	var inputMessage = document.getElementById('inputMessage');
	
	webSocket.onerror = function(e){
		onError(e);
	};
	webSocket.onopen = function(e){
		onOpen(e);
	};
	webSocket.onmessage = function(e){
		onMessage(e);
	};
	
	
	function onMessage(e){
		var chatMsg = event.data;
		var date = new Date();
		var dateInfo = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		if(chatMsg.substring(0,6) == 'server'){
			var $chat = $("<div class='chat notice'>" + chatMsg + "</div>");
			$('#chat-container').append($chat);
		}else{
			var $chat = $("<div class='chat-box'><div class='chat'>" + chatMsg + "</div><div class='chat-info chat-box'>"+ dateInfo +"</div></div>");
			$('#chat-container').append($chat);
		}
		
		
		$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
	}
	
	function onOpen(e){
		
	}
	
	function onError(e){
		alert(e.data);
	}
	
	function send(){
		var chatMsg = inputMessage.value;
		if(chatMsg == ''){
			return;
		}
		var date = new Date();
		var dateInfo = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		var $chat = $("<div class='my-chat-box'><div class='chat my-chat'>" + chatMsg + "</div><div class='chat-info'>"+ dateInfo +"</div></div>");
		$('#chat-container').append($chat);
		webSocket.send(chatMsg);
		inputMessage.value = "";
		$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
	}
	
</script>

<script type="text/javascript">
	$(function(){
		$('#inputMessage').keydown(function(key){
			if(key.keyCode == 13){
				$('#inputMessage').focus();
				send();
			}
		});
		$('#btn-submit').click(function(){
			send();
		});
		
	})
</script>
</html>