package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;

public class PaginationDAO {
	Connection conn = null;
	String query = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//ham dem so luong ban ghi
	public int count(String txtSearch) {
		query = "Select count(*) from Products where product_name like ?";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		
		return 0;
	}
	
	//ham lay so link pagin can hien thi
	public int getEndPage(int count, int pageSize) {
		int endPage = count/pageSize;
		if(count % pageSize != 0){
			endPage++;
		}
		return endPage;
	}
	
	//ham hien thi so luong ban ghi tuy theo link pagin 1|2|3|4 duoc click vao
	
	/*(can nhac viet them search(int pageIndex, int pageSize) trong t/hop search(String txtSearch, int pageIndex, int pageSize)
	bao loi khi txtSearch = null*/
	
	public List<Product> search(String txtSearch, int pageIndex, int pageSize){
		
		List<Product> list = new ArrayList<>();
		
		query = "with tempTable as(select ROW_NUMBER() over (order by [product_id] asc) as rowNo, * from Products where [product_name] like ?)"
				+ "select * from tempTable where rowNo between ?*?-2 and ?*?";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			ps.setInt(2, pageIndex);
			ps.setInt(3, pageSize);
			ps.setInt(4, pageIndex);
			ps.setInt(5, pageSize);
			rs = ps.executeQuery();
			//do ban moi co them rowNo nen vi tri cac field cua Products dieu chinh len 1 don vi
			while (rs.next()) {
				list.add(new Product(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6),
						rs.getString(7), rs.getString(8)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
			
		return list;
	}
	
	//kiem tra ham 
	public static void main(String[] args) {
		PaginationDAO dao = new PaginationDAO();
		//kiem tra ham Count()
//		int count = dao.count("d");
//		System.out.println(count);
		//kiem tra ham search()
		List<Product> list = dao.search("b", 1, 3);
		System.out.println("So luong san pham: " + list.size());
		for(Product p: list) {
			System.out.println(p);
		}
		
	}
}















