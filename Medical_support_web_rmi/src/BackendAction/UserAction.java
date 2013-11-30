package BackendAction;

import java.util.Vector;

import Model.Role;
import Model.User;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5095053774227771915L;
	private static final int ITEM_PER_PAGE = 20;
	private Vector<User> userList;
	private String name = "";
	private int id, page, totalPage, startIndex, endIndex;
	private User userBean;
	private String confirmPassword;
	private Vector<String> selection = new Vector<String>();
	private Vector<Role> roles = new Vector<>();


	public UserAction() {
	}

	@Override
	public String execute() throws Exception {
		
		this.userList = RMIConnector.getService().findUser(name);
		totalPage = userList.size() / ITEM_PER_PAGE;

		if (this.userList.size() == 0) {
			endIndex = -1;
			return SUCCESS;
		}

		if (totalPage * ITEM_PER_PAGE < userList.size()) {
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
			endIndex = userList.size() - 1;
		}

		System.out.println(userList.size());
		return SUCCESS;
	}

	public String edit() throws Exception {
		
		this.userBean = RMIConnector.getService().getUser(id);
		roles = RMIConnector.getService().findRole("");
		return SUCCESS;
	}

	public String saveUser() throws Exception {
		
		if (RMIConnector.getService().saveUser(userBean))
			return SUCCESS;
		else
			return ERROR;
	}

	public String deleteUser() throws Exception {
		if (id != 0) {
			if (RMIConnector.getService().deleteUser(id)) {
				addActionMessage("Item #" + id + " was deleted!");
				return SUCCESS;
			} else
				return ERROR;
		} else {
			for (String s : selection) {
				if (RMIConnector.getService().deleteUser(Integer.parseInt(s)))
					addActionMessage("Item #" + s + " was deleted !");
				else
					return ERROR;
			}
			return SUCCESS;
		}
	}

	public String newUser() throws Exception {
		if (userBean == null) {
			roles = RMIConnector.getService().findRole("");

			return ERROR;
		}
		if (confirmPassword.equals(userBean.getPassword())) {
			if (RMIConnector.getService().newUser(userBean)) {
				addActionMessage("One item has been created successfully.");
				return SUCCESS;
			} else {
				addActionError("This username has already been taken or role id is invalid.");
				return ERROR;
			}
		} else {
			addFieldError("confirmPassword", "password not match");
		}
		return ERROR;
	}

	public Vector<Role> getRoles() {
		return roles;
	}

	public void setRoles(Vector<Role> roles) {
		this.roles = roles;
	}

	public void setSlelect(String[] select) {
		for (int i = 0; i < select.length; i++) {
			selection.add(select[i]);
		}
	}

	public Vector<User> getUserList() {
		return userList;
	}

	public void setUserList(Vector<User> userList) {
		this.userList = userList;
	}

	public String getName() {
		return name;
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

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserBean() {
		return userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
