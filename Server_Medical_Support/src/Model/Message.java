package Model;

import java.io.Serializable;

public class Message implements Serializable{
	private int type;
	private Object obj;
	private String receipent;
	private String sender;
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getReceipent() {
		return receipent;
	}
	public void setReceipent(String receipent) {
		this.receipent = receipent;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Message(int type, Object obj, String receipent, String sender) {
		super();
		this.type = type;
		this.obj = obj;
		this.receipent = receipent;
		this.sender = sender;
	}
	public Message(int type, Object obj) {
		super();
		this.type = type;
		this.obj = obj;
	}
	

	
	
}
