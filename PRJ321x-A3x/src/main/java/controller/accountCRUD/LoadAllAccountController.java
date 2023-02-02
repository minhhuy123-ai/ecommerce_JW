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
 * Servlet implementation class LoadAllAccountController
 */
@WebServlet("/LoadAllAccount")
public class LoadAllAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			AccountCRUDDAO aCrud = new AccountCRUDDAO();
			List<Account> list = aCrud.loadAllAccount();
			request.setAttribute("listP", list);
			request.getRequestDispatcher("crud.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
