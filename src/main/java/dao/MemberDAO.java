package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.MemberBean;
import dbcon.DBUtil;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	private MemberDAO() {
	}

	public int login(String id, String pw) {
		int result = 0;

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
				PreparedStatement pstmt = conn.prepareStatement("select member_id, pw from member where id = ?")) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String dbPw = rs.getString("pw");
					if (BCrypt.checkpw(shaPw, dbPw)) {
						result = rs.getInt("member_id");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean register(MemberBean member) {
		SHA256 sha = SHA256.getInstance();

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("insert into member(id,pw,phone,addr) values(?, ?, ?, ?)")) {
			String shaPw = sha.getSha256(member.getPw().getBytes());
			String bcPw = BCrypt.hashpw(shaPw, BCrypt.gensalt());

			pstmt.setString(1, member.getId());
			pstmt.setString(2, bcPw);
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddr());
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
