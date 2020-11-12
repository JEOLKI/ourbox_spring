package ourbox.reply.service;

import java.sql.SQLException;
import java.util.List;

import ourbox.common.vo.ReplyVO;
import ourbox.reply.dao.IReplyDao;
import ourbox.reply.dao.ReplyDaoImpl;

public class ReplyServiceImpl implements IReplyService {
	
	private IReplyDao replyDao;
	
	private static IReplyService replyService;
	
	private ReplyServiceImpl() {
		replyDao = ReplyDaoImpl.getInstance();
	}
	
	public static IReplyService getInstance() {
		if (replyService == null) replyService = new ReplyServiceImpl();
		
		return replyService;
	}

	@Override
	public List<ReplyVO> replyList(int board_seq) {
		
		List<ReplyVO> replyList = null;
		
		try {
			replyList = replyDao.replyList(board_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return replyList;
	}

	@Override
	public int insertReply(ReplyVO reply) {
		int count = 0;
		
		try {
			count = replyDao.insertReply(reply);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int deleteReply(int reply_seq) {
		int count = 0;
		
		try {
			count = replyDao.deleteReply(reply_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int updateReply(ReplyVO reply) {
		int count = 0;
		
		try {
			count = replyDao.updateReply(reply);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
