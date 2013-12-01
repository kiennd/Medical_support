package BackendAction;

import java.rmi.RemoteException;
import java.util.Vector;

import Model.MedicineForm;

import com.opensymphony.xwork2.ActionSupport;

public class MedicineFormAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7516414029598309278L;
	
	private int page, totalPage, startIndex, endIndex;
	private static final int ITEM_PER_PAGE = 20;
	private Vector<MedicineForm> medicineforms;
	private String name = "";
	
	public String indexAction() throws RemoteException {
		int totalRecord = RMIConnector.getService().getCountMedicineForm();
		if (page <= 0) {
			page = 1;
		}
		startIndex = (page - 1) * ITEM_PER_PAGE;
		endIndex = page * ITEM_PER_PAGE - 1;
		
		medicineforms = RMIConnector.getService().findMedicineForm(name, startIndex);
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

	public Vector<MedicineForm> getMedicineforms() {
		return medicineforms;
	}

	public void setMedicineforms(Vector<MedicineForm> medicineforms) {
		this.medicineforms = medicineforms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
