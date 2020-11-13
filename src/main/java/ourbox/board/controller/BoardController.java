package ourbox.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ourbox.board.service.IBoardService;
import ourbox.common.util.FileUploadUtil;
import ourbox.common.vo.AtchFileVO;
import ourbox.common.vo.BoardVO;

@RequestMapping("/board")
@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Resource(name = "boardService")
	private IBoardService boardService;

	@RequestMapping("/view")
	public String getView() {
		return "board/boardMain";
	}

	@RequestMapping("/list")
	public String memberList(@RequestParam(name = "page", defaultValue = "1", required = false) int page, int room_seq,
			Model model) {

		int perlist = 10; // 페이지당 출력개수

		// 메소드 호출하기
		int totalcount = boardService.getTotalCount(room_seq);

		// 전체 페이지수
		int totalpage = (int) (Math.ceil((double) totalcount / perlist)); // 올림공식

		int perblock = 5; // 한화면에 표현되는 페이지수
		int startpage = ((page - 1) / perblock * perblock) + 1;
		int endpage = startpage + perblock - 1;
		if (endpage > totalpage)
			endpage = totalpage;

		// map에 설정하기
		int start = (page - 1) * perlist + 1;
		int end = start + perlist - 1;
		if (end > totalcount)
			end = totalcount;

		Map<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("room_seq", room_seq);

		// 페이지별 리스트 메소드 호출하기
		List<BoardVO> list = boardService.selectPage(map);

		// request에 결과 저장하기
		model.addAttribute("boardList", list);
		model.addAttribute("spage", startpage);
		model.addAttribute("epage", endpage);
		model.addAttribute("tpage", totalpage);
		model.addAttribute("cpage", page);

		return "jsonView";
	}

	@RequestMapping("/Detail")
	public String boardDetail(int board_seq, String mem_id, Model model) {
		Map<String, Object> map = boardService.detailBoard(board_seq);

		model.addAttribute("detailBoard", map.get("detailBoard"));
		model.addAttribute("atchFile", map.get("atchFile"));
		model.addAttribute("logId", mem_id);

		return "board/detailBoardFile";
	}

	@RequestMapping("/fileDownload")
	public String fildDownload(int atch_file_seq, Model model) {

		// db에서 사용자 filename 확인하고
		AtchFileVO atchFileVO = boardService.selectAtchFile(atch_file_seq);

		model.addAttribute("filepath", atchFileVO.getAtch_file_path());
		model.addAttribute("filename", atchFileVO.getAtch_file_name());

		return "DownloadView";
	}

	@RequestMapping(path = "/insert", method = { RequestMethod.GET })
	public String boardInsert() {
		return "board/insertBoardForm";
	}

	@RequestMapping(path = "/insert", method = { RequestMethod.POST })
	public String boardInsesrt(BoardVO boardVO, MultipartFile file, Model model) {

		logger.debug("boardVO : {}", boardVO);

		String filePath = "";
		String realFilename = "";
		if (file.getSize() > 0) {
			String filename = UUID.randomUUID().toString();
			realFilename = file.getOriginalFilename();
			String extension = FileUploadUtil.getExtension(realFilename);
			File uploadFile = new File("D:\\attachment\\" + filename + "." + extension);
			filePath = "D:\\attachment\\" + filename + "." + extension;
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		AtchFileVO atchFileVO = new AtchFileVO(realFilename, filePath);

		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("atchFileVO", atchFileVO);
		map.put("boardVO", boardVO);

		int insertCnt = 0;
		try {
			insertCnt = boardService.insertBoard(map);
			
			// 1건이 입력되었을 때 : 정상 - memberList 페이지로 이동
			if (insertCnt > 0) {
				return "redirect:/board/Detail?board_seq=" + boardVO.getBoard_seq() +"&mem_id="+ boardVO.getMem_id();
			}
		} catch (Exception e) {
		}

		// 1건이 아닐때 : 비정상
		return "board/insertBoardForm";
	}

	@RequestMapping(path = "/update", method = { RequestMethod.GET })
	public String boardUpdate(int board_seq, Model model) {
		Map<String, Object> map = boardService.detailBoard(board_seq);
		model.addAttribute("boardVO", (BoardVO) map.get("detailBoard"));
		logger.debug("boardVO : {}", (BoardVO) map.get("detailBoard"));
		return "board/updateBoardForm";
	}

	@RequestMapping(path = "/update", method = { RequestMethod.POST })
	public String boardUpdate(BoardVO boardVO, MultipartFile file, Model model) {
		logger.debug("boardVO : {}", boardVO);
		
		AtchFileVO atchFileVO = null;
		if( file.getSize() > 0 ) {
			String realFilename= "";
			String filePath ="";
			realFilename = file.getOriginalFilename();
			String filename = UUID.randomUUID().toString();
			String extension = FileUploadUtil.getExtension(realFilename);
			File uploadFile = new File("D:\\attachment\\" + filename + "." + extension);
			filePath = "D:\\attachment\\" + filename + "." + extension;

			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			atchFileVO = new AtchFileVO(realFilename, filePath);
		} else {
			Map<String, Object> dbmap = boardService.detailBoard(boardVO.getBoard_seq());
			atchFileVO = (AtchFileVO) dbmap.get("atchFile");
		}
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("atchFileVO", atchFileVO);
		map.put("boardVO", boardVO);
		
		int updateCnt = 0;
		try {
			updateCnt = boardService.updateBoard(map);

			// 1건이 입력되었을 때 : 정상 - memberList 페이지로 이동
			if (updateCnt == 1) {
				model.addAttribute("board_seq", boardVO.getBoard_seq());
				model.addAttribute("mem_id", boardVO.getMem_id());
				return "redirect:/board/Detail";
			}
		} catch (Exception e) {
		}
		
		// 1건이 아닐때 : 비정상
		return "board/update?board_seq=" + boardVO.getBoard_seq();
	}

	@RequestMapping(path = "/delete")
	public String boardDelete(int board_seq, Model model) {
		model.addAttribute("result", boardService.deleteBoard(board_seq));
		return "jsonView";
	}

	@RequestMapping(path = "/search")
	public String boardSearch(String searchOption, String searchKeyWord, Model model) {

		// board객체에 정보 저장하기
		BoardVO board = new BoardVO();

		if (searchOption.equals("글제목만")) {
			board.setBoard_title(searchKeyWord);
		} else if (searchOption.equals("글내용만")) {
			board.setBoard_content(searchKeyWord);
		} else if (searchOption.equals("글제목 + 내용")) {
			board.setBoard_title(searchKeyWord);
			board.setBoard_content(searchKeyWord);
		} else if (searchOption.equals("작성자")) {
			board.setMem_id(searchKeyWord);
		}

		List<BoardVO> boardList = boardService.searchBoard(board);
		model.addAttribute("boardList", boardList);
		return "jsonView";

	}

}
