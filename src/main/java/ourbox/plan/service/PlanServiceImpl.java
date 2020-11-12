package ourbox.plan.service;

import java.sql.SQLException;
import java.util.List;

import ourbox.common.vo.MemberVO;
import ourbox.common.vo.PlanUseVO;
import ourbox.common.vo.PlanVO;
import ourbox.common.vo.UseVO;
import ourbox.plan.dao.IPlanDao;
import ourbox.plan.dao.PlanDaoImpl;

public class PlanServiceImpl implements IPlanService {
	
	private static IPlanService planService;
	
	private IPlanDao planDao;
	
	private PlanServiceImpl() {
		planDao = PlanDaoImpl.getInstance();
	}
	
	public static IPlanService getInstance() {
		if(planService == null) {
			planService = new PlanServiceImpl();
		}
		return planService;
	}

	@Override
	public List<PlanVO> planList() {
		List<PlanVO> planList = null;
		try {
			planList = planDao.planList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planList;
	}

	@Override
	public int insertPlan(PlanVO plan) {
		int cnt = 0;
		try {
			cnt = planDao.insertPlan(plan);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deletePlan(int planSeq) {
		int cnt = 0;
		try {
			cnt = planDao.deletePlan(planSeq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<PlanUseVO> selectPlan(String mem_id) {
		List<PlanUseVO> planuseList = null;
		try {
			planuseList = planDao.selectPlan(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planuseList;
	}

	@Override
	public PlanUseVO getPlan(String mem_id) {
		PlanUseVO mv = null;
		try {
			mv = planDao.getPlan(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Override
	public int endPlan(String mem_id) {
		int cnt = 0;
		try {
			cnt = planDao.endPlan(mem_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public PlanVO getPlanManager(int plan_seq) {
		PlanVO pu = null;
		try {
			pu = planDao.getPlanManager(plan_seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pu;
	}

	@Override
	public int payPlan(UseVO uv) {
		int cnt = 0;
		try {
			cnt = planDao.payPlan(uv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	
}
