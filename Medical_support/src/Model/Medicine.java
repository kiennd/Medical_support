package Model;

import java.io.Serializable;

public class Medicine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -303136442736717063L;
	private int name;
	private String quantity,description;
	private String id;
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
