package ourbox.login.controller;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ourbox.common.vo.MemberVO;
import ourbox.member.service.IMemberService;
import ourbox.member.service.MemberServiceImpl;

@RequestMapping("/login")
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "memberService")
	private IMemberService memberService;

	@RequestMapping("/view")
	public String getView() {
		return "login/view";
	}

	@RequestMapping("/process")
	public String process(String id, String pass, HttpSession session, Model model) {

		if (id.equals("ourbox") && pass.equals("ourbox")) {

			return "manager/view";

		} else {
			MemberVO memberVO = new MemberVO();
			memberVO.setMem_id(id);
			memberVO.setMem_pass(pass);

			MemberVO member = memberService.loginMember(memberVO);

			session.setAttribute("vo", member); // 아이디

			String msg = "";

			if (member != null && member.getMem_pass().equals(pass)) { // 올바르게 로그인
				return "member/ourboxmember";
				
			} else if (member == null) { // 아이디 또는 비밀번호 틀림
				
				msg = "아이디와 패스워드를 확인해주세요.";
				model.addAttribute("msg", msg);
				return "login/view";
			} else {
				msg = "탈퇴한 회원입니다.";
				model.addAttribute("msg", msg);
				return "login/view";
			}
		}

	}

}
