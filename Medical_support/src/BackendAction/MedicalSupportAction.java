package BackendAction;

import java.util.Vector;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import DAO.LaboratorDAO;
import Model.ConstantMedical;
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
			InstanceQuery query = new InstanceQuery();
			query.setUsername("root");
			query.setPassword(" ");
			StringBuffer q = new StringBuffer("select");
			for (int i = 0; i < ConstantMedical.LABORATOR_CATEGORY.length; i++) {
				q.append("`"+ConstantMedical.LABORATOR_CATEGORY[i]+"`,");
			}
			q.append("`result` from tbllaborator"
					+ " inner join tbllaboratorform on tbllaboratorform.patientid = tbllaborator.patientid"
					+ "	and tbllaboratorform.count = tbllaborator.count"
					+ " limit 2000");
			
			query.setQuery(q.toString());

			Instances data = query.retrieveInstances();

			data.setClassIndex(data.numAttributes() - 1); 
			System.out.println("load done");
			J48 j48 = new J48();
	        FilteredClassifier fc = new FilteredClassifier();
	        fc.setClassifier(j48);
	        fc.buildClassifier(data);
	        Instance ins = data.instance(data.numInstances()-1);
	        
	        for (int i = 0; i < laboratorValue.size(); i++) {	
	        	String v = "?";
	        	if(laboratorValue.elementAt(i).length()>0){
	        		v = laboratorValue.elementAt(i);
	        		ins.setValue(ins.attribute(i), Double.parseDouble(v));
	        	}else{
	        		ins.setValue(ins.attribute(i), Instance.missingValue());
	        	}
			}
	        System.out.println(ins);
	        double pred = fc.classifyInstance(ins);
	        System.out.println("pred= " + pred);
	        System.out.println(". predicted value: "
	                + data.classAttribute().value((int) pred));
	        System.out.println(data.classAttribute().value((int) pred));
	        this.disease = data.classAttribute().value((int) pred);
	        setAbNormalValue();
	        LaboratorDAO ld = new LaboratorDAO();
	        this.nearLaboratorForms = ld.findLaborator(disease, abNormals);
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setAbNormalValue(){
		LaboratorDAO mpd = new LaboratorDAO();
		abNormals  = new Vector<>();
        for (int i = 0; i < laboratorValue.size(); i++) {	
        	if(laboratorValue.elementAt(i).length()>0){
    			Laborator la = mpd.checkAbnormal(laboratorName.elementAt(i), Float.parseFloat(laboratorValue.elementAt(i)));
    			if(la!=null){
    				abNormals.addElement(la);
    			}
        		
        	}
		}
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
