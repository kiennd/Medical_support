package BackendAction;

import java.rmi.RemoteException;
import java.util.Vector;

import Model.ConstantMedical;
import Model.DetectedResult;
import Model.Laborator;
import Model.LaboratorForm;

import com.opensymphony.xwork2.ActionSupport;


public class MedicalSupportAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3878294433506773345L;
	Vector<Laborator> laborators;
	Vector<String> laboratorName = new Vector<>();
	Vector<String> laboratorValue = new Vector<>();
	Vector<Laborator> abNormals;
	Vector<LaboratorForm> nearLaboratorForms;

	String disease;
	
	public MedicalSupportAction() {
		
		laborators = new Vector<>();
		for (int i = 0; i < ConstantMedical.LABORATOR_CATEGORY.length; i++) {
			Laborator la   = new Laborator();
			la.setName(ConstantMedical.LABORATOR_CATEGORY[i]);
			laborators.addElement(la);
		}
	}
	
	public String Detect(){
		
		System.out.println("abc");
		
		try {
			DetectedResult dr = new DetectedResult();
			
			laborators = generateLaborators();
			dr =  RMIConnector.getService().detectDisease(laborators);
	        this.disease = dr.getDisease();
	        this.abNormals = dr.getAbNormals();
	        this.nearLaboratorForms = dr.getNearLaboratorForms();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public Vector<Laborator> generateLaborators(){
		Vector<Laborator> res = new Vector<>();
		for (int i = 0; i < laboratorValue.size(); i++) {
			Laborator la = new Laborator();
			la.setName(laboratorName.get(i));
			if(laboratorValue.get(i).length()>0){
				la.setResult(Float.parseFloat(laboratorValue.get(i)));
			}else{
				la.setResult(Float.NaN);
			}
			res.add(la);
		}
		return res;
	}
	

	
	
	public float valueWithName(String name){
		return 0;
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

	public Vector<Laborator> getLaborators() {
		return laborators;
	}

	public void setLaborators(Vector<Laborator> laborators) {
		this.laborators = laborators;
	}

	
	public void setLaboratorName(String [] laboratorName){
		for (int i = 0; i < laboratorName.length; i++) {
			this.laboratorName.addElement(laboratorName[i]);
		}
	}
	public void setLaboratorValue(String [] laboratorValue){
		for (int i = 0; i < laboratorValue.length; i++) {
			this.laboratorValue.addElement(laboratorValue[i]);
		}
	}

	public Vector<Laborator> getAbNormals() {
		return abNormals;
	}

	public void setAbNormals(Vector<Laborator> abNormals) {
		this.abNormals = abNormals;
	}
	
	
}
