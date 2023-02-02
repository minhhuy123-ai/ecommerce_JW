package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdersDAO;
import model.Cart;
import model.Orders;
//Controller chuc nang luu thong tin chi tiet don hang va khach hang vao datasource

@WebServlet("/pay")
public class PayController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		try {
			HttpSession session = req.getSession(true);
			/* Kiem tra user da dang nhap chua - neu chua thi bat dang nhap */
			if (session.getAttribute("user") == null) {
				req.setAttribute("error", "Not yet login");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else if(session.getAttribute("cart") == null){
				req.setAttribute("error", "Cart session not yet created");
				req.getRequestDispatcher("cart.jsp").forward(req, resp);
			}
			  else {
				Cart c = (Cart) session.getAttribute("cart");
				/* kiem tra gio hang co san pham chua*/
				if (c.getAmount() == 0) {
					req.setAttribute("error", "Cart is Empty");
					req.getRequestDispatcher("cart.jsp").forward(req, resp);
				}
				else {
					OrdersDAO dao = new OrdersDAO();
//					Cart c = (Cart) session.getAttribute("cart");
					// userMail lay tu input an danh
					String userMail = req.getParameter("mail");
					String discount = req.getParameter("discount");
					String address = req.getParameter("address");
					Orders d = new Orders(userMail, 2, discount, address, "", null);
					// sau lenh nay ban ghi tu cart se chuyen sang order table va order_detail table
					dao.insertOrder(d, c);
					// xoa toan bo Product trong gio hang <- tu them
//					c.getItems().clear();
					// huy session "cart" vua tao
					req.getSession().removeAttribute("cart");
					
					req.setAttribute("success", "Order successful !");
					req.getRequestDispatcher("cart.jsp").forward(req, resp);
//					resp.sendRedirect("list");
				}
			}

		} catch (Exception ex) {
			resp.getWriter().println(ex);
			ex.printStackTrace();
		}
	}

}
