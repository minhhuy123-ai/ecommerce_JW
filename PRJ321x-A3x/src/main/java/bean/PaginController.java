package bean;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

@WebServlet("/search3")
public class PaginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// khi click vao cac nut trong cac page tiep theo (ngoai page 1) thi se tu dong
		// lay txtSearch truoc do (lay tu page 1) de hien thi
		response.setContentType("text/html;charset=UTF-8");
		try {
			String txtSearch = request.getParameter("txtSearch");
			int index = Integer.parseInt(request.getParameter("index"));

			PaginationDAO paginationDAO = new PaginationDAO();
			int count = paginationDAO.count(txtSearch);
			int pageSize = 3;
			int endPage = paginationDAO.getEndPage(count, pageSize);

			List<Product> listSearch = paginationDAO.search(txtSearch, index, pageSize);

			// kiem tra
//					for(Product p: listSearch) {
//						System.out.println(p);
//					}

			request.setAttribute("endPage", endPage);
			request.setAttribute("listSearch", listSearch);
			// luu nguoc txtSearch de luc click <a> cua |3|4... van co txtSearch de...
			request.setAttribute("txtSearch", txtSearch);
			request.setAttribute("index", index);
			request.getRequestDispatcher("search.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// khi bam vao nut search lan dau (tren head cua index.jsp) se hien ra du lieu
		// trang dau tien
		/*
		 * response.setContentType("text/html;charset=UTF-8"); try { String txtSearch =
		 * request.getParameter("search"); int index =
		 * Integer.parseInt(request.getParameter("index")); PaginationDAO paginationDAO
		 * = new PaginationDAO(); int count = paginationDAO.count(txtSearch); int
		 * pageSize = 3; int endPage = count/pageSize; //7 ban ghi 3 ban/trang -> 1|2|3
		 * if(count % pageSize != 0) { endPage++; } List<Product> listSearch =
		 * paginationDAO.search(txtSearch, index, pageSize);
		 * 
		 * request.setAttribute("endPage", endPage); request.setAttribute("list",
		 * listSearch); //lua nguoc txtSearch vao de luc click <a> cua 2|3|4... van co
		 * txtSearch de... request.setAttribute("txtSearch", txtSearch);
		 * request.getRequestDispatcher("search.jsp").forward(request, response);
		 * 
		 * } catch (Exception e) { e.printStackTrace();
		 * System.out.print(e.getMessage()); }
		 */
	}

}
