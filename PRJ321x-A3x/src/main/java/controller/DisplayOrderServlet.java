package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;
import model.Account;
import model.ProductOrders;

@WebServlet("/display-order")
public class DisplayOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			OrdersDAO dao = new OrdersDAO();
			HttpSession session = request.getSession();
			Account acc = (Account) session.getAttribute("user");
			List<ProductOrders> po = dao.getAllProductOrders(acc.getUsr());
			request.setAttribute("listOrders", po);
			request.getRequestDispatcher("pay.jsp").forward(request, response);
			
		} catch (Exception e) {
			response.getWriter().println(e);
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
