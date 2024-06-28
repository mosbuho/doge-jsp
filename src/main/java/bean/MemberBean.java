package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbcon.DBUtil;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class MemberBean {
	private static MemberBean instance = new MemberBean();

	public static MemberBean getInstance() {
		return instance;
	}

	private MemberBean() {
	}

	public boolean login(String id, String pw) {
		boolean result = false;

		SHA256 sha = SHA256.getInstance();
		String shaPw = null;
		try {
			String orgPw = pw;
			shaPw = sha.getSha256(orgPw.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select pw from member where id = ?")) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String dbPw = rs.getString("pw");
					if (pw.equals(dbPw)) {
						result = true;
					}
//					if (BCrypt.checkpw(shaPw, dbPw)) {
//						result = true;
//					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
