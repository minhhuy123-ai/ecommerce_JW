package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListProductDAO;
import bean.PaginationDAO;
//Controller cua chuc nang tim kiem danh cho nguoi dung
import model.Product;

@WebServlet("/search-2")
public class SearchController2 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		try {
			String text = req.getParameter("search");
			List<Product> ls = new ListProductDAO().search(text);
			
			//Ho tro phan trang
			int count = ls.size();
			int pageSize = 3;
			int endPage = new PaginationDAO().getEndPage(count, pageSize);
			List<Product> listSearch = new PaginationDAO().search(text, 1, pageSize);
			//
			
			req.setAttribute("endPage", endPage);
			req.setAttribute("listSearch", listSearch);
			req.setAttribute("index", 1);
			req.setAttribute("txtSearch", text);
//			req.setAttribute("result", new ListProductDAO().search(text));
			RequestDispatcher rd = req.getRequestDispatcher("search.jsp");
			rd.forward(req, resp);
			
		} catch(Exception ex) {
			resp.getWriter().println(ex);
		}
		
	}
}
