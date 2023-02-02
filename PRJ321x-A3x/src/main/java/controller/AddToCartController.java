package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import model.Cart;
import model.Product;

//controller cua chuc nang them 1 san pham vao gio hang

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("utf-8");

		try {
			HttpSession session = req.getSession(true);
			String idd = req.getParameter("id");
			String action = req.getParameter("action");
			// xu ly khi nhan vao nut "add - them san pham vao gio"
			if (action != null && action.equalsIgnoreCase("add")) {
				// khi nao "cart" = null / != null
				if (session.getAttribute("cart") == null) {
					session.setAttribute("cart", new Cart());
				}
				int id = Integer.parseInt(idd);
				Product p = new ListProductDAO().getProduct("" + id);
				// lam sao phan biet cart nao la cua Customer nao
				// neu coi moi user la 1 session thi nen chen url de giup phan bien dau la cart
				// tuong ung user ID nao?
				Cart c = (Cart) session.getAttribute("cart");
				c.add(new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getSrc(), p.getType(),
						p.getBrand(), 1));
				// vi sao ko co dong session.setAttribute("cart", c) de cap nhat so luong moi
				// cho "cart" -> chi ap dung voi request

				resp.sendRedirect("list");

				// xy ly khi nhan vao nut "delete - xoa san pham khoi gio"
			} else if (action != null && action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(idd);
				Cart c = (Cart) session.getAttribute("cart");
				c.remove(id);
				resp.sendRedirect("cart.jsp");
			}

		} catch (Exception ex) {
			resp.getWriter().println(ex);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		try {
			HttpSession session = req.getSession(true);
			String idd = req.getParameter("id");
			String quantity = req.getParameter("quantity");// tu viet them
			// xu ly khi nhan vao nut "add - them san pham vao gio"

			// khi nao "cart" = null / != null
			if (session.getAttribute("cart") == null) {
				session.setAttribute("cart", new Cart());
			}
			int id = Integer.parseInt(idd);
			Product p = new ListProductDAO().getProduct("" + id);
			// lam sao phan biet cart nao la cua Customer nao
			// neu coi moi user la 1 session thi nen chen url de giup phan bien dau la cart
			// tuong ung user ID nao?
			Cart c = (Cart) session.getAttribute("cart");
			c.add(new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getSrc(), p.getType(),
					p.getBrand(), Integer.parseInt(quantity)));
			// vi sao ko co dong session.setAttribute("cart", c) de cap nhat so luong moi
			// cho "cart" -> chi ap dung voi request
			resp.sendRedirect("cart.jsp");

		} catch (Exception ex) {
			resp.getWriter().println(ex);
		}
	}

}
