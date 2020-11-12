package ourbox.chat.dao;

import java.sql.SQLException;

import javax.websocket.Session;

import ourbox.common.vo.ChatVO;

public interface IChatDao {
//	
//	/**
//	 * zz
//	 * @param message
//	 * @param session
//	 * @throws SQLException
//	 */
	public String getNickName(String mem_id) throws SQLException;

	public int insertChat(ChatVO chat) throws SQLException;
	
//	public ChatVO loginMember(ChatVO chat) throws SQLException;
	
}
