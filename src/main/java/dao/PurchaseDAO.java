package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import bean.PurchaseBean;
import dbcon.DBUtil;

public class PurchaseDAO {
	private static PurchaseDAO instnace = new PurchaseDAO();

	public static PurchaseDAO getInstance() {
		return instnace;
	}

	private PurchaseDAO() {
	}

	public void purchase(int member_id, int goods_id, int quantity, String name, String addr, int total_usd,
			int total_doge, String uuid) {
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into purchase (member_id, goods_id, quantity, name, addr, total_usd, total_doge, transaction_id) values (?, ?, ?, ?, ?, ?, ?, ?)")) {
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, goods_id);
			pstmt.setInt(3, quantity);
			pstmt.setString(4, name);
			pstmt.setString(5, addr);
			pstmt.setInt(6, total_usd);
			pstmt.setInt(7, total_doge);
			pstmt.setString(8, uuid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, ArrayList<PurchaseBean>> getPurchaseList(int member_id) {
		HashMap<String, ArrayList<PurchaseBean>> purchaseMap = new HashMap<String, ArrayList<PurchaseBean>>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select p.purchase_id, p.member_id, p.goods_id, p.quantity, p.name, p.addr, p.total_usd, p.total_doge, p.delivery_state, p.transaction_id, p.reg_date, g.title_img, g.title, g.price "
								+ "FROM purchase p join goods g on p.goods_id = g.goods_id where p.member_id = ? order by p.transaction_id")) {
			pstmt.setInt(1, member_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String transaction_id = rs.getString("transaction_id");

					if (!purchaseMap.containsKey(transaction_id)) {
						purchaseMap.put(transaction_id, new ArrayList<>());
					}

					ArrayList<PurchaseBean> purchaseList = purchaseMap.get(transaction_id);

					PurchaseBean purchase = new PurchaseBean(rs.getInt("purchase_id"), rs.getInt("member_id"),
							rs.getInt("goods_id"), rs.getInt("quantity"), rs.getString("name"), rs.getString("addr"),
							rs.getInt("total_usd"), rs.getInt("total_doge"), rs.getInt("delivery_state"),
							transaction_id, rs.getDate("reg_date"), rs.getString("title_img"), rs.getString("title"),
							rs.getInt("price"));
					purchaseList.add(purchase);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return purchaseMap;
	}
}