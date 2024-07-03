package bean;

import java.util.Date;

public class MemberBean {
	private int member_id;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String addr;
	private Date reg_Date;

	public MemberBean(String id, String pw, String name, String phone, String addr) {
		this.id = id;
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
		this.reg_Date = reg_Date;
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

	public Date getReg_Date() {
		return reg_Date;
	}

	public void setReg_Date(Date reg_Date) {
		this.reg_Date = reg_Date;
	}

	@Override
	public String toString() {
		return "id=" + id + ", pw=" + pw + ", phone=" + phone + ", addr=" + addr + ", reg_Date=" + reg_Date;
	}
}