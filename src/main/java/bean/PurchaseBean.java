package bean;

import java.util.Date;

public class PurchaseBean {
	private int purchase_id;
	private int member_id;
	private int goods_id;
	private int quantity;
	private String name;
	private String addr;
	private int total_usd;
	private int total_doge;
	private int delivery_state;
	private String transaction_id;
	private Date reg_date;
	private String title_img;
	private String title;
	private int price;

	public PurchaseBean(int purchase_id, int member_id, int goods_id, int quantity, String name, String addr,
			int total_usd, int total_doge, int delivery_state, String transaction_id, Date reg_date, String title_img,
			String title, int price) {
		this.purchase_id = purchase_id;
		this.member_id = member_id;
		this.goods_id = goods_id;
		this.quantity = quantity;
		this.name = name;
		this.addr = addr;
		this.total_usd = total_usd;
		this.total_doge = total_doge;
		this.delivery_state = delivery_state;
		this.transaction_id = transaction_id;
		this.reg_date = reg_date;
		this.title_img = title_img;
		this.title = title;
		this.price = price;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getTotal_usd() {
		return total_usd;
	}

	public void setTotal_usd(int total_usd) {
		this.total_usd = total_usd;
	}

	public int getTotal_doge() {
		return total_doge;
	}

	public void setTotal_doge(int total_doge) {
		this.total_doge = total_doge;
	}

	public int getDelivery_state() {
		return delivery_state;
	}

	public void setDelivery_state(int delivery_state) {
		this.delivery_state = delivery_state;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getTitle_img() {
		return title_img;
	}

	public void setTitle_img(String title_img) {
		this.title_img = title_img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}