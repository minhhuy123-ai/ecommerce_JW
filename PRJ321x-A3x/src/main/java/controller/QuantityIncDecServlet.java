package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Product;

@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			//sua code phan nay, session da co chua???
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			//xet param id de phong nguoi dung co tinh chen id ko hop le tren URL
			if(action != null && id >= 1) {
				if(action.equals("inc")) {
					for(Product p: cart.getItems()) {
						if(p.getId()==id) {
							int number = p.getNumber();
							number++;
							p.setNumber(number);
							break;
						}
						
					}
				}
				if(action.equals("dec")) {
					for(Product p: cart.getItems()) {
						if(p.getId()==id) {
							int number = p.getNumber();
							if(number > 0) {
								number--;
							}else {
								number = 0;
							}
							p.setNumber(number);
							break;
						}
					}
				}
				
				response.sendRedirect("cart.jsp");
			}
			else {
				response.sendRedirect("cart.jsp");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}	
	}

}
