/**
 * 
 */


var	deleteMemo = function(byeMemo) {
	
	$.ajax({
		url : '/ourbox/DeleteMemoController',
		data : byeMemo,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			location.reload();
			
		}
	
	})
	
}

var	updateMemo = function(memo) {
	
	$.ajax({
		url : '/ourbox/UpdateMemoController',
		data : memo,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			window.opener.location.reload();
			window.close();
			
		}
		
	})
	
}



var insertMemo = function(newMemo) {
	
	$.ajax({
		url : '/ourbox/InsertMemoController',
		data : newMemo,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			window.opener.location.reload();
			window.close();
		
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
}