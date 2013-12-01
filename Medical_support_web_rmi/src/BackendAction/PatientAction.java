package BackendAction;

import java.rmi.RemoteException;
import java.util.Vector;

import Model.Patient;

import com.opensymphony.xwork2.ActionSupport;

public class PatientAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7426685351940185926L;
	private Patient patientBean;
	private int page, totalPage, startIndex, endIndex;
	private static final int ITEM_PER_PAGE = 20;
	private Vector<Patient> patients;
	private String name = "";
	private String id = "";
	private Vector<String> selection = new Vector<String>();

	public String indexAction() throws RemoteException {
		
		patients = new Vector<>();
		patients = RMIConnector.getService().findPatient(name);
		
		int size = this.patients.size();
		
		if (size == 0) {
			endIndex = -1;
			return SUCCESS;
		}
		totalPage = size / ITEM_PER_PAGE;
		if (totalPage * ITEM_PER_PAGE < patients.size()) {
			totalPage++;
		}
		System.out.println("totalPage:" + totalPage);
		if (page <= 0) {
			page = 1;
		}
		startIndex = (page - 1) * ITEM_PER_PAGE;
		if (page != totalPage) {
			endIndex = page * ITEM_PER_PAGE - 1;
		} else {
			endIndex = size - 1;
		}
		return SUCCESS;
	}

	
	public String edit() throws RemoteException {
		
		patientBean = RMIConnector.getService().getPatient(id);
		if (patientBean != null)
			return SUCCESS;
		return ERROR;
	}
	public String save() throws RemoteException {
		
		if(RMIConnector.getService().updatePatient(patientBean)){
			return SUCCESS;		
		}else{
			System.out.println("update patient error");
			return ERROR;
		}
		
	}
	public String newAction() throws RemoteException {
		
		if(patientBean==null){
			return ERROR;
		}
		if(RMIConnector.getService().newPatient(patientBean)){
			return SUCCESS;		
		}else{
			System.out.println("new patient error");
			return ERROR;
		}

	}
	public String delete() throws RemoteException {
		
		if (id.length() > 0) {
			if(RMIConnector.getService().deletePatient(id)){
				addActionMessage("Item #" + id + " was deleted !");
				return SUCCESS;
			}else{
				return ERROR;
			}
		} else {
			for (String s : selection) {
				if (RMIConnector.getService().deletePatient(s)){
					addActionMessage("Item #" + s + " was deleted !");
				}else{
					return ERROR;
				}
			}
			return SUCCESS;
		}
	}

	
	// get set
	public void setSlelect(String[] select) {
		for (int i = 0; i < select.length; i++) {
			selection.add(select[i]);
		}
	}


	public Patient getPatientBean() {
		return patientBean;
	}


	public void setPatientBean(Patient patientBean) {
		this.patientBean = patientBean;
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


	public Vector<Patient> getPatients() {
		return patients;
	}


	public void setPatients(Vector<Patient> patients) {
		this.patients = patients;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Vector<String> getSelection() {
		return selection;
	}


	public void setSelection(Vector<String> selection) {
		this.selection = selection;
	}
	
	

}
