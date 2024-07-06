package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.GoodsBean;
import dbcon.DBUtil;

public class GoodsDAO {
	private static GoodsDAO instance = new GoodsDAO();

	public static GoodsDAO getInstance() {
		return instance;
	}

	public ArrayList<GoodsBean> getGoodsList(String category) {
		ArrayList<GoodsBean> goodsList = new ArrayList<>();
		String sql1 = "select * from goods";
		String sql2 = "select * from goods where cateogry = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(category == null ? sql1 : sql2);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				GoodsBean goods = new GoodsBean(rs.getInt("goods_id"), rs.getString("title"),
						rs.getString("description"), rs.getString("title_img"), rs.getInt("price"),
						rs.getInt("discount"), rs.getInt("quantity"), rs.getDate("reg_date"));
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodsList;
	}

	public GoodsBean getGoods(int id) {
		GoodsBean goods = null;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from goods where goods_id = ?")) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					goods = new GoodsBean(rs.getInt("goods_id"), rs.getString("title"), rs.getString("description"),
							rs.getString("title_img"), rs.getInt("price"), rs.getInt("discount"), rs.getInt("quantity"),
							rs.getDate("reg_date"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}

	public void purchaseGoods(int id, int quantity) {
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("update goods set quantity = quantity - ? where goods_id = ?")) {
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}