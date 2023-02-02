package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		try {
			//huy tat ca cac session -> de "error" neu xuat hien lan trc cung tu dong huy
//			request.getSession(true).invalidate();
			
			HttpSession session = request.getSession(true);	
			
			//make sure that email and password is valid
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@#$%^&*]+";
			//collect data from a register form
			String userMail = request.getParameter("user_mail");
			String userName = request.getParameter("user_name");
			String pass = request.getParameter("password");		
			String repass = request.getParameter("re_pass");	
				
			//check whether email and password meet the standard format
			if (!pass.matches(regex) || !userMail.matches(regexMail)) {
				request.setAttribute("error", "invalid syntax");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else if(!pass.equals(repass)) 
			{	//check whether password and re_password is the same
				request.setAttribute("error", "password not same");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else{
				//check whether register email same as exist user_email in database
				AccountDAO adao = new AccountDAO();
				Account a = adao.checkAccountExist(userMail);
				if(a==null) {
					//duoc sign up
					adao.signup(userMail, pass, userName);
					request.setAttribute("usermail", userMail);
					request.setAttribute("password", pass);
					request.setAttribute("success", "Register success!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else {
					//trung email -> day ve trang register
					request.setAttribute("error", "email already existed");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}	
			}
		} catch (Exception e) {
			response.getWriter().println(e);
		}
		
	}

}
