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
import bean.PaginationDAO;
import model.Product;

//controller cua chuc nang hien thi thong tin san pham trong data source

@WebServlet("/list")
public class ListController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {
//			List<Product> ls = new ListProductDAO().search("");
			List<Product> ls = new ListProductDAO().getAllProduct();
			
			//ho tro phan trang
			int count = ls.size();
			int pageSize = 3;
			int endPage = new PaginationDAO().getEndPage(count, pageSize);
			List<Product> listSearch = new PaginationDAO().search("", 1, pageSize);
		
			
			req.setAttribute("endPage", endPage);
			req.setAttribute("listSearch", listSearch);
			req.setAttribute("index", 1);
			req.setAttribute("txtSearch", ""); 
			//chua setAttribute"txtSearch" co tu hieu la "txtSearch" = "" ko -> se ko hien thi ket qua
//			req.setAttribute("products", ls);
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.include(req, resp);
			System.out.print("Kích co la" + ls.size());
		} catch (Exception ex) {
			Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
