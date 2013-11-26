package BackendAction;

import java.util.Vector;

import DAO.*;
import Model.*;

import com.opensymphony.xwork2.ActionSupport;

public class MedicineAction extends ActionSupport {
	
	private Medicine medicineBean;
	private int page, totalPage, startIndex, endIndex;
	private static final int ITEM_PER_PAGE = 20;
	private Vector<Medicine> medicines;
	private String name = "";
	private int id;
	private Vector<String> selection = new Vector<String>();

	

	public String indexAction() {
		medicines = new Vector<>();
		MedicineDAO md = new MedicineDAO();
		medicines = md.findMedicine(name);
		
		int size = this.medicines.size();
		
		if (size == 0) {
			endIndex = -1;
			return SUCCESS;
		}
		totalPage = size / ITEM_PER_PAGE;
		if (totalPage * ITEM_PER_PAGE < medicines.size()) {
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

	public String edit() {

		return SUCCESS;
	}
	public String save() {

		return SUCCESS;
	}
	public String newAction() {

		return SUCCESS;
	}
	public String delete() {

		return SUCCESS;
	}

	public Medicine getMedicineBean() {
		return medicineBean;
	}

	public void setMedicineBean(Medicine medicineBean) {
		this.medicineBean = medicineBean;
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

	public Vector<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Vector<Medicine> medicines) {
		this.medicines = medicines;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vector<String> getSelection() {
		return selection;
	}

	public void setSelection(Vector<String> selection) {
		this.selection = selection;
	}
	
	

	
}
