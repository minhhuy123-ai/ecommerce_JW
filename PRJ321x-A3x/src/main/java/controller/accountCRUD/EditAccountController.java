package controller.accountCRUD;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountCRUDDAO;

/**
 * Servlet implementation class EditAccountController
 */
@WebServlet("/edit")
public class EditAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//co nen dung regex password o day ko
		String umail = request.getParameter("user_mail_2"); 
		String name = request.getParameter("user_name_2");
		String password = request.getParameter("password_2");
		String address = request.getParameter("address_2");
		String phone = request.getParameter("phone_2");
		AccountCRUDDAO aCrud = new AccountCRUDDAO();
		aCrud.editAccount(name, password, address, phone, umail);
		response.sendRedirect("LoadAllAccount");
	}

}
