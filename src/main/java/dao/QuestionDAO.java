package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.QuestionBean;
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

	public ArrayList<QuestionBean> getQuestions(int goods_id) {
		ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from question where goods_id = ? order by question_id asc")) {
			pstmt.setInt(1, goods_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					QuestionBean question = new QuestionBean(rs.getInt("question_id"), rs.getInt("member_id"),
							rs.getInt("goods_id"), rs.getString("content"), rs.getDate("reg_date"));
					questionList.add(question);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionList;
	}
}
