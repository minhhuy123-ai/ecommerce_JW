package model;

//chua thong tin cua san pham trong don hang

public class ProductOrders {
	private int orderId;
	private int productId;
	private int amountProduct;//quantity of selected product
	private String nameProduct;
	
	public ProductOrders(int orderId, int productId, int amountProduct, String nameProduct) {
		this.orderId = orderId;
		this.productId = productId;
		this.amountProduct = amountProduct;
		this.nameProduct = nameProduct;
	}
	
	public String getNameProduct() {
		return this.nameProduct;
	}
	
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
	
	public int getProductId() {
		return this.productId;
	}
	
	public int getAmountProduct() {
		return this.amountProduct;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}
}
