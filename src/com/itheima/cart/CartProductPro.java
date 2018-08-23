package com.itheima.cart;

import com.itheima.domain.Product;

public class CartProductPro {
	private String uid;
	private String quantity;
	private String subtotal;
	private String pid;
	private String pname;
	private double market_price;
	private double shop_price;
	private String pimage;
	private String pdate;
	private int is_hot;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public int getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "CartProductPro [uid=" + uid + ", quantity=" + quantity + ", subtotal=" + subtotal + ", pid=" + pid
				+ ", pname=" + pname + ", market_price=" + market_price + ", shop_price=" + shop_price + ", pimage="
				+ pimage + ", pdate=" + pdate + ", is_hot=" + is_hot + "]";
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
