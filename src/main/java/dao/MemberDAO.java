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
		int member_id = 0;

		SHA256 sha = SHA256.getInstance();
		String shaPw = null;
		try {
			String orgPw = pw;
			shaPw = sha.getSha256(orgPw.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return member_id;
		}

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select member_id, pw from member where id = ?")) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String dbPw = rs.getString("pw");
					if (BCrypt.checkpw(shaPw, dbPw)) {
						member_id = rs.getInt("member_id");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member_id;
	}

	public void register(MemberBean member) {
		SHA256 sha = SHA256.getInstance();

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("insert into member(id, pw, name, phone, addr) values(?, ?, ?, ?, ?)")) {
			String shaPw = sha.getSha256(member.getPw().getBytes());
			String bcPw = BCrypt.hashpw(shaPw, BCrypt.gensalt());

			pstmt.setString(1, member.getId());
			pstmt.setString(2, bcPw);
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddr());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemberBean getMember(int member_id) {
		MemberBean member = null;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from member where member_id = ?")) {
			pstmt.setInt(1, member_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					member = new MemberBean(rs.getInt("member_id"), rs.getString("id"), rs.getString("pw"),
							rs.getString("name"), rs.getString("phone"), rs.getString("addr"), rs.getDate("reg_date"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
}
