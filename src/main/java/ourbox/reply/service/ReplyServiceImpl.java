package ourbox.reply.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ourbox.common.vo.ReplyVO;
import ourbox.reply.dao.IReplyDao;

@Service("replyService")
public class ReplyServiceImpl implements IReplyService {
	
	@Resource(name = "replyRepository")
	private IReplyDao replyDao;
	
	@Override
	public List<ReplyVO> replyList(int board_seq) {
		return replyDao.replyList(board_seq);
	}

	@Override
	public int insertReply(ReplyVO reply) {
		return replyDao.insertReply(reply);
	}

	@Override
	public int deleteReply(int reply_seq) {
		return replyDao.deleteReply(reply_seq);
	}

	@Override
	public int updateReply(ReplyVO reply) {
		return replyDao.updateReply(reply);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
