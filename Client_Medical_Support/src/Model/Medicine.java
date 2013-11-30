package Model;

import java.io.Serializable;

public class Medicine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -247802876868789419L;
	
	int id;
	private String name;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}