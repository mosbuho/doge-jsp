package mngr.enc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbcon.DBUtil;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class PwCrypt {
	private static PwCrypt instance = new PwCrypt();

	public static PwCrypt getInstance() {
		return instance;
	}

	private PwCrypt() {}
	
	/** 비밀번호 암호화 */
	public void cryptProcess() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SHA256 sha = SHA256.getInstance();
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select id, pw from manager");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pstmt = conn.prepareStatement("update manager set pw = ? where id = ?");
				pstmt.setString(1, BCrypt.hashpw(sha.getSha256(rs.getString("pw").getBytes()), BCrypt.gensalt()));
				pstmt.setString(2, rs.getString("id"));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.err.println("pw encryption error : " + e.getMessage());
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			 if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			 if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}
