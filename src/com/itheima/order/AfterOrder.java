package com.itheima.order;

import com.itheima.domain.Product;

public class AfterOrder {
	private String pid;
	private String quantity;
	private String uid;
	private int id;
	private String address;
	private String getter;
	private Product product;
	private int subtotal;
	@Override
	public String toString() {
		return "AfterOrder [pid=" + pid + ", quantity=" + quantity + ", uid=" + uid + ", id=" + id + ", address="
				+ address + ", getter=" + getter + "]";
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
}
