package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Account;
import model.Cart;
import model.Orders;
import model.Product;
import model.ProductOrders;

//Cac phuong thuc lam viec voi bang Orders

public class OrdersDAO {

	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;

	// insert information of Order to data source, that including list of
	// products in cart (c) and information of buyer in Order (o)
	public void insertOrder(Orders o, Cart c) throws Exception {
		
		//insert new Order
		int orderId = 0;
		
		query = "insert into Orders ([user_mail], [order_status], [order_date], [order_discount_code], [order_address]) "
				+ "values (?,?,?,?,?)";

		try {
			con = new DBContext().getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			// set parameter for statement
			ps.setString(1, o.getUserMail());
			ps.setInt(2, o.getStatus());
			ps.setDate(3, (Date) o.getOrderDate());
			ps.setString(4, o.getDiscount());
			ps.setString(5, o.getAddress());
			int rowAffected = ps.executeUpdate();
			if (rowAffected == 1) {
				// get orderId
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					orderId = rs.getInt(1);
				}
			}
		
			//insert new Order_detail
			for (Product p : c.getItems()) {
				insertOrderDetail(orderId, p.getId(), p.getNumber(), p.getPrice());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// ham lay gia tri cot tu tang
	public void insertOrderDetail(int orderId, int productId, int amount, float price) throws Exception {

		// for insert a new Order_detail
		query = "insert into Orders_detail ([order_id], [product_id], [amount_product], [price_product]) values (?,?,?,?)";
		
		try {
			con = new DBContext().getConnection();
			ps = con.prepareStatement(query);
			// set parameter for statement
			ps.setInt(1, orderId);
			ps.setInt(2, productId);
			ps.setInt(3, amount);
			ps.setFloat(4, price);
			ps.executeUpdate();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}

	// ham hien thi san pham da mua len pay.jsp
	public List<ProductOrders> getAllProductOrders(String mail) {

		List<ProductOrders> listOrder = new ArrayList<>();
		query = "select Orders.order_id, Orders_detail.product_id, Orders_detail.amount_product, Products.product_name\r\n"
				+ "from Orders \r\n" + "inner join Orders_detail on Orders.order_id = Orders_detail.order_id\r\n"
				+ "inner join Products on Orders_detail.product_id = Products.product_id\r\n"
				+ "where Orders.user_mail = ?";

		try {
			con = new DBContext().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mail);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductOrders po = new ProductOrders(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
				listOrder.add(po);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return listOrder;
	}

}
