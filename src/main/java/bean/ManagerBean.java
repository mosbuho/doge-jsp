package bean;

import java.util.Date;

public class ManagerBean {
	private String id;
	private String pw;
	private Date reg_Date;

	public ManagerBean(String id, String pw) {
		this.id = id;
		this.pw = pw;
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

	public Date getReg_Date() {
		return reg_Date;
	}

	public void setReg_Date(Date reg_Date) {
		this.reg_Date = reg_Date;
	}

	@Override
	public String toString() {
		return "id=" + id + ", pw=" + pw + ", reg_Date=" + reg_Date;
	}
}