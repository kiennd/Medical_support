package Model;

import java.io.Serializable;
import java.util.Vector;

public class LaboratorForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5526078621361372253L;
	private Patient pantient;
	private Vector<Medicine> medicines;
	private Vector<Laborator> laborators;
	private String result;
	private int count,id;
	
	
	public Vector<Laborator> getLaborators() {
		return laborators;
	}
	public void setLaborators(Vector<Laborator> laborators) {
		this.laborators = laborators;
	}
	public Patient getPantient() {
		return pantient;
	}
	public void setPantient(Patient pantient) {
		this.pantient = pantient;
	}
	public Vector<Medicine> getMedicines() {
		return medicines;
	}
	public void setMedicines(Vector<Medicine> medicines) {
		this.medicines = medicines;
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
