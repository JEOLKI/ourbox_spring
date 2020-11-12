package ourbox.reply.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.ReplyVO;

@Repository("replyRepository")
public class ReplyDaoImpl implements IReplyDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ReplyVO> replyList(int board_seq) {
		return sqlSession.selectList("reply.replyList", board_seq);
	}

	@Override
	public int insertReply(ReplyVO reply) {
		return sqlSession.insert("reply.insertReply", reply);
	}

	@Override
	public int deleteReply(int reply_seq) {
		return sqlSession.delete("reply.deleteReply", reply_seq);
	}

	@Override
	public int updateReply(ReplyVO reply) {
		return sqlSession.update("reply.updateReply", reply);
	}

}
