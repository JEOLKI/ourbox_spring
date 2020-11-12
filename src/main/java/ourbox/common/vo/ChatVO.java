package ourbox.common.vo;

public class ChatVO {
		private int chat_seq;
		private String chat_content;
		private String chat_date;
		private String mem_id;
		private int room_seq;
		
		
		public int getChat_seq() {
			return chat_seq;
		}
		public void setChat_seq(int chat_seq) {
			this.chat_seq = chat_seq;
		}
		public String getChat_content() {
			return chat_content;
		}
		public void setChat_content(String chat_content) {
			this.chat_content = chat_content;
		}
		public String getChat_date() {
			return chat_date;
		}
		public void setChat_date(String chat_date) {
			this.chat_date = chat_date;
		}
		public String getMem_id() {
			return mem_id;
		}
		public void setMem_id(String mem_id) {
			this.mem_id = mem_id;
		}
		public int getRoom_seq() {
			return room_seq;
		}
		public void setRoom_seq(int room_seq) {
			this.room_seq = room_seq;
		}
}
