package ourbox.member.dao;

import java.sql.SQLException;
import java.util.List;

import ourbox.common.vo.EnterVO;
import ourbox.common.vo.MemberVO;
import ourbox.common.vo.PlanVO;

public interface IMemberDao {

	/**
	 * DB의 member테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> memberList() throws SQLException;
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param member DB에 insert할 자료가 저장된 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertMember(MemberVO member) throws SQLException;
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param mem_id 검색할 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public MemberVO IdCheck(String mem_id) throws SQLException;
	
	/**
	 * 하나의 MemberVO 자료를 이용하여 DB를 update하는 메서드
	 * @param member update할 회원 정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO member) throws SQLException;
	
	/**
	 * 하나의 mem_id 자료를 이용하여  그 회원 상태를 탈퇴로 변경하는 메서드
	 * @param mem_id 
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMember(String mem_id) throws SQLException;
	
	/**
	 * PlanVO에 담긴 자료를 이용하여 요금제별 회원을 검색하는 메서드
	 * @param member 검색할 자료가 들어있는 MemberVO객체
	 * @return 검색한 결과를 담은  List객체
	 */
	public List<MemberVO> getMemberForPlanSeq(PlanVO plan) throws SQLException;
	
	/**
	 * 회원ID에 해당하는 MemberVO객체를 반환하는 메서드
	 * @param mem_id 세부내용을 볼 회원ID
	 * @return mem_id 에 해당하는 MemberVO 객체
	 */
	public MemberVO detailMember(String mem_id) throws SQLException;
	
	/**
	 * 입력한 ID와 비밀번호에 해당하는 회원을 반환하는 메서드
	 * @param mem_id
	 * @param mem_pass
	 * @return mem_id,mem_pass에 해당하는 MemberVO 객체
	 */
	public MemberVO loginMember(MemberVO mv) throws SQLException;
	
	/**
	 * 아이디 찾기 메서드
	 * @param mv
	 * @return
	 */
	public MemberVO findId(MemberVO mv) throws SQLException;
	
	/**
	 * 비밀번호 찾기 메서드
	 * @param mv
	 * @return
	 */
	public MemberVO findPw(MemberVO mv) throws SQLException;
	
	
	/**
	 * 비밀번호 변경 메서드
	 * @param mv
	 * @return
	 */
	public int updatePw(MemberVO mv) throws SQLException;
	
	/**
	 * 아이디검색에 해당하는 아이디 모두 출력
	 * @param EnterVO ev
	 * @return
	 */
	public List<MemberVO> searchMember(EnterVO ev) throws SQLException;
	
	/**
	 * enter를 생성시켜 회원을 입력하는 메서드
	 * @param ev
	 * @return
	 */
	public int enterGroup(EnterVO ev) throws SQLException;
	
	/**
	 * 선택한 요금제 기준으로 회원리스트를 출력하는 메서드
	 * @param planseq
	 * @return
	 */
	public List<MemberVO> memberselectPlan(int planseq) throws SQLException;
	
	/**
	 * 프로필 업로드 하는 메서드
	 * @param mem_id
	 * @param fileName
	 * @return
	 * @throws SQLException
	 */
	public int profile(MemberVO mv) throws SQLException;
	
	
	/**
	 * 해당 그룹의 사용자들의 채팅에서 프로필을 보여주는 메서드
	 * @param roomseq
	 * @return
	 */
	public List<MemberVO> chatMemProfile(int roomseq) throws SQLException;
	
//	/**
//	 * 로그인 시 관리자를 구분하기 위한 메서드
//	 * @param mv
//	 * @return ManagerVO
//	 */
//	public ManagerVO adminCheck(ManagerVO mv) throws SQLException;
	
	
	

}
