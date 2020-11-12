package ourbox.answer.service;

import java.util.List;

import ourbox.common.vo.AnswerVO;


public interface IAnswerService {
	
	/**
	 * 해당하는 Qna의 답변의 목록을 가져오는 메서드
	 * @param qna_seq 답변을 볼 Qna의 번호
	 * @return 해당하는 qna 번호의 answer list
	 */
	public List<AnswerVO> answerList(int qna_seq);
	
	/**
	 * 해당하는 Qna에 답변을 등록하기 위한 메소드
	 * @param answer 답변의 정보를 담은 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertAnswer(AnswerVO answer);
	
	/**
	 * 해당하는 answer번호의 답변을 삭제하는 메소드
	 * @param ans_seq 삭제할 답변의 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteAnswer(int ans_seq);
	
	
	/**
	 * 답변을 수정하기위한 메소드
	 * @param answer 수정할 답변의 정보를 담은 객체
	 * @return 작업성공 : 1 , 작업실패 : 0
	 */
	public int updateAnswer(AnswerVO answer);
	
	
	
}
