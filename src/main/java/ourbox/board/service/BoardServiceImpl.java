package ourbox.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import ourbox.board.dao.IBoardDao;
import ourbox.common.vo.AtchFileVO;
import ourbox.common.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements IBoardService {

	
	@Resource(name="boardRepository")
	private IBoardDao boardDao;
	
	public static IBoardService getInstance() {
		return new BoardServiceImpl();
	}

	@Override
	public List<BoardVO> selectPage(Map<String, Integer> map) {
		return boardDao.selectPage(map);
	}

	@Override
	public int getTotalCount(int room_seq) {
		return boardDao.getTotalCount(room_seq);
	}
	
	@Override
	public Map<String , Object> detailBoard(int board_seq) {
		
		BoardVO detailBoard = boardDao.detailBoard(board_seq);
		
		AtchFileVO atchFile = boardDao.selectAtchFile(detailBoard.getAtch_file_seq());
		
		Map<String , Object> map = new HashedMap<String, Object>();
		map.put("detailBoard", detailBoard);
		map.put("atchFile", atchFile);
		
		return map;
	}
	
	@Override
	public int insertBoard(Map<String, Object> map) {
		
		int atch_file_seq = boardDao.insertAtchFile((AtchFileVO) map.get("atchFileVO"));
		
		BoardVO board = (BoardVO) map.get("boardVO");
		board.setAtch_file_seq(atch_file_seq);
		
		return boardDao.insertBoard(board);
	}
	
	@Override
	public List<BoardVO> searchBoard(BoardVO board) {
		return boardDao.searchBoard(board);
	}

	@Override
	public int deleteBoard(int board_seq) {
		return boardDao.deleteBoard(board_seq);
	}

	@Override
	public int updateBoard(BoardVO board) {
		return boardDao.updateBoard(board);
	}
	
	@Override
	public int insertAtchFile(AtchFileVO atchFile){
		return boardDao.insertAtchFile(atchFile);
	}

	@Override
	public AtchFileVO selectAtchFile(int atch_file_seq){
		return boardDao.selectAtchFile(atch_file_seq);
	}




	
	

}
