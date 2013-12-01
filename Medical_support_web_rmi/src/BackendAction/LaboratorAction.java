package BackendAction;

import java.rmi.RemoteException;
import java.util.Vector;

import Model.Laborator;
import Model.LaboratorForm;
import Model.MedicineForm;
import Model.Patient;

import com.opensymphony.xwork2.ActionSupport;


public class LaboratorAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3653765503531368559L;
	private String patientid;
	int count;
	private String laboratorName;
	private String laboratorValue;
	private String name = "";
	private Vector<LaboratorForm> laboratorForms; 
	private int page, totalPage, startIndex, endIndex;
	private static final int ITEM_PER_PAGE = 20;
	
	private Vector<Laborator> laborators;
	private LaboratorForm laboratorFormBean;
	private Patient currentPatient;
	Vector<MedicineForm> medicineforms;

	
	public String addPatient(){
		return SUCCESS;
	}
	
	public String indexAction() throws RemoteException{
		
		int totalRecord = RMIConnector.getService().getCountLaborator();
		if (page <= 0) {
			page = 1;
		}
		startIndex = (page - 1) * ITEM_PER_PAGE;
		endIndex = page * ITEM_PER_PAGE - 1;
		
		laboratorForms = RMIConnector.getService().findLaborator(name, startIndex,endIndex );
		System.out.println(totalRecord);
		int size = totalRecord;
		if (size == 0) {
			endIndex = -1;
			return SUCCESS;
		}
		totalPage = size / ITEM_PER_PAGE;
		if (totalPage * ITEM_PER_PAGE < size) {
			totalPage++;
		}
		System.out.println("totalPage:" + totalPage);
		
		return SUCCESS;
	}

	
	public String detailAction() throws RemoteException{
		
		this.laborators = RMIConnector.getService().getLaborators(patientid, count);
		currentPatient = RMIConnector.getService().getPatient(patientid);
		medicineforms = RMIConnector.getService().getMedicineForms(patientid, count);
		return SUCCESS;
	}
	
	
	public String saveLaborator() throws RemoteException{
		
		RMIConnector.getService().saveLaborator(patientid, count, laboratorValue, laboratorName);
		return SUCCESS;
	}
	
	public String viewLaboratorForm() throws RemoteException{
		
		laboratorFormBean = RMIConnector.getService().getLaboratorForm(patientid, count);
		return SUCCESS;
	}
	
	public String saveLaboratorForm() throws RemoteException{
		
		RMIConnector.getService().saveLaboratorForm(laboratorFormBean);
		return SUCCESS;
	}
	
	public String saveNewLaboratorForm() throws RemoteException{
		
		RMIConnector.getService().saveNewLaboratorForm(laboratorFormBean);
		
		return SUCCESS;
		
	}
	
	
	public LaboratorForm getLaboratorFormBean() {
		return laboratorFormBean;
	}

	public void setLaboratorFormBean(LaboratorForm laboratorFormBean) {
		this.laboratorFormBean = laboratorFormBean;
	}

	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Vector<Laborator> getLaborators() {
		return laborators;
	}

	public void setLaborators(Vector<Laborator> laborators) {
		this.laborators = laborators;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<LaboratorForm> getLaboratorForms() {
		return laboratorForms;
	}

	public void setLaboratorForms(Vector<LaboratorForm> laboratorForms) {
		this.laboratorForms = laboratorForms;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getLaboratorName() {
		return laboratorName;
	}

	public void setLaboratorName(String laboratorName) {
		this.laboratorName = laboratorName;
	}

	public String getLaboratorValue() {
		return laboratorValue+"";
	}

	public void setLaboratorValue(String laboratorValue) {
		if(Float.isNaN(Float.parseFloat(laboratorValue))|| laboratorValue==null){
			laboratorValue = null;
		}else{
			this.laboratorValue = laboratorValue;
		}
	}

	public Vector<MedicineForm> getMedicineforms() {
		return medicineforms;
	}

	public void setMedicineforms(Vector<MedicineForm> medicineforms) {
		this.medicineforms = medicineforms;
	}
	 
	
}
