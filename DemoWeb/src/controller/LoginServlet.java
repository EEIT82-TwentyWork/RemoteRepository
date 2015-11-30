package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerBean;
import model.CustomerService;
public class LoginServlet extends HttpServlet {
	
	public CustomerService customerService = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ccccccc");
		System.out.println("BBBBBBB");
		System.out.println("aaaaaaa");
		System.out.println("aaaaaaas");
//接收資料
		System.out.println("LT  write");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if(username==null || username.length()==0) {
			errors.put("username", "請輸入ID");
		}
		if(password==null || password.length()==0) {
			errors.put("password", "請輸入PWD");
		}
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/secure/login.jsp").forward(request, response);
			return;
		}
		
//呼叫Model
		CustomerBean bean = customerService.login(username, password);
		
//根據Model執行結果，呼叫View
		if(bean==null) {
			errors.put("password", "登入失敗，請再試一次");
			request.getRequestDispatcher(
					"/test.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", bean);

			String path = request.getContextPath();
			response.sendRedirect(path+"/index.jsp");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("isDoPOST");
		this.doGet(req, resp);
	}
}
