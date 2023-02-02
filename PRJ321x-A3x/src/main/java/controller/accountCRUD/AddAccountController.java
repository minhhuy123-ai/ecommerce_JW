package controller.accountCRUD;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountCRUDDAO;

/**
 * Servlet implementation class AddAccountController
 */
@WebServlet("/add-account")
public class AddAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//co nen dung regex o day ko
		String umail = request.getParameter("user_mail"); 
		String name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		AccountCRUDDAO aCrud = new AccountCRUDDAO();
		aCrud.insertAccount(name, umail, password, address, phone);
		//day ve LoadAllAccountController de thuc hien load du lieu lai tu dau
		response.sendRedirect("LoadAllAccount");
	}

}
