package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.CartBean;
import dbcon.DBUtil;

public class CartDAO {
	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
		return instance;
	}

	private CartDAO() {
	}

	public void addCart(CartBean cart) {
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareCall("{call update_cart(?, ?, ?)}")) {
			pstmt.setInt(1, cart.getMember_id());
			pstmt.setInt(2, cart.getGoods_id());
			pstmt.setInt(3, cart.getQuantity());
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
