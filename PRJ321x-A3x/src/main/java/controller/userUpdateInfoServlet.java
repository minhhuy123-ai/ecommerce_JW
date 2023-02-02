package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

@WebServlet("/user-profile")
public class userUpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//xu ly khi click vao "Hello Customer"
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			Account acc = (Account) session.getAttribute("user");
			session.setAttribute("detail", acc);
			response.sendRedirect("profile.jsp");
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
	
	//xu ly sau khi dang ki tai khoan thanh cong
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			//collect data from a profile form
			String userMail = request.getParameter("InputEmail");
			String pass = request.getParameter("inputPass");
			String user = request.getParameter("inputName");	
			String address = request.getParameter("inputAddress");		
			String phone = request.getParameter("inputPhone");
			
			AccountDAO adao = new AccountDAO();
			adao.updateInfo(userMail, user, address, phone);
			
			HttpSession session = request.getSession(true);
			session.removeAttribute("user");
			request.setAttribute("usermail", userMail);
			request.setAttribute("password", pass);
			request.setAttribute("success", "Update success !!!");
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
				
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}

}
