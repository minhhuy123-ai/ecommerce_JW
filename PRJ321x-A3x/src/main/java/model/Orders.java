package model;

import java.util.Date;
import java.util.List;

//lop chua thong tin ve 1 don hang, bao gom danh sach san pham cua don hang, thong tin nguoi mua hang

public class Orders {
	
	private int orderId;
	private float price;//total amount of order
	private int status;
	private Date orderDate;
	private String address;//buyer 's address
	private String phoneNumber;
	private List<ProductOrders> lp;
	private String userMail;//buyer's email
	private Date receivedDate;
	private String discount;
	
	public Orders() {
		
	}
	
	public Orders(int orderId, float price, int status, Date orderDate, 
			String address, String phoneNumber, List<ProductOrders> lp, String userMail, Date receivedDate) {
		this.orderId = orderId;
		this.price = price;
		this.status = status;
		this.orderDate = orderDate;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.lp = lp;
		this.userMail = userMail;
		this.receivedDate = receivedDate;
	}
	
	public Orders(String userMail, int status, String discount, String address, String phoneNumber, Date receivedDate) {
		this.userMail = userMail;
		this.status = status;
		this.discount = discount;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.receivedDate = receivedDate;
	}
	
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	public String getDiscount() {
		return this.discount;
	}
	
	public Date getReceivedDate() {
		return this.receivedDate;
	}
	
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	public String getUserMail() {
		return this.userMail;
	}
	
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public Date getOrderDate() {
		long millis=System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);
		return (Date)date;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public List<ProductOrders> getLp(){
		return this.lp;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setLp(List<ProductOrders> lp) {
		this.lp = lp;
	}
	
}
