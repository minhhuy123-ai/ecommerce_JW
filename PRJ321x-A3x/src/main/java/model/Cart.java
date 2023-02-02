package model;

import java.util.ArrayList;
import java.util.List;

//Lop chua thong tin ve 1 don hang hien tai

public class Cart {
	
	private List<Product> items; //List of item in cart
	private double amount;//Total price amount in cart
	
	public Cart() {
		items = new ArrayList<>();
	}
	
	//add a new product to cart
	public void add(Product ci) {
	//th1: san pham them vao co id trung voi 1 trong cac sp trong cart 
	//-> cong them so luong moi vao so luong hien tai -> return ket thuc chuong trinh
		for(Product x: items) {
			if(ci.getId() == x.getId()) {
				x.setNumber(x.getNumber() + ci.getNumber());
				return;
			}
		}
	//th2: san pham them vao co id ko dung hang id bat ki sp nao trong cart -> them moi sp vao cart
		items.add(ci);
	}
	
	//remove a product from cart
	public void remove(int id) {
	//neu id trung voi bat ki sp nao trong cart thi xoa sp khoi cart + return ket thuc chuong trinh
		for(Product x : items) {
			if(x.getId() == id) {
				items.remove(x);
				return;
			}
		}
	}
	
	//return total price amount of cart - tinh tong tien trong gio hang
	public double getAmount() {
		double s = 0;
		for (Product x : items) {
			s += x.getPrice() * x.getNumber();
		}
		amount = Math.round(s * 100.0) / 100.0;
		return amount;
	}
	
	//return list of product in cart
	public List<Product> getItems(){
		return items;
	}


	
}








