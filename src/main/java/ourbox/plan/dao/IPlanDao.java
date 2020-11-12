package ourbox.plan.dao;

import java.sql.SQLException;
import java.util.List;

import ourbox.common.vo.PlanUseVO;
import ourbox.common.vo.PlanVO;
import ourbox.common.vo.UseVO;

public interface IPlanDao {

	/**
	 * DB의 plan테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return PlanVO객체를 담고 있는 List객체
	 */
	public List<PlanVO> planList() throws SQLException;
	
	/**
	 * PlanVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param plan DB에 insert할 자료가 저장된 PlanVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertPlan(PlanVO plan) throws SQLException;
	
	
	/**
	 * 요금제 번호를 매개변수로 받아서  그 요금제 정보를 삭제하는 메서드
	 * @param planSeq 삭제할 요금제 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deletePlan(int planSeq) throws SQLException;
	
	/**
	 * 로그인된 회원의 이용중인 요금제 조회하는 메서드
	 * @param mem_id
	 * @return PlanUseVO
	 */
	public List<PlanUseVO> selectPlan(String mem_id) throws SQLException;

	
	/**
	 * 해당 회원이 요금제를 이용하고 있는지 확인하는 메서드
	 * @param mem_id
	 * @return PlanUseVO
	 */
	public PlanUseVO getPlan(String mem_id) throws SQLException;
	
	/**
	 * 요금제 해지하는 메서드
	 * @param mem_id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int endPlan(String mem_id) throws SQLException;
	
	/**
	 * 해당 요금제 번호가 있는지 확인하는 메서드
	 * @param plan_seq
	 * @return
	 */
	public PlanVO getPlanManager(int plan_seq) throws SQLException;
	
	/**
	 * 요금제 결제하는 메서드
	 * @param uv
	 * @return
	 */
	public int payPlan(UseVO uv) throws SQLException;

}
