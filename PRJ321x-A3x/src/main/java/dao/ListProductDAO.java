package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;

//lop cac phuong thuc lam viec voi bang Product

public class ListProductDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// load all products
	public List<Product> getAllProduct() {
		List<Product> list = new ArrayList<>();
		String query = "Select * from Products";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		return list;
	}

	// return the list of products by product name
	public List<Product> search(String characters) throws Exception {
		List<Product> list = new ArrayList<>();
		String query = "Select * from Products where product_name like ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + characters + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return list;
	}

	// check whether search() getAllProduct() work
	public static void main(String[] args) throws Exception {
		ListProductDAO dao = new ListProductDAO();
//		List<Product> list = dao.search("");
		List<Product> list = dao.getAllProduct();
		System.out.println("So luong san pham: " + list.size());
		for (Product o : list) {
			System.out.println(o);
		}
//		System.out.println("------------------------------------------------------------------------------------------------------------");
//		List<Product> list2 = dao.search("a");
//		System.out.println("So luong san pham: " + list2.size());
//		for (Product o : list2) {
//			System.out.println(o);
//		}
	}

	// get the product (by id)
	public Product getProduct(String characters) throws Exception {
		Product p = null;
		String query = "Select * from Products where product_id = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, characters);
			rs = ps.executeQuery();
			while (rs.next()) {
				p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
//			p.setId(rs.getInt(1));
//			p.setName(rs.getString(2));
//			p.setDescription(rs.getString(3));
//			p.setPrice(rs.getFloat(4));
//			p.setSrc(rs.getString(5));
//			p.setType(rs.getString(6));
//			p.setBrand(rs.getString(7))
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return p;
	}

}
