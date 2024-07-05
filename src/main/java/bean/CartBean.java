package bean;

import java.util.Date;

public class CartBean {
	private int cart_id;
	private int member_id;
	private int goods_id;
	private String title;
	private String title_img;
	private double price;
	private int quantity;
	private Date reg_date;

	public CartBean(int member_id, int goods_id, int quantity) {
		this.member_id = member_id;
		this.goods_id = goods_id;
		this.quantity = quantity;
	}

	public CartBean(int goods_id, String title, String title_img, int price, int quantity) {
		this.goods_id = goods_id;
		this.title = title;
		this.title_img = title_img;
		this.price = price;
		this.quantity = quantity;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle_img() {
		return title_img;
	}

	public void setTitle_img(String title_img) {
		this.title_img = title_img;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

}