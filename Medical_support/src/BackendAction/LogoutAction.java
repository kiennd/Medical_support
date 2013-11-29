package BackendAction;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4505802358351199937L;

	@Override
	public String execute() throws Exception {
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.remove("user");
		return SUCCESS;
	}
}
