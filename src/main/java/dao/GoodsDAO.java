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
		String sql1 = "select * from goods order by goods_id";
		String sql2 = "select * from goods where cateogry = ? order by goods_id";
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

	public int purchaseGoods(int id, int quantity) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("update goods set quantity = quantity - ? where goods_id = ?")) {
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int registerGoods(GoodsBean goods) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into goods(title, description, title_img, price, discount, quantity, category) values (?, ?, ?, ?, ?, ?, ?)")) {
			pstmt.setString(1, goods.getTitle());
			pstmt.setString(2, goods.getDescription());
			pstmt.setString(3, goods.getTitle_img());
			pstmt.setInt(4, goods.getPrice());
			pstmt.setInt(5, goods.getDiscount());
			pstmt.setInt(6, goods.getQuantity());
			pstmt.setString(7, goods.getCategory());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateGoods(GoodsBean goods) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"update goods set title = ?, description = ?, title_img = ?, price = ?, discount = ?, quantity = ?, category = ? where goods_id = ?")) {
			pstmt.setString(1, goods.getTitle());
			pstmt.setString(2, goods.getDescription());
			pstmt.setString(3, goods.getTitle_img());
			pstmt.setInt(4, goods.getPrice());
			pstmt.setInt(5, goods.getDiscount());
			pstmt.setInt(6, goods.getQuantity());
			pstmt.setString(7, goods.getCategory());
			pstmt.setInt(8, goods.getGoods_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}