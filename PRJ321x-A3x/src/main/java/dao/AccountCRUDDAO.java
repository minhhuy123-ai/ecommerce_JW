package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Account;
import model.Product;

public class AccountCRUDDAO {
	private Connection conn;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;

	// ham hien thi toan bo tai khoan cua khach
	public List<Account> loadAllAccount() {
		List<Account> list = new ArrayList<>();
		query = "Select * from Account where [account_role] = 0";
		try {
			conn = new DBContext().getConnection(); // mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				// chi set nhung bien can hien thi
				acc.setUsr(rs.getString(1));
				acc.setName(rs.getString(4));
				acc.setAddress(rs.getString(5));
				acc.setPhone(rs.getString(6));
				list.add(acc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		return list;
	}

	// ham hien thi tai khoan dua tren mail
	public Account getAccountByMail(String mail) {
		String query = "Select * from Account where [user_mail] = ?";

		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, mail);
			rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				acc.setUsr(rs.getString(1));
				acc.setPwd(rs.getString(2));
				acc.setRole(rs.getInt(3));
				acc.setName(rs.getString(4));
				acc.setAddress(rs.getString(5));
				acc.setPhone(rs.getString(6));
				return acc;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		return null;
	}

	// Xoa toan bo tai khoan
	public void deleteAllAccount() {
		query = "delete from Account";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}

	// Xoa tai khoan duoc chi dinh
	public void deleteAccount(String mail) {
		query = "delete from Account where [user_mail] = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, mail);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}

	// Them moi tai khoan
	public void insertAccount(String name, String mail, String password, String address, String phone) {
		query = "insert into Account values (?,?,?,?,?,?)";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, mail);
			ps.setString(2, password);
			ps.setInt(3, 0);
			ps.setString(4, name);
			ps.setString(5, address);
			ps.setString(6, phone);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}

	// Cap nhap tai khoan
	public void editAccount(String name, String password, String address, String phone, String mail) {
		query = "update Account set [password] = ?, [user_name] = ?, [user_address] = ?, [user_phone] = ? where [user_mail] = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, password);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, phone);
			ps.setString(5, mail);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception {
		AccountCRUDDAO aCrud = new AccountCRUDDAO();
		aCrud.editAccount("Le thi B", "123", "01 Hai Phong", "123", "aNguyen@mail.com");
		
	}

}
