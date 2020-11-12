package ourbox.plan.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourbox.common.util.SqlMapClientFactory;
import ourbox.common.vo.MemberVO;
import ourbox.common.vo.PlanUseVO;
import ourbox.common.vo.PlanVO;
import ourbox.common.vo.UseVO;

public class PlanDaoImpl implements IPlanDao {
	private SqlMapClient smc;
	
	private static IPlanDao planDao;

	private PlanDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IPlanDao getInstance() {
		if(planDao == null) {
			planDao = new PlanDaoImpl();
		}
		return planDao;
	}

	@Override
	public List<PlanVO> planList() throws SQLException {
		
		return smc.queryForList("plan.planlist");
	}

	@Override
	public int insertPlan(PlanVO plan) throws SQLException {
		Object obj = smc.insert("plan.insertPlan",plan);
		int cnt = 0;
		if(obj==null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int deletePlan(int planSeq) throws SQLException {
		int cnt = 0;
		cnt = smc.update("plan.deletePlan",planSeq);
		return cnt;
	}

	@Override
	public List<PlanUseVO> selectPlan(String mem_id) throws SQLException {
		return smc.queryForList("planuse.selectPlan",mem_id);
	}

	@Override
	public PlanUseVO getPlan(String mem_id) throws SQLException {
		PlanUseVO pu = null;
		pu = (PlanUseVO) smc.queryForObject("planuse.getPlan",mem_id);
		return pu;
	}

	@Override
	public int endPlan(String mem_id) throws SQLException {
		return smc.update("planuse.endPlan",mem_id);
	}

	@Override
	public PlanVO getPlanManager(int plan_seq) throws SQLException {
		PlanVO pu = null;
		pu = (PlanVO) smc.queryForObject("plan.getPlanManager",plan_seq);
		return pu;
	}

	@Override
	public int payPlan(UseVO uv) throws SQLException {
		System.out.println(uv.getPlan_seq());
		System.out.println(uv.getMem_id());
		Object obj = smc.insert("planuse.payPlan",uv);
		int cnt = 0;
		if(obj==null) {
			cnt = 1;
		}
		return cnt;
	}

}
