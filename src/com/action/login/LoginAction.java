package com.action.login;

import com.cyz.dbdao.impl.UserDaoImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String execute() throws Exception {
		
		//创建上下文（context）对象 用来将用户名储存到session中
		ActionContext context= ActionContext.getContext();
		
		System.out.println(username + "/" + password);
		UserDaoImpl userService = new UserDaoImpl();
		Boolean returnType = userService.MatchPassword(username, password);

		if (returnType) {
			//向Request中添加：
			//context.put("username", username);
			//向session中添加:
			context.getSession().put("username", username);
			
			return "loginsucc";
		}else{
			return "wrongpw";
		}
	}
}
