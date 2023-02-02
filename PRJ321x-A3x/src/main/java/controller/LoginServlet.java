package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		HttpSession session = req.getSession(true);
		
		/*xoa session da ton tai truoc do
		if(session.getAttribute("error")!= null) {
			req.getSession().removeAttribute("error");
		}*/
		
		//b1: get account's user from cookies
		Cookie arr[] = req.getCookies();
		for(Cookie o : arr) {
			if(o.getName().equals("userC")) {
				req.setAttribute("usermail", o.getValue());
			}
			if(o.getName().equals("pswC")) {
				req.setAttribute("password", o.getValue());
			}
		}
		
		//b2: set account's user to login form
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		
		try {
			//huy tat ca cac session -> de "error" neu xuat hien lan trc cung tu dong huy
//			req.getSession(true).invalidate();
			HttpSession session = req.getSession(true);
			
			//collect data from a login form
			String user = req.getParameter("username");
			String password = req.getParameter("password");
			String remember = req.getParameter("remember");
			
			try {
				AccountDAO adao = new AccountDAO();
				Account acc = adao.accountLogin(user, password);
				
				if(acc != null) {
					//Dang nhap thanh cong
					session.setAttribute("user", acc);
						
					//save account's user mail and password in cookie
					Cookie u = new Cookie("userC", user);
					Cookie p = new Cookie("pswC", password);
					
					//when click on remember check will create cookie exist for 5 minute		
					if(remember != null) {
						u.setMaxAge(60*5);	//(60*60) = 1 hour
						p.setMaxAge(60*5);	
					} else {
						u.setMaxAge(0);	
						p.setMaxAge(0);
					}
					
					resp.addCookie(u);
					resp.addCookie(p);
					
					//login is valid, now redirect to index admin page when acc_role = 1 or to home page when acc_role = 0
					String fullName = acc.getName();
					
					String surName = adao.getSurName(fullName);
					
					session.setAttribute("s", surName);
					
					if(acc.getRole() == 1) {
						//display admin name on NavBar. Ex: Nguyen Minh Huy -> Welcome "Minh Huy"
					
						//go back to admin page
						resp.sendRedirect("admin/index.jsp");
					}else {
						//display customer name on NavBar. Ex: Nguyen Minh Huy -> Welcome "Minh Huy"
						
						//go back to home page
						resp.sendRedirect("list");
					}
					
				}else {
					//Dang nhap that bai
					req.setAttribute("error", "Wrong email or password");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (NullPointerException e) {
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, resp);
		} catch (Exception ex) {
			resp.getWriter().println(ex);
		}	
	}
}
