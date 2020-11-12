package ourbox.common.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="boardVO")
public class BoardVO {

	private int board_seq;
	private String board_title;
	private String board_date;
	private String board_content;
	private int room_seq;
	private int atch_file_seq;
	private String mem_id;
	private String board_status;

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getRoom_seq() {
		return room_seq;
	}

	public void setRoom_seq(int room_seq) {
		this.room_seq = room_seq;
	}

	public int getAtch_file_seq() {
		return atch_file_seq;
	}

	public void setAtch_file_seq(int atch_file_seq) {
		this.atch_file_seq = atch_file_seq;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getBoard_status() {
		return board_status;
	}

	public void setBoard_status(String board_status) {
		this.board_status = board_status;
	}

	@Override
	public String toString() {
		return "BoardVO [board_seq=" + board_seq + ", board_title=" + board_title + ", board_date=" + board_date
				+ ", board_content=" + board_content + ", room_seq=" + room_seq + ", atch_file_seq=" + atch_file_seq
				+ ", mem_id=" + mem_id + ", board_status=" + board_status + "]";
	}

}
