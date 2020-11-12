/**
 * 
 */

var alarmList = function(memId) {
	
	$.ajax({
		url : '/ourbox/AlarmListController',
		data : { "memId" : memId},
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			$('.modal-body').empty();
			
			code = "";
			$.each(res, function(i,v) {
				code += "<div class='alarmContent'>"
				code += "<span>";
				code += v.alarm_content;
				code += "</span>";
				code += '<button id="'+v.alarm_seq+'" type="button" class="alarmDelete">지우기</button><br>';
				code += "</div>"
			})
				
			
			$('.modal-body').append(code);
		}
	
	})
	
}


var	deleteAlarm = function(alarm_seq) {
	
	$.ajax({
		url : '/ourbox/DeleteAlarmController',
		data : {"alarm_seq" : alarm_seq },
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			$(this).parents('.alarmContent').remove();
			
		}
	
	})
	
}

var	deleteAllAlarm = function(memId) {
	
	$.ajax({
		url : '/ourbox/DeleteAllAlarmController',
		data : {"mem_id" : memId },
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			$('.modal-body').empty();
			
		}
		
	})
	
}

