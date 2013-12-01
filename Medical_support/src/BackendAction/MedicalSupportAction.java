package BackendAction;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.neighboursearch.LinearNNSearch;
import weka.experiment.InstanceQuery;
import DAO.DBConnection;
import DAO.LaboratorDAO;
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
		try {
			laborators  = generateLaborators();
			DetectedResult dr = detectDisease(laborators);
			this.abNormals = dr.getAbNormals();
			this.nearLaboratorForms = dr.getNearLaboratorForms();
			this.disease = dr.getDisease();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public DetectedResult detectDisease(Vector<Laborator> laborators)
			throws RemoteException {
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
					+ " ");
			
			query.setQuery(q.toString());
			System.out.println(q.toString());
			Instances data = query.retrieveInstances();

			data.setClassIndex(data.numAttributes() - 1);
			System.out.println("load done : number instance "+data.numInstances());
			
	        Instance ins = data.instance(data.numInstances()-1);
	        float choice = 0;
	        for (int i = 0; i < laborators.size(); i++) {	
	        	
	        	if(!Float.isNaN(laborators.elementAt(i).getResult())){
	        		float v = laborators.elementAt(i).getResult();
	        		ins.setValue(ins.attribute(i), v);
	        		choice+= v;
	        	}else{
	        		ins.setValue(ins.attribute(i), Instance.missingValue());
	        	}
			}
	        

	        
	        System.out.println(ins);
			String disease ;
			disease = knndetect(data, ins,(int)choice);
	        if((int)choice %2==0){
	        	disease = j48detect(data, ins);
	        }
	        
	        if((int)choice %3==0){
	        	disease = navieDetect(data, ins);
	        }
	        
	        DetectedResult dr = new DetectedResult();
	        dr.setDisease(disease);
	        dr.setAbNormals(getAbNormalValue(laborators));
	        LaboratorDAO ld = new LaboratorDAO();
	        
	        dr.setNearLaboratorForms(ld.findLaborator(dr.getDisease(), dr.getAbNormals()));
	        return dr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
	
	public String navieDetect(Instances data, Instance ins) throws Exception{
	    NaiveBayes model=new NaiveBayes();
	    model.buildClassifier(data);
		double pred = model.classifyInstance(ins);
		System.out.println("pred= " + pred);
		System.out.println(". predicted value: "
				+ data.classAttribute().value((int) pred));
		System.out.println(data.classAttribute().value((int) pred));
		return data.classAttribute().value((int) pred);
	}
	
	
	public String j48detect(Instances data, Instance ins) throws Exception{
		
		J48 j48 = new J48();
		FilteredClassifier fc = new FilteredClassifier();
		fc.setClassifier(j48);
		fc.buildClassifier(data);
		double pred = fc.classifyInstance(ins);
		System.out.println("pred= " + pred);
		System.out.println(". predicted value: "
				+ data.classAttribute().value((int) pred));
		System.out.println(data.classAttribute().value((int) pred));
		return data.classAttribute().value((int) pred);
	}
	public String svmdetect(Instances data, Instance ins) throws Exception{
        LibSVM svm = new LibSVM();
        svm.buildClassifier(data);
		double pred = svm.classifyInstance(ins);
		System.out.println("pred= " + pred);
		System.out.println(". predicted value: "
				+ data.classAttribute().value((int) pred));
		System.out.println(data.classAttribute().value((int) pred));
		return data.classAttribute().value((int) pred);
	}

	public String knndetect(Instances data, Instance ins, int total) throws Exception{

		LinearNNSearch knn = new LinearNNSearch(
				data);

        Instances nearestInstances= knn.kNearestNeighbours(ins, 20);

        return data.classAttribute().value((int) nearestInstances.instance(total%19).classValue());
	
	}
	
	
	
	public boolean checkMatchIntance(Instance i1, Instance i2){
		return i1.toString().equals(i2.toString());		
	}
	
	public LaboratorForm wekaInstanceToLaboratorForm(Instance ins){
				LaboratorForm lf = new LaboratorForm();
		StringBuffer query = new StringBuffer("select * from tbllaborator where ");
		for (int i = 0; i < ins.numAttributes()-2; i++) {
			query.append("`"+ins.attribute(i).name()+"`");
			
			if(!Double.isNaN(ins.value(i))){			
				query.append(" = "+ins.value(i)+"");
			}else{
				query.append(" is null ");
			}
			if(i!=ins.numAttributes()-3){
				query.append(" and ");
			}
			
		}
		java.sql.Connection conn = DBConnection.getConn();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query.toString());
			while (rs.next()) {
				System.out.println("abc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lf;
	}
	
	
	public Vector<Laborator> getAbNormalValue(Vector<Laborator> laborators){
		LaboratorDAO mpd = new LaboratorDAO();
		Vector<Laborator> abNormals  = new Vector<>();
        for (int i = 0; i < laborators.size(); i++) {	
        	if(!Float.isNaN(laborators.elementAt(i).getResult())){
    			Laborator la = mpd.checkAbnormal(laborators.elementAt(i).getName(), laborators.elementAt(i).getResult());
    			if(la!=null){
    				abNormals.addElement(la);
    			}
        		
        	}
		}
        return abNormals;
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
