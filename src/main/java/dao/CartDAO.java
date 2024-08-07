package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CartBean;
import dbcon.DBUtil;

public class CartDAO {
	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
		return instance;
	}

	private CartDAO() {
	}

	public int addCart(CartBean cart) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareCall("{call update_cart(?, ?, ?)}")) {
			pstmt.setInt(1, cart.getMember_id());
			pstmt.setInt(2, cart.getGoods_id());
			pstmt.setInt(3, cart.getQuantity());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<CartBean> getCartList(int member_id) {
		ArrayList<CartBean> cartList = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareCall(
						"select c.goods_id, g.title, g.title_img, g.price, c.quantity from cart c join goods g on c.goods_id = g.goods_id where c.member_id = ?")) {
			pstmt.setInt(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CartBean cart = new CartBean(rs.getInt("goods_id"), rs.getString("title"), rs.getString("title_img"),
						rs.getInt("price"), rs.getInt("quantity"));
				cartList.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}

	public int delCart(int member_id) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from cart where member_id = ?")) {
			pstmt.setInt(1, member_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}