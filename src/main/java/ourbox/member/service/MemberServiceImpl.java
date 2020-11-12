package ourbox.member.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ourbox.common.vo.EnterVO;
import ourbox.common.vo.MemberVO;
import ourbox.common.vo.PlanVO;
import ourbox.member.dao.IMemberDao;
import ourbox.member.dao.MemberDaoImpl;

@Service("memberService")
public class MemberServiceImpl implements IMemberService {

	@Resource(name = "memberRepository")
	private IMemberDao memberDao;
	
	private MemberServiceImpl() {
		
	}
	
	public static IMemberService getInstance() {
		return new MemberServiceImpl();
	}

	/**
	 * DB의 member테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	@Override
	public List<MemberVO> memberList() {
		List<MemberVO> memList = null;
		try {
			memList = memberDao.memberList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param member DB에 insert할 자료가 저장된 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	@Override
	public int insertMember(MemberVO member) {
		int cnt = 0;
		try {
			cnt = memberDao.insertMember(member);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param mem_id 검색할 회원ID
	 * @return 해당 회원ID가 있으면
	 */
	@Override
	public MemberVO IdCheck(String mem_id) {
		MemberVO mv = null;
		try {
			mv = memberDao.IdCheck(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv; 
	}
	/**
	 * 하나의 MemberVO 자료를 이용하여 DB를 update하는 메서드
	 * @param member update할 회원 정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	@Override
	public int updateMember(MemberVO member) {
		int cnt = 0;
		try {
			cnt = memberDao.updateMember(member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * 회원 ID를 매개변수로 받아서  그 회원 상태를 탈퇴로 변경하는 메서드
	 * @param mem_id 삭제할 회원 ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	@Override
	public int deleteMember(String mem_id) {
		int cnt =  0;
		try {
			cnt = memberDao.deleteMember(mem_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/**
	 * PlanVO에 담긴 자료를 이용하여 요금제별 회원을 검색하는 메서드
	 * @param member 검색할 자료가 들어있는 MemberVO객체
	 * @return 검색한 결과를 담은  List객체
	 */
	@Override
	public List<MemberVO> getMemberForPlanSeq(PlanVO plan) {
		List<MemberVO> memList = null;
		try {
			memList = memberDao.getMemberForPlanSeq(plan);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memList;
	}

	/**
	 * 회원ID에 해당하는 MemberVO객체를 반환하는 메서드
	 * @param mem_id 세부내용을 볼 회원ID
	 * @return boardNo에 해당하는 MemberVO 객체
	 */
	@Override
	public MemberVO detailMember(String mem_id) {
		MemberVO mv = null;
		try {
			mv = memberDao.detailMember(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 회원ID,PASS에 해당하는 MemberVO객체를 반환하는 메서드
	 * @param member
	 * @return mem_id,mem_pass 에 해당하는 MemberVO 객체
	 */
	@Override
	public MemberVO loginMember(MemberVO member){
		MemberVO mv = null;
		try {
			mv = memberDao.loginMember(member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;	
	}

	@Override
	public MemberVO findId(MemberVO mv) {
		MemberVO vo = null;
		try {
			vo = memberDao.findId(mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public MemberVO findPw(MemberVO mv) {
		MemberVO vo = null;
		try {
			vo = memberDao.findPw(mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}


	@Override
	public int updatePw(MemberVO mv) {
		int cnt =  0;
		try {
			cnt = memberDao.updatePw(mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> searchMember(EnterVO ev) {
		List<MemberVO> otherList = null;
		try {
			otherList = memberDao.searchMember(ev);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return otherList;
	}


	@Override
	public int enterGroup(EnterVO ev){
		int cnt = 0;
		try {
			cnt = memberDao.enterGroup(ev);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
		
		
	}
	
	
	@Override
	public List<MemberVO> memberselectPlan(int planseq) {
		List<MemberVO> memList = null;
		try {
			memList = memberDao.memberselectPlan(planseq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int profile(MemberVO mv) {
		int cnt =  0;
		try {
			cnt = memberDao.profile(mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> chatMemProfile(int roomseq) {
		List<MemberVO> memList = null;
		try {
			memList = memberDao.chatMemProfile(roomseq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

//	@Override
//	public ManagerVO adminCheck(ManagerVO mv) {
//		ManagerVO admin = null;
//		try {
//			admin = memberDao.adminCheck(mv);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return admin;
//	}


}
