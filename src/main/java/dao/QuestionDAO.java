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

	public int newQuestion(int member_id, int goods_id, String content) {
		int question_id = 0;

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into question(member_id, goods_id, content) values(?,?,?)",
						new String[] { "question_id" })) {
			pstmt.setInt(1, member_id);
			pstmt.setInt(2, goods_id);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					question_id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return question_id;
	}

	public ArrayList<QuestionBean> getQuestions(int goods_id) {
		ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select q.question_id, q.member_id, q.goods_id, q.content, q.reg_date, m.id from question q inner join member m on q.member_id = m.member_id where goods_id = ? order by q.question_id asc")) {
			pstmt.setInt(1, goods_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					QuestionBean question = new QuestionBean(rs.getInt("question_id"), rs.getInt("member_id"),
							rs.getInt("goods_id"), rs.getString("content"), rs.getDate("reg_date"), rs.getString("id"));
					questionList.add(question);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionList;
	}

	public void delQuestion(int question_id) {
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from question where question_id = ?")) {
			pstmt.setInt(1, question_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
