package BackendAction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import Model.DetectedResult;
import Model.Laborator;
import Model.LaboratorForm;
import Model.Medicine;
import Model.MedicineForm;
import Model.Patient;
import Model.Role;
import Model.User;

public interface MedicalSupportInterface extends Remote {

	public DetectedResult detectDisease(Vector<Laborator> laborators) throws RemoteException;
	public Vector<LaboratorForm> findLaborator(String name, int startIndex,int endIndex)throws RemoteException;
	public Vector<Laborator> getLaborators(String patientid, int count)throws RemoteException;
	public LaboratorForm getLaboratorForm(String patientid, int count) throws RemoteException;
	public int getCountLaborator() throws RemoteException;
	public boolean saveLaboratorForm(LaboratorForm laboratorform) throws RemoteException;
	public boolean saveNewLaboratorForm(LaboratorForm laboratorform) throws RemoteException;
	public boolean saveLaborator(String patientid,int count,String value, String laboratorName) throws RemoteException;
	public Laborator checkAbnormal(String name, float value) throws RemoteException;
	public Vector<LaboratorForm> findLaborator(String disease, Vector<Laborator> abNormals) throws RemoteException;
	// medicine:
	public Vector<Medicine> findMedicine(String name) throws RemoteException;
	public Medicine getMedicine(int id)  throws RemoteException;
	public Vector<MedicineForm> getMedicineForms(String patientid, int count) throws RemoteException;
	public boolean updateMedicine(Medicine medicine)  throws RemoteException;
	public boolean newMedicine(Medicine medicine) throws RemoteException;
	public boolean deleteMedicine(int id) throws RemoteException;
	
	// patient:
	public Vector<Patient> findPatient(String name)  throws RemoteException;
	public Patient getPatient(String id)  throws RemoteException;
	public boolean updatePatient(Patient patient) throws RemoteException;
	public boolean newPatient(Patient patient) throws RemoteException;
	public boolean deletePatient(String id) throws RemoteException;
	
	// role
	public Vector<Role> findRole(String name) throws RemoteException;
	public Role getRole(int id) throws RemoteException;
	public boolean newRole(Role role) throws RemoteException;
	public boolean saveRole(Role role) throws RemoteException;
	public boolean deleteRole(int id) throws RemoteException;
	//user
	public boolean checkLogin(User u) throws RemoteException;
	public Vector<User> findUser(String name) throws RemoteException;
	public User getUser(String username) throws RemoteException;
	public User getUser(int id) throws RemoteException;
	public boolean deleteUser(int id) throws RemoteException;
	public boolean newUser(User user) throws RemoteException;
	public boolean saveUser(User user) throws RemoteException;
}
