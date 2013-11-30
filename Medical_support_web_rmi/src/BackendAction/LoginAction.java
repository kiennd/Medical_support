package BackendAction;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import Model.User;

import com.opensymphony.xwork2.ActionSupport;

import control.MedicalSupportInterface;

public class LoginAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 2L;
	private User userBean;
	private Map<String, Object> session;

	public String execute() throws Exception {
		
		if(userBean == null){
			return "input";
		}
		if (RMIConnector.getService().checkLogin(userBean)) {
			userBean = RMIConnector.getService().getUser(userBean.getUsername());
			session.put("user", userBean);
			return "success";
		} else {
			addActionError(getText("error.login"));
			return "input";
		}
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