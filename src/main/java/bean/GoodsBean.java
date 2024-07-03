package bean;

import java.util.Date;

public class GoodsBean {
	private int goods_id;
	private String title;
	private int price;
	private String description;
	private String title_img;
	private int discount;
	private int quantity;
	private Date reg_date;

	public GoodsBean(int goods_id, String title, String description, String title_img, int price, int discount,
			int quantity, Date reg_date) {
		this.goods_id = goods_id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.title_img = title_img;
		this.discount = discount;
		this.quantity = quantity;
		this.reg_date = reg_date;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle_img() {
		return title_img;
	}

	public void setTitle_img(String title_img) {
		this.title_img = title_img;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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