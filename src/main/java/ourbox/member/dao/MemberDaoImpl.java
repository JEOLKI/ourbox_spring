package ourbox.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.EnterVO;
import ourbox.common.vo.MemberVO;
import ourbox.common.vo.PlanVO;


@Repository("memberRepository")
public class MemberDaoImpl implements IMemberDao {
	
	private SqlMapClient smc;
	
	private static IMemberDao memberDao;
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberDao getInstance() {
		if(memberDao == null) {
			memberDao = new MemberDaoImpl();
		}
		return memberDao;
	}

	
	/**
	 * DB의 member테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	@Override
	public List<MemberVO> memberList() throws SQLException {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		memList = smc.queryForList("member.memberList");
		return memList;
	}

	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param member DB에 insert할 자료가 저장된 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	@Override
	public int insertMember(MemberVO member) throws SQLException {
		Object obj = smc.insert("member.insertMember",member);
		int cnt = 0;
		if(obj==null) {
			cnt = 1;
		}
		return cnt;
	}

	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 검색할 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	@Override
	public MemberVO IdCheck(String mem_id) throws SQLException {
		MemberVO mv = null;
		mv = (MemberVO) smc.queryForObject("member.getMember",mem_id);
		return mv;
	}

	/**
	 * 하나의 MemberVO 자료를 이용하여 DB를 update하는 메서드
	 * @param member update할 회원 정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	@Override
	public int updateMember(MemberVO member) throws SQLException {
		int cnt = 0;
		cnt = smc.update("member.updateMember",member);
		return cnt;

	}

	/**
	 * 하나의 mem_id 자료를 이용하여  그 회원 상태를 탈퇴로 변경하는 메서드
	 * @param mem_id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	@Override
	public int deleteMember(String mem_id) throws SQLException {
		int cnt = 0;
		cnt = smc.update("member.deleteMember",mem_id);
		return cnt;
	}

	/**
	 * PlanVO에 담긴 자료를 이용하여 요금제별 회원을 검색하는 메서드
	 * @param member 검색할 자료가 들어있는 MemberVO객체
	 * @return 검색한 결과를 담은  List객체
	 */
	@Override
	public List<MemberVO> getMemberForPlanSeq(PlanVO plan) throws SQLException {
		List<MemberVO> memList= new ArrayList<>();
		memList = smc.queryForList("member.getMemberForPlanSeq", plan);
		return memList;
	}

	/**
	 * 회원ID에 해당하는 MemberVO객체를 반환하는 메서드
	 * @param memId 세부내용을 볼 회원ID
	 * @return boardNo에 해당하는 MemberVO 객체
	 */
	@Override
	public MemberVO detailMember(String mem_id) throws SQLException {
		MemberVO mv = null;
		mv = (MemberVO) smc.queryForObject("member.getMember",mem_id);
		return mv;
	}

	/**
	 * 회원ID,PASS에 해당하는 MemberVO객체를 반환하는 메서드
	 * @param member
	 * @return mem_id,mem_pass 에 해당하는 MemberVO 객체
	 */
	@Override
	public MemberVO loginMember(MemberVO member) throws SQLException {
		MemberVO mv = null;
		mv = (MemberVO) smc.queryForObject("member.loginMember",member);
		return mv;
	}


	@Override
	public MemberVO findId(MemberVO mv) throws SQLException {
		MemberVO vo = null;
		vo = (MemberVO) smc.queryForObject("member.findId", mv);
		return vo;
	}

	@Override
	public MemberVO findPw(MemberVO mv) throws SQLException {
		MemberVO vo = null;
		vo = (MemberVO) smc.queryForObject("member.findPw", mv);
		return vo;
	}


	@Override
	public int updatePw(MemberVO mv) throws SQLException {
		return smc.update("member.updatePw", mv);
	}

	@Override
	public List<MemberVO> searchMember(EnterVO ev) throws SQLException {
		return smc.queryForList("member.searchMember",ev);
	}

	@Override
	public int enterGroup(EnterVO ev) throws SQLException {
		Object obj = smc.insert("member.enterGroup",ev);
		int cnt = 0;
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}
	
	@Override
	public List<MemberVO> memberselectPlan(int planseq) throws SQLException {
	   List<MemberVO> memList = new ArrayList<MemberVO>();
	   memList = smc.queryForList("member.memberselectPlan",planseq);
	   return memList;
	}

	@Override
	public int profile(MemberVO mv) throws SQLException {
	   int cnt = 0;
	   cnt = smc.update("member.profile", mv);
	   return cnt;
	}
	
	
	@Override
	public List<MemberVO> chatMemProfile(int roomseq) throws SQLException {
	   List<MemberVO> memList = new ArrayList<MemberVO>();
	   memList = smc.queryForList("member.chatMemProfile",roomseq);
	   return memList;
	}

//	@Override
//	public ManagerVO adminCheck(ManagerVO mv) throws SQLException {
//		ManagerVO admin = null;
//		admin = (ManagerVO) smc.queryForObject("member.adminCheck", mv);
//		return admin;
//	}

 
	
}
