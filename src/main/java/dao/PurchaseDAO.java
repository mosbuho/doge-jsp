package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import dbcon.DBUtil;

public class PurchaseDAO {
	private static PurchaseDAO instnace = new PurchaseDAO();

	public static PurchaseDAO getInstance() {
		return instnace;
	}

	private PurchaseDAO() {
	}

	public int purchase(int member_id, int goods_id, int quantity, String addr, String uuid) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into purchase (member_id, goods_id, quantity, addr, transaction_id) values (?, ?, ?, ?, ?)")) {
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, goods_id);
			pstmt.setInt(3, quantity);
			pstmt.setString(4, addr);
			pstmt.setString(5, uuid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}