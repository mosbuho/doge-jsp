package bean;

import java.util.Date;

public class CartBean {
	private int cart_id;
	private int member_id;
	private int goods_id;
	private int quantity;
	private Date reg_date;

	public CartBean(int member_id, int goods_id, int quantity) {
		this.member_id = member_id;
		this.goods_id = goods_id;
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

	@Override
	public String toString() {
		return "cart_id=" + cart_id + ", member_id=" + member_id + ", goods_id=" + goods_id + ", quantity=" + quantity
				+ ", reg_date=" + reg_date;
	}
}