package ourbox.member.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@RequestMapping("/member")
@Controller
public class MemberController {

	@Resource(name = "memberService")
	private IMemberService memberService;

	@RequestMapping("/profile")
	public String profileView(String mem_id, Model model) {

		// 메소드 호출하기
		MemberVO member = memberService.detailMember(mem_id);

		model.addAttribute("member", member);

		return "member/profile";
	}

	@RequestMapping("/chatMemberList")
	public String chatMemberList(int roomSeq, Model model) {

		List<MemberVO> list = memberService.chatMemProfile(roomSeq);
			
		model.addAttribute("list", list);
		
		return "chat/chatMemberList";

	}

}
