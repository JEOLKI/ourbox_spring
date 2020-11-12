package ourbox.memo.dao;

import java.sql.SQLException;
import java.util.List;

import ourbox.common.vo.MemoVO;

public interface IMemoDao {
	
	/**
	 * DB의 Memo테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return 전체 MemoVO객체를 담고 있는 List객체
	 */
	public List<MemoVO> memoList(String mem_id) throws SQLException;

	/**
	 * MemoVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param Memo DB에 insert할 자료가 저장된 MemoVO 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertMemo(MemoVO memo) throws SQLException;
	
	/**
	 * 메모 번호를 매개변수로 받아서 그 게시글을 삭제하는 메서드
	 * @param memoSeq 삭제할 게시글의 번호
	 * @return 작업성공 : 1, 작업실패 : 0;
	 */
	public int deleteMemo(int memo_seq) throws SQLException;
	
	/**
	 * 하나의 MemoVO 자료를 이용하여 DB를 update하는 메서드
	 * @param memo update할 게시글의 정보가 들어 있는 MemoVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMemo(MemoVO memo) throws SQLException;
	
	/**
	 * MemoVO에 담긴 자료를 이용하여 메모를 검색하는 메서드
	 * @param memo 검색할 자료가 들어있는 MemoVO 객체
	 * @return 검색된 MemoVO객체를 담은 List객체
	 */
	public List<MemoVO> searchMemo(MemoVO memo) throws SQLException;
	
	
}
