package Model;

import java.io.Serializable;

public class LaboratorForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5526078621361372253L;
	private Patient pantient;
//	private Vector<MedicineForm> medicineForms;
//	private Vector<Laborator> laborators;
	
	private String result;
	private int count,id;
	public LaboratorForm() {
//		medicineForms = new Vector<>();
//		laborators = new Vector<>();
	}

	public Patient getPantient() {
		return pantient;
	}
	public void setPantient(Patient pantient) {
		this.pantient = pantient;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
