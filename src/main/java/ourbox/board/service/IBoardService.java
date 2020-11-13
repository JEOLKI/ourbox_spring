package ourbox.board.service;

import java.util.List;
import java.util.Map;

import ourbox.common.vo.AtchFileVO;
import ourbox.common.vo.BoardVO;


public interface IBoardService {
	
	/**
	 * 해당하는 페이지의 board객체를 가져오는 메서드
	 * @param map 원하는 페이지 구성의 정보가 담긴 맵
	 * @return 해당하는 페이지의 boardList
	 */
	public List<BoardVO> selectPage(Map<String, Integer> map);
	
	/**
	 * 전체 board의 갯수를 가져오는 메서드
	 * @return board의 갯수
	 */
	public int getTotalCount(int room_seq);
	
	/**
	 * boardNo에 해당하는 BoardVO객체를 반환하는 메서드
	 * @param boardNo 세부내용을 볼 board의 번호 
	 * @return boardNo에 해당하는 BoardVO 객체
	 */
	public Map<String , Object> detailBoard(int boardSeq);
	
	/**
	 * BoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param map DB에 insert할 자료가 저장된 BoardVO 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertBoard(Map<String, Object> map);
	
	/**
	 * BoardVO에 담긴 자료를 이용하여 게시글을 검색하는 메서드
	 * @param board 검색할 자료가 들어있는 BoardVO 객체
	 * @return 검색된 BoardVO객체를 담은 List객체
	 */
	public List<BoardVO> searchBoard(BoardVO board);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 그 게시글을 삭제하는 메서드
	 * @param boardNo 삭제할 게시글의 번호
	 * @return 작업성공 : 1, 작업실패 : 0;
	 */
	public int deleteBoard(int board_seq);
	
	
	/**
	 * 하나의 BoardVO 자료를 이용하여 DB를 update하는 메서드
	 * @param map update할 게시글의 정보가 들어 있는 BoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard(Map<String, Object> map);
	
	public int insertAtchFile(AtchFileVO atchFile);

	public AtchFileVO selectAtchFile(int atch_file_seq);

	

	
}
