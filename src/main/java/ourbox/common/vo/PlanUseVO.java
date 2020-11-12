package ourbox.common.vo;

public class PlanUseVO {
	
	private int plan_seq;
	private int use_seq;
	private String plan_name;
	private String plan_content;
	private int plan_price;
	private String use_status;
	private int plan_sto_cap;
	private String use_start;
	private String mem_id;
	
	
	
	
	public String getUse_status() {
		return use_status;
	}
	public void setUse_status(String use_status) {
		this.use_status = use_status;
	}
	public int getPlan_seq() {
		return plan_seq;
	}
	public void setPlan_seq(int plan_seq) {
		this.plan_seq = plan_seq;
	}
	public int getUse_seq() {
		return use_seq;
	}
	public void setUse_seq(int use_seq) {
		this.use_seq = use_seq;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public String getPlan_content() {
		return plan_content;
	}
	public void setPlan_content(String plan_content) {
		this.plan_content = plan_content;
	}
	public int getPlan_price() {
		return plan_price;
	}
	public void setPlan_price(int plan_price) {
		this.plan_price = plan_price;
	}
	public int getPlan_sto_cap() {
		return plan_sto_cap;
	}
	public void setPlan_sto_cap(int plan_sto_cap) {
		this.plan_sto_cap = plan_sto_cap;
	}
//	public String getUse_end() {
//		return use_end;
//	}
//	public void setUse_end(String use_end) {
//		this.use_end = use_end;
//	}
	public String getMem_id() {
		return mem_id;
	}
	public String getUse_start() {
		return use_start;
	}
	public void setUse_start(String use_start) {
		this.use_start = use_start;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	

}
