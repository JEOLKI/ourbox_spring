package ourbox.qna.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import ourbox.common.vo.QnaVO;

public interface IQnaDao {
	
	/**
	 * 해당하는 페이지의 QnA객체를 가져오는 메서드
	 * @param map 원하는 페이지 구성의 정보가 담긴 맵
	 * @return 해당하는 페이지의 QnA리스트
	 */
	public List<QnaVO> selectPage(Map<String, Integer> map) throws SQLException ;
	
	/**
	 * 전체 QnA의 갯수를 가져오는 메서드
	 * @return QnA의 갯수
	 */
	public int getTotalCount() throws SQLException;
	
	/**
	 * qnaSeq에 해당하는 QnaVO객체를 반환하는 메서드
	 * @param qnaSeq 세부내용을 볼 Qna의 번호 
	 * @return qnaSeq에 해당하는 QnaVO 객체
	 */
	public QnaVO detailQna(int qna_seq) throws SQLException ;
	
	/**
	 * QnaVO에 담긴 자료를 이용하여 게시글을 검색하는 메서드
	 * @param qna 검색할 자료가 들어있는 QnaVO 객체
	 * @return 검색된 QnaVO객체를 담은 List객체
	 */
	public List<QnaVO> searchQna(QnaVO qna) throws SQLException ;
	
	
	/**
	 * 회원 아이디에 해당하는 Qna목록을 가져오는 메서드
	 * @param mem_id Qna의 작성자를 확인하기위한  회원아이디
	 * @return 해당하는 회원의 qna 목록
	 */
	public List<QnaVO> myQnaList(String mem_id) throws SQLException;
	
	/**
	 * QnaVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param qna DB에 insert할 자료가 저장된 QnaVO 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertQna(QnaVO qna) throws SQLException;
	
	
	/**
	 * QnA 번호를 매개변수로 받아서 그 QnA삭제하는 메서드
	 * @param QnaVO 삭제할 게시글의 번호
	 * @return 작업성공 : 1, 작업실패 : 0;
	 */
	public int deleteQna(int qna_seq) throws SQLException;
	
	/**
	 * 하나의 QnaVO 자료를 이용하여 DB를 update하는 메서드
	 * @param qna update할 게시글의 정보가 들어 있는 QnaVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateQna(QnaVO qna)  throws SQLException;
	
	
}
