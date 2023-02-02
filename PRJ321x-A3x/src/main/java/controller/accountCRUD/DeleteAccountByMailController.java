package controller.accountCRUD;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountCRUDDAO;
import model.Account;

/**
 * Servlet implementation class DeleteAccountByMailController
 */
@WebServlet("/del-by-mail")
public class DeleteAccountByMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String mail = request.getParameter("mail");
		AccountCRUDDAO aCrud = new AccountCRUDDAO();
		aCrud.deleteAccount(mail);
		//day ve LoadAllAccountController de thuc hien load du lieu lai tu dau
		response.sendRedirect("LoadAllAccount");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
