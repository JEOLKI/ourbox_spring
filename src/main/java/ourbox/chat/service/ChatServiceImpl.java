package ourbox.chat.service;

import java.sql.SQLException;


import ourbox.chat.dao.ChatDaoImpl;
import ourbox.chat.dao.IChatDao;
import ourbox.common.vo.ChatVO;

public class ChatServiceImpl implements IChatService {

	private static IChatService chatService;
	private IChatDao chatDao;
	
	private ChatServiceImpl() {
		chatDao = ChatDaoImpl.getInstance();
	}
	
	public static IChatService getInstance() {
		if(chatService == null) {
			chatService = new ChatServiceImpl();
		}
		return chatService;
	}

	@Override
	public int insertChat(ChatVO chat) {
		int cnt =0;
		try {
			cnt = chatDao.insertChat(chat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String getNickName(String mem_id) {
		String nickName = null;
		
		try {
			nickName = chatDao.getNickName(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nickName;
	}
//
//	@Override
//	public ChatVO loginMember(ChatVO chat) {
//		ChatVO vo = null;
//		try {
//			vo = chatDao.loginMember(chat);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return vo;
//	}

}
