package control;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import view.RmiMedicalView;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import DAO.LaboratorDAO;
import DAO.UserDAO;
import Model.ConstantMedical;
import Model.DetectedResult;
import Model.Laborator;
import Model.User;

public class MedicalRMIControl extends UnicastRemoteObject implements
		MedicalSupportInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8226593487598812757L;
	private Registry registry;
	private int rmiport = 1234;
	private String rmiservice = "SudokuRmiService";
	private RmiMedicalView view;
	private UserDAO userdao = new UserDAO();
	
	public MedicalRMIControl(RmiMedicalView view) throws RemoteException {
		this.view = view;
		view.showMessage("runing");
		registry = LocateRegistry.createRegistry(rmiport);
		registry.rebind(rmiservice, this);

	}
	
	
	@Override
	public boolean checkLogin(User u) throws RemoteException {
		return userdao.checkLogin(u);
	}
	
	@Override
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
	        
	        for (int i = 0; i < laborators.size(); i++) {	
	        	
	        	if(!Float.isNaN(laborators.elementAt(i).getResult())){
	        		float v = laborators.elementAt(i).getResult();
	        		ins.setValue(ins.attribute(i), v);
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
	        
	        //
	        DetectedResult dr = new DetectedResult();
	        dr.setDisease(data.classAttribute().value((int) pred));
	        dr.setAbNormals(getAbNormalValue(laborators));
	        LaboratorDAO ld = new LaboratorDAO();
	        dr.setNearLaboratorForms(ld.findLaborator(dr.getDisease(), dr.getAbNormals()));
	        return dr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
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

	public static void main(String[] args) {
		RmiMedicalView view = new RmiMedicalView();
		try {
			MedicalRMIControl control = new MedicalRMIControl(view);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
