package ourbox.reply.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.ReplyVO;

public class ReplyDaoImpl implements IReplyDao {

	private SqlMapClient smc;
	private static IReplyDao replyDao;
	
	private ReplyDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IReplyDao getInstance() {
		if(replyDao == null) replyDao = new ReplyDaoImpl();
		
		return replyDao;
	}

	@Override
	public List<ReplyVO> replyList(int board_seq) throws SQLException {
		return smc.queryForList("reply.replyList", board_seq);
	}

	@Override
	public int insertReply(ReplyVO reply) throws SQLException {
		return (Integer) smc.insert("reply.insertReply", reply);
	}

	@Override
	public int deleteReply(int reply_seq) throws SQLException {
		return smc.delete("reply.deleteReply", reply_seq);
	}

	@Override
	public int updateReply(ReplyVO reply) throws SQLException {
		return smc.update("reply.updateReply", reply);
	}
	
	
}
