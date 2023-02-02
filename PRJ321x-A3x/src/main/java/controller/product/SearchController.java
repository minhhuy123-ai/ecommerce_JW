package controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListProductDAO;
import model.Product;
import bean.PaginationDAO;

//controller cua chuc nang tim kiem product danh cho quan tri he thong

@WebServlet("/search")
public class SearchController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = resp.getWriter()){
			String name = req.getParameter("search");
			List<Product> ls = new ListProductDAO().search(name);
			
			//Ho tro phan trang
			int count = ls.size();
			int pageSize = 3;
			int endPage = new PaginationDAO().getEndPage(count, pageSize);
			List<Product> listSearch = new PaginationDAO().search(name, 1, pageSize);
			//
			
			req.setAttribute("endPage", endPage);
			req.setAttribute("listSearch", listSearch);
			req.setAttribute("txtSearch", name);
//			req.setAttribute("products", ls);
			RequestDispatcher rd = req.getRequestDispatcher("list.jsp");
			rd.forward(req, resp);
			resp.sendRedirect("admin/index.jsp");
		} catch(Exception ex) {
			Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
