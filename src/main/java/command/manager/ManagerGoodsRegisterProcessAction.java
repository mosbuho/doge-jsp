package command.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbcon.DBUtil;
import process.CommandAction;

public class ManagerGoodsRegisterProcessAction implements CommandAction {
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String uploadedImageName = request.getParameter("uploadedImageName");
		int price = Integer.parseInt(request.getParameter("price"));
		int discount = Integer.parseInt(request.getParameter("discount"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String category = request.getParameter("category");

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO goods (title, description, title_img, price, discount, quantity, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, description);
			pstmt.setString(3, uploadedImageName);
			pstmt.setInt(4, price);
			pstmt.setInt(5, discount);
			pstmt.setInt(6, quantity);
			pstmt.setString(7, category);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return "managerGoodsList.do";
	}
}