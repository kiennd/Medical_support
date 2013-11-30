package BackendAction;

import java.util.Vector;

import DAO.*;
import Model.*;

import com.opensymphony.xwork2.ActionSupport;

public class MedicineAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7426685351940185926L;
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
		MedicineDAO md = new MedicineDAO();
		medicineBean = md.getMedicine(id);
		if (medicineBean != null)
			return SUCCESS;
		return ERROR;
	}
	public String save() {
		MedicineDAO md = new MedicineDAO();
		if(md.updateMedicine(medicineBean)){
			return SUCCESS;		
		}else{
			System.out.println("update medicine error");
			return ERROR;
		}
		
	}
	public String newAction() {
		if(medicineBean==null){
			return ERROR;
		}
		MedicineDAO md = new MedicineDAO();
		if(md.newMedicine(medicineBean)){
			return SUCCESS;		
		}else{
			System.out.println("new medicine error");
			return ERROR;
		}

	}
	public String delete() {
		MedicineDAO md = new MedicineDAO();

		if (id!= 0) {
			if(md.deleteMedicine(id)){
				addActionMessage("Item #" + id + " was deleted !");
				return SUCCESS;
			}else{
				return ERROR;
			}
		} else {
			for (String s : selection) {
				if (md.deleteMedicine(Integer.parseInt(s))){
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
