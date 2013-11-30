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
import DAO.MedicineDAO;
import DAO.PatientDAO;
import DAO.RoleDAO;
import DAO.UserDAO;
import Model.ConstantMedical;
import Model.DetectedResult;
import Model.Laborator;
import Model.LaboratorForm;
import Model.Medicine;
import Model.MedicineForm;
import Model.Patient;
import Model.Role;
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
	private RoleDAO roledao = new RoleDAO();
	private PatientDAO patientdao = new PatientDAO();
	private MedicineDAO medicinedao = new MedicineDAO();
	private LaboratorDAO laboratordao = new LaboratorDAO();
	
	
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




	@Override
	public Vector<LaboratorForm> findLaborator(String name, int startIndex,
			int endIndex) throws RemoteException {
		return laboratordao.findLaborator(name, startIndex, endIndex);
	}


	@Override
	public Vector<Laborator> getLaborators(String patientid, int count)
			throws RemoteException {
		return laboratordao.getLaborators(patientid, count);
	}


	@Override
	public LaboratorForm getLaboratorForm(String patientid, int count)
			throws RemoteException {
		return laboratordao.getLaboratorForm(patientid, count);
	}


	@Override
	public int getCountLaborator() throws RemoteException {
		// TODO Auto-generated method stub
		return laboratordao.getCountLaborator();
	}


	@Override
	public boolean saveLaboratorForm(LaboratorForm laboratorform)
			throws RemoteException {
		// TODO Auto-generated method stub
		return laboratordao.saveLaboratorForm(laboratorform);
	}


	@Override
	public boolean saveNewLaboratorForm(LaboratorForm laboratorform)
			throws RemoteException {
		// TODO Auto-generated method stub
		return laboratordao.saveNewLaboratorForm(laboratorform);
	}


	@Override
	public boolean saveLaborator(String patientid, int count, String value,
			String laboratorName) throws RemoteException {
		// TODO Auto-generated method stub
		return laboratordao.saveLaborator(patientid, count, value, laboratorName);
	}


	@Override
	public Laborator checkAbnormal(String name, float value)
			throws RemoteException {
		return laboratordao.checkAbnormal(name, value);
	}


	@Override
	public Vector<LaboratorForm> findLaborator(String disease,
			Vector<Laborator> abNormals) throws RemoteException {
		// find similar laborator 
		return laboratordao.findLaborator(disease, abNormals);
	}


	@Override
	public Vector<Medicine> findMedicine(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return medicinedao.findMedicine(name);
	}


	@Override
	public Medicine getMedicine(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return medicinedao.getMedicine(id);
	}


	@Override
	public Vector<MedicineForm> getMedicineForms(String patientid, int count)
			throws RemoteException {
		// TODO Auto-generated method stub
		return medicinedao.getMedicineForms(patientid, count);
	}


	@Override
	public boolean updateMedicine(Medicine medicine) throws RemoteException {
		// TODO Auto-generated method stub
		return medicinedao.updateMedicine(medicine);
	}


	@Override
	public boolean newMedicine(Medicine medicine) throws RemoteException {
		// TODO Auto-generated method stub
		return medicinedao.newMedicine(medicine);
	}


	@Override
	public boolean deleteMedicine(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return medicinedao.deleteMedicine(id);
	}


	@Override
	public Vector<Patient> findPatient(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return patientdao.findPatient(name);
	}


	@Override
	public Patient getPatient(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return patientdao.getPatient(id);
	}


	@Override
	public boolean updatePatient(Patient patient) throws RemoteException {
		return patientdao.updatePatient(patient);
	}


	@Override
	public boolean newPatient(Patient patient) throws RemoteException {
		// TODO Auto-generated method stub
		return patientdao.newPatient(patient);
	}


	@Override
	public boolean deletePatient(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return patientdao.deletePatient(id);
	}


	@Override
	public Vector<Role> findRole(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return roledao.findRole(name);
	}


	@Override
	public Role getRole(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return roledao.getRole(id);
	}


	@Override
	public boolean newRole(Role role) throws RemoteException {
		// TODO Auto-generated method stub
		return roledao.newRole(role);
	}


	@Override
	public boolean saveRole(Role role) throws RemoteException {
		// TODO Auto-generated method stub
		return roledao.saveRole(role);
	}


	@Override
	public boolean deleteRole(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return roledao.deleteRole(id);
	}


	@Override
	public Vector<User> findUser(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getUser(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userdao.getUser(username);
	}


	@Override
	public User getUser(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return userdao.getUser(id);
	}


	@Override
	public boolean deleteUser(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return userdao.deleteUser(id);
	}


	@Override
	public boolean newUser(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return userdao.newUser(user);
	}
	

	public boolean saveUser(User user) throws RemoteException {
		return userdao.saveUser(user);
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
