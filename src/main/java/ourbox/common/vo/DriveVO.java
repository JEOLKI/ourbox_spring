package ourbox.common.vo;

import java.io.File;
import java.util.Date;

public class DriveVO{
	
	private int drive_seq;
	private String drive_name;
	private String drive_path;
	private int drive_size;
	private String drive_type;
	private String drive_status;
	private int room_seq;
	private Date drive_date;
	public int getDrive_seq() {
		return drive_seq;
	}
	public void setDrive_seq(int drive_seq) {
		this.drive_seq = drive_seq;
	}
	public String getDrive_name() {
		return drive_name;
	}
	public void setDrive_name(String drive_name) {
		this.drive_name = drive_name;
	}
	public String getDrive_path() {
		return drive_path;
	}
	public void setDrive_path(String drive_path) {
		this.drive_path = drive_path;
	}
	public int getDrive_size() {
		return drive_size;
	}
	public void setDrive_size(int drive_size) {
		this.drive_size = drive_size;
	}
	public String getDrive_type() {
		return drive_type;
	}
	public void setDrive_type(String drive_type) {
		this.drive_type = drive_type;
	}
	public int getRoom_seq() {
		return room_seq;
	}
	public void setRoom_seq(int room_seq) {
		this.room_seq = room_seq;
	}
	public Date getDrive_date() {
		return drive_date;
	}
	public void setDrive_date(Date drive_date) {
		this.drive_date = drive_date;
	}
	public String getDrive_status() {
		return drive_status;
	}
	public void setDrive_status(String drive_status) {
		this.drive_status = drive_status;
	}
	
	
}
