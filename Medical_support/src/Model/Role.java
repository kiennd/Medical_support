package Model;

import java.io.Serializable;

public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3671260087241341662L;
	private int id;
	String role,description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Role(int id, String role, String description) {
		super();
		this.id = id;
		this.role = role;
		this.description = description;
	}
	public Role() {
	}
}
