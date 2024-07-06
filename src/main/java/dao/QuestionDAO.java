package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbcon.DBUtil;

public class QuestionDAO {
	private static QuestionDAO instance = new QuestionDAO();

	public static QuestionDAO getInstance() {
		return instance;
	}

	private QuestionDAO() {
	}

	public void newQuestion(int member_id, int goods_id, String content) {
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("insert into question(member_id, goods_id, content) values(?, ?, ?)")) {
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, goods_id);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 질문 리스트 불러오기
}
