package Model;

import java.io.Serializable;
import java.util.Vector;

public class DetectedResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7051345741219364233L;
	private Vector<Laborator> abNormals;
	private Vector<LaboratorForm> nearLaboratorForms;
	private String disease;
	public Vector<Laborator> getAbNormals() {
		return abNormals;
	}
	public void setAbNormals(Vector<Laborator> abNormals) {
		this.abNormals = abNormals;
	}
	public Vector<LaboratorForm> getNearLaboratorForms() {
		return nearLaboratorForms;
	}
	public void setNearLaboratorForms(Vector<LaboratorForm> nearLaboratorForms) {
		this.nearLaboratorForms = nearLaboratorForms;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	
}
