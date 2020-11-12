package ourbox.board.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import ourbox.ModelTestConfig;


public class BoardDaoImplTest extends ModelTestConfig{
	
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@Test
	public void getTotalCountTest() {
		/***Given***/

		/***When***/
		int count = boardService.getTotalCount(296);

		/***Then***/
		assertEquals(15, count);
	}

}
