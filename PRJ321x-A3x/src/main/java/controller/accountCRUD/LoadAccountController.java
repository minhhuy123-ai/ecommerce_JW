package controller.accountCRUD;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountCRUDDAO;
import dao.ListProductDAO;
import model.Account;

/**
 * Servlet implementation class LoadAccountController
 */
@WebServlet("/load-account")
public class LoadAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//Controller dung de tu dong hien thi du lieu san pham tren Edit Modal Form khi click vao bieu tuong "edit"
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String mail = request.getParameter("mail");
			AccountCRUDDAO aCrud = new AccountCRUDDAO();
			Account acc = aCrud.getAccountByMail(mail);
			request.setAttribute("detail", acc);
			request.getRequestDispatcher("editForm.jsp").forward(request, response);
		} 
		catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
