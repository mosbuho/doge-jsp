package bean;

import java.util.Date;

public class QuestionBean {
	private int question_id;
	private int member_id;
	private int goods_id;
	private String content;
	private Date reg_date;
	private String m_id;
	private String title;
	private String title_img;

	public QuestionBean(int goods_id, String content, Date reg_date, String title, String title_img) {
		this.goods_id = goods_id;
		this.content = content;
		this.reg_date = reg_date;
		this.title = title;
		this.title_img = title_img;
	}

	public QuestionBean(int question_id, int member_id, int goods_id, String content, Date reg_date, String m_id) {
		this.question_id = question_id;
		this.member_id = member_id;
		this.goods_id = goods_id;
		this.content = content;
		this.reg_date = reg_date;
		this.m_id = m_id;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
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

}
