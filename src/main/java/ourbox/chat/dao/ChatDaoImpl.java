package ourbox.chat.dao;

import java.sql.SQLException;

import javax.websocket.Session;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.ChatVO;

public class ChatDaoImpl implements IChatDao {

	private SqlMapClient smc;
	private static IChatDao chatDao;
	
	private ChatDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IChatDao getInstance() {
		if(chatDao == null) {
			chatDao = new ChatDaoImpl();
		}
		return chatDao;
	}

	@Override
	public int insertChat(ChatVO chat) throws SQLException {
		return (Integer) smc.insert("chat.insertChat", chat);
	}

	
	
	@Override
	public String getNickName(String mem_id) throws SQLException {
		return (String) smc.queryForObject("chat.getNickName", mem_id);
	}
//
//	@Override
//	public ChatVO loginMember(ChatVO chat) throws SQLException {
//		return (ChatVO) smc.queryForObject("chat.loginMember", chat);
//	}
}
