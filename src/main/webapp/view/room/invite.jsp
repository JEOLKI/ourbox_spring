<%@page import="ourbox.common.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberVO> otherList = (List<MemberVO>)request.getAttribute("otherList");
	String mem_id = (String)request.getAttribute("memId");
	int room_seq = (Integer)request.getAttribute("roomSeq");
%>
<script>
	$(function(){
		$('.someOne').on('click',function(event){
			otherId = $(this).attr('other');
			roomSeq = $('#roomSeq').val();
			event.preventDefault(); //
			$.ajax({
				url:'/ourbox/MemberInviteController',
				type:'get',
				data:{
					"other" : otherId,
					"roomSeq" : roomSeq
				},
				success : function(res){
					opener.document.location.reload();
					self.close();
				},
				error : function(xhr){
					alert("초대에 실패했습니다." + xhr.status);
				}
			})
			
		})
	})    
</script>
<style>
	span{
		display: inline-block;
		width : 150px;
	}
	#btn{
		text-align: right;
	}
</style>
	<input type="hidden" id ="roomSeq" name ="roomSeq" value="<%=room_seq%>" >
<%
		if(otherList.size()>0){
			for(int i=0; i<otherList.size();i++){
				if(!(otherList.get(i).getMem_id().equals(mem_id))){
%>			
			<div class='di2'>
			<input id="someOneId" class="others" type="hidden" value ="<%=otherList.get(i).getMem_id()%>" name = "otherId" >&nbsp;<%=otherList.get(i).getMem_id() %>
			<input class ="someOne" type="button" other ="<%=otherList.get(i).getMem_id() %>" value="초대"></div>
<%
				}
			}
		} else { // 검색된 회원 정보가 없을 경우
%>
		<div>
			일치하는 회원이 존재하지 않습니다.
		</div>
<%
		}
%>	
