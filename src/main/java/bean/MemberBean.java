package bean;

import java.util.Date;

public class MemberBean {
	private int member_id;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String addr;
	private Date reg_date;

	public MemberBean() {
	}

	public MemberBean(String id, String pw, String name, String phone, String addr) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.addr = addr;
	}

	public MemberBean(int member_id, String pw, String name, String phone, String addr) {
		this.member_id = member_id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.addr = addr;
	}

	public MemberBean(int member_id, String id, String pw, String name, String phone, String addr, Date reg_Date) {
		this.member_id = member_id;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.addr = addr;
		this.reg_date = reg_Date;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "id=" + id + ", pw=" + pw + ", phone=" + phone + ", addr=" + addr + ", reg_date=" + reg_date;
	}
}