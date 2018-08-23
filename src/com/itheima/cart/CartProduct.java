package com.itheima.cart;

public class CartProduct {

	private String pid;
	private String uid;
	private String quantity;
	public String getPid() {
		return pid;
	}
	@Override
	public String toString() {
		return "CartProduct [pid=" + pid + ", uid=" + uid + ", quantity=" + quantity + "]";
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}

