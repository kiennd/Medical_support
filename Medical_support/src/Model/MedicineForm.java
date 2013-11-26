package Model;

import java.io.Serializable;

public class MedicineForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -303136442736717063L;
	private Medicine medicine;
	private String quantity,description;
	private String id;

	
	
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
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
