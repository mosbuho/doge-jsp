package bean;

import java.util.Date;

public class MemberBean {
	private String id;
	private String pw;
	private String phone;
	private String addr;
	private Date reg_Date;

	public MemberBean(String id, String pw, String phone, String addr) {
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.addr = addr;
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