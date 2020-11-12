package ourbox.chat.service;

import ourbox.common.vo.ChatVO;

public interface IChatService {
	
	public int insertChat(ChatVO chat);
	
	public String getNickName(String mem_id);
	
//	public ChatVO loginMember(ChatVO chat);
}
