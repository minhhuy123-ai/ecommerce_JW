package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListProductDAO;

// xem thong tin chi tiet cua san pham

@WebServlet("/information-product")
public class InformationProductController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("product", new ListProductDAO().getProduct("" + id));
			RequestDispatcher rd = req.getRequestDispatcher("infoProduct.jsp");
			rd.forward(req, resp);
		} 
		catch (Exception ex) {
			resp.getWriter().println(ex);
		}
	}
	
}
