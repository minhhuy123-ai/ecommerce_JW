package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import context.DBContext;
import model.Account;

public class AccountDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String query;
	
	//Ham kiem tra login thanh cong
	public Account accountLogin(String user_mail, String password) {

		query = "Select * from Account where [user_mail] = ? and password = ?";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user_mail);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
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
	
	//Ham hien thi tai khoan dua tren email
	public Account findAccount(String user_mail) {

		query = "Select * from Account where [user_mail] = ?";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user_mail);
			rs = ps.executeQuery();
			while(rs.next()) {
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
	
	//Ham cat chuoi hien thi ten nguoi dung tren web
	public String getSurName(String fullName) {
		String surName = "";
		for(int i = fullName.indexOf(" ") + 1; i < fullName.length(); i++){
			surName  = surName  + fullName.charAt(i);
		}
		return surName;
	}
	
	//Ham kiem tra user mail dang ki co trung user mail trong he thong khong
	public Account checkAccountExist(String user_mail) {
		
		query = "Select * from Account where [user_mail] = ?";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user_mail);
			rs = ps.executeQuery();
			while(rs.next()) {
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
	
	//Ham dang ki tai khoan moi
	public void signup(String userMail, String pass, String name) {
		query = "insert into Account ([user_mail], [password], [account_role], [user_name], [user_address], [user_phone]) values(?,?,0,?,'','')";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, userMail);
			ps.setString(2, pass);
			ps.setString(3, name);
			ps.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}	
	}
	
	//Ham cap nhat thong tin user
	public void updateInfo(String userMail, String user, String address, String phone) {
		query = "update Account set [user_name] = ?, [user_address] = ?, [user_phone] = ? where [user_mail] = ?";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, address);
			ps.setString(3, phone);
			ps.setString(4, userMail);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
	
	public boolean updateAccount(Account acc) {
		
		boolean f = false;
		
		try {
			query = "update Account set [user_name] = ?, [user_address] = ?, [user_phone] = ? where [user_mail] = ?";
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, acc.getName());
			ps.setString(2, acc.getAddress());
			ps.setString(3, acc.getPhone());
			ps.setString(4, acc.getUsr());
			ps.executeUpdate();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		
		return f;
	}
	
}






