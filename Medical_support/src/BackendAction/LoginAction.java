package BackendAction;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import DAO.UserDAO;
import Model.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 2L;
	private User userBean;
	private Map<String, Object> session;

	public String execute() throws Exception {
		UserDAO ud = new UserDAO();
		if(userBean == null){
			return "input";
		}
		if (ud.checkLogin(userBean)) {
			session.put("user", userBean);
			return "success";
		} else {
			addActionError(getText("error.login"));
			return "input";
		}
	}
	
	public String logout() throws Exception {
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.remove("user");
		return SUCCESS;
	}
	
	public void validate() {
		System.out.println(userBean.getUsername());
		if (userBean.getUsername().length() == 0) {
			addActionError("Username is required.");
		}

		if (userBean.getPassword().length() == 0) {
			addActionError("Password is required.");
		}
	}

	public User getUserBean() {
		return userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}