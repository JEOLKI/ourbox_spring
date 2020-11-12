package ourbox.board.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import ourbox.common.vo.AtchFileVO;
import ourbox.common.vo.BoardVO;

@Repository("boardRepository")
public class BoardDaoImpl implements IBoardDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BoardVO> selectPage(Map<String, Integer> map) {
		return sqlSession.selectList("board.selectPage", map);
	}

	@Override
	public int getTotalCount(int room_seq) {
		return sqlSession.selectOne("board.getTotalCount", room_seq);
	}

	@Override
	public BoardVO detailBoard(int board_seq) {
		return sqlSession.selectOne("board.detailBoard", board_seq);
	}

	@Override
	public int insertBoard(BoardVO board) {
		sqlSession.insert("board.insertBoard", board);
		return board.getBoard_seq();
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO board) {
		return sqlSession.selectList("board.searchBoard", board);
	}

	@Override
	public int deleteBoard(int board_seq) {
		return sqlSession.delete("board.deleteBoard", board_seq);
	}

	@Override
	public int updateBoard(BoardVO board) {
		return sqlSession.update("board.updateBoard", board);
	}
	
	@Override
	public int insertAtchFile(AtchFileVO atchFile){
		sqlSession.insert("board.insertAtchFile", atchFile);
		return atchFile.getAtch_file_seq();
	}

	@Override
	public AtchFileVO selectAtchFile(int atch_file_seq){
		return sqlSession.selectOne("board.selectAtchFile", atch_file_seq);
	}

}
