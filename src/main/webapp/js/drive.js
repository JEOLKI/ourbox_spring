
var createFolder = function(drive_name, room_seq, drive_path) {
	
	$.ajax({
		
		url : '/ourbox/CreateFolderController',
		type : 'post',
		data : {"drive_name" : drive_name,
				"room_seq"	: room_seq},
		dataType : 'json',
		success : function(res) {
			//alert("폴더를 생성했습니다.")
			location.href="/ourbox/DriveListController?roomSeq="+room_seq+"";
		},
		error : function(xhr) {
			//alert("폴더 생성을 실패하셨습니다.")
			location.href="/ourbox/DriveListController?roomSeq="+room_seq+"";
		}
		
	})
	
}


var setPCookieDrive = function(drive_seq,drive_path){
	event.stopPropagation();
	$.ajax({
		url 		: '/ourbox/SetPDriveCookieController',
		type 		: 'get',
		data 		: {"drive_seq" : drive_seq,
						"drive_path" : drive_path},
		dataType 	: 'json',
		success 	: function(res) {
		},
		error 		: function(xhr) {
			//alert("상태 : " + xhr.status);
		}
	})
	
}


var setDivCookieDrive = function(room_seq,room_path){
	$.ajax({
		url 		: '/ourbox/SetDivDriveCookieController',
		type 		: 'get',
		data 		: {"room_seq" : room_seq,
						"room_path" : room_path},
		dataType 	: 'json',
		success 	: function(res) {
		},
		error 		: function(xhr) {
			//alert("상태 : " + xhr.status);
		}
	})
	
}


var downloadDrive = function(drive_seq){
	
	var win = window.open("/ourbox/DriveDownloadController?drive_seq="+drive_seq, "_parent");
	
}

var reName = function(drive_seq, newName){
	
	$.ajax({
		url 		: '/ourbox/DriveRenameController',
		type 		: 'post',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",	// 한글 깨짐 방지 encoding
		data 		: {"drive_seq" : drive_seq,
						"newName" : newName },
		dataType 	: 'json',
		success 	: function(res) {
			room_seq = res.room_seq;
			
			location.href="/ourbox/DriveListController?roomSeq="+room_seq+"";
		},
		error 		: function(xhr) {
			//alert("상태 : " + xhr.status);
			location.href="/ourbox/DriveListController?roomSeq="+room_seq+"";
		}
	})
}



var pasteDrive = function(room_seq,copyDriveSeq){
	
	$.ajax({
		url 		: '/ourbox/DrivePasteController',
		type 		: 'post',
		data 		: {"roomSeq" : room_seq,
					   "copyDriveSeq" : copyDriveSeq},
		dataType 	: 'json',
		success 	: function(res) {
			location.href="/ourbox/DriveListController?roomSeq="+room_seq+"";
		},
		error 		: function(xhr) {
			//alert("상태 : " + xhr.status);
			location.href="/ourbox/DriveListController?roomSeq="+room_seq+"";
		}
	})
}


var changeDriveStatus = function(drive_seq){
	$.ajax({
		url 		: '/ourbox/DriveStatusChangeController',
		type 		: 'get',
		data 		: {"drive_seq" : drive_seq },
		dataType 	: 'json',
		success 	: function(res) {
			room_seq = res.room_seq;
			
			location.href="/ourbox/DriveListController?roomSeq="+room_seq+"";
		},
		error 		: function(xhr) {
			location.href="/ourbox/DriveListController?roomSeq="+room_seq+"";
		}
	})
}


var searchDrive = function(keyword){
	$.ajax({
		url 		: '/ourbox/DriveSearchController',
		type 		: 'get',
		data 		: {"keyword" : keyword },
		dataType 	: 'json',
		success 	: function(res) {
			$('.driveBox').empty();
				code ="";
				$.each(res, function(i, v) {
					code += "<table  class='list' id='"+v.drive_seq+"' onmousedown='setPCookie(this.id,'"+v.drive_path+"')' >"
					code += "    <tr><td class='typeImg'>"
					code += "	<img src='/ourbox/images/"+v.drive_type+".png'></td></tr>"
					code += "    <tr><td>"+v.drive_name+"</td></tr>"
					code += "</table>"
				})		
			$('.driveBox').html(code);
					
				
		},
		error 		: function(xhr) {
			//alert("상태 : " + xhr.status) ;
		}
	})
}


