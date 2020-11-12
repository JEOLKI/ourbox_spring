package ourbox.reply.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ourbox.common.vo.ReplyVO;
import ourbox.reply.service.IReplyService;

@Controller
public class ReplyController {

	@Resource(name = "replyService")
	private IReplyService replyService;

	@RequestMapping("/ReplyListController")
	public String replyList(int board_seq, Model model) {
		model.addAttribute("replyList", replyService.replyList(board_seq));
		return "jsonView";
	}

	@RequestMapping("/InsertReplyController")
	public String replyRegist(ReplyVO replyVO, Model model) {
		model.addAttribute("result", replyService.insertReply(replyVO));
		return "jsonView";
	}

	@RequestMapping("/DeleteReplyController")
	public String replyDelete(int reply_seq, Model model) {
		model.addAttribute("result", replyService.deleteReply(reply_seq));
		return "jsonView";
	}

	@RequestMapping("/UpdateReplyController")
	public String replyUpdate(ReplyVO replyVO, Model model) {
		model.addAttribute("result", replyService.updateReply(replyVO));
		return "jsonView";

	}

}
