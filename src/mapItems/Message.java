package mapItems;

public class Message {
	private DataType type;
	private String msg;
	private Object o;
	
	
	public Message(String msg, DataType type, Object o) {
		this.type = type;
		this.msg = msg;
		this.o = o;
	}


	public DataType getType() {
		return type;
	}


	public void setType(DataType type) {
		this.type = type;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Object getO() {
		return o;
	}


	public void setO(Object o) {
		this.o = o;
	}
	
	
}
