package controller.accountCRUD;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountCRUDDAO;

/**
 * Servlet implementation class DeleteSelectedAccountsController
 */
@WebServlet("/del-sel-accs")
public class DeleteSelectedAccountsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//list nay chua cac account duoc tick chon
		String[] mailList = request.getParameterValues("options[]");
		AccountCRUDDAO aCrud = new AccountCRUDDAO();
		for(String email: mailList) {
			aCrud.deleteAccount(email);
		}
		//day ve LoadAllAccountController de thuc hien load du lieu lai tu dau
		response.sendRedirect("LoadAllAccount");
	}

}
