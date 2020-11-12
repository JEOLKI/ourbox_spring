package ourbox.common.vo;

public class AtchFileVO {

	private int atch_file_seq;
	private String atch_file_name;
	private String atch_file_path;

	public AtchFileVO() {

	}

	public AtchFileVO(String atch_file_name, String atch_file_path) {
		this.atch_file_name = atch_file_name;
		this.atch_file_path = atch_file_path;

	}

	public int getAtch_file_seq() {
		return atch_file_seq;
	}

	public void setAtch_file_seq(int atch_file_seq) {
		this.atch_file_seq = atch_file_seq;
	}

	public String getAtch_file_name() {
		return atch_file_name;
	}

	public void setAtch_file_name(String atch_file_name) {
		this.atch_file_name = atch_file_name;
	}

	public String getAtch_file_path() {
		return atch_file_path;
	}

	public void setAtch_file_path(String atch_file_path) {
		this.atch_file_path = atch_file_path;
	}

}
