package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbcon.DBUtil;

public class AnswerDAO {
	private static AnswerDAO instance = new AnswerDAO();

	public static AnswerDAO getInstance() {
		return instance;
	}

	private AnswerDAO() {
	}

	public int newAnswer(int question_id, String content) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareCall("insert into answer (question_id, content) values (?, ?)")) {
			pstmt.setInt(1, question_id);
			pstmt.setString(2, content);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
