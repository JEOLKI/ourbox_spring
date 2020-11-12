package ourbox.reply.dao;

import java.sql.SQLException;
import java.util.List;

import ourbox.common.vo.ReplyVO;

public interface IReplyDao {

	/**
	 * 해당하는 번호의 게시글의 댓글을 가져오기 위한 메소드
	 * @param board_seq 댓글을 가져올 게시글의 번호
	 * @return 해당하는 번호의 게시글의 댓글목록
	 */
	public List<ReplyVO> replyList(int board_seq) throws SQLException;
	
	
	/**
	 * 새로운 reply 객체를 등록하기 위한 메소드
	 * @param reply 등록할 정보를 갖는 객체
	 * @return 작업성공 : 1 , 작업실패 : 0
	 */
	public int insertReply(ReplyVO reply) throws SQLException;
	
	
	/**
	 * 해당하는 번호의 댓글을 삭제하기 위한 메소드
	 * @param reply_seq 삭제할 댓글의 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteReply(int reply_seq) throws SQLException;
	
	
	/**
	 * 해당하는 번호의 댓글을 수정하기 위한 메소드 
	 * @param reply 수정할 정보를 담은 댓글 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateReply(ReplyVO reply) throws SQLException;
	
	
}
