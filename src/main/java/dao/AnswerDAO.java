package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.AnswerBean;
import bean.MemberBean;
import bean.QuestionBean;
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

	public ArrayList<AnswerBean> getAnswerList() {
		ArrayList<AnswerBean> answerList = new ArrayList<AnswerBean>();
		String sql = "SELECT a.answer_id, a.question_id, a.content AS answer_content, "
				+ "a.reg_date AS answer_reg_date, m.id, m.member_id, q.content AS question_content FROM answer a "
				+ "JOIN question q ON a.question_id = q.question_id JOIN member m ON q.member_id = m.member_id";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				AnswerBean answer = new AnswerBean();
				answer.setAnswer_id(rs.getInt("answer_id"));
				answer.setQuestion_id(rs.getInt("question_id"));
				answer.setContent(rs.getString("answer_content"));
				answer.setReg_date(rs.getDate("answer_reg_date"));

				QuestionBean question = new QuestionBean();
				question.setQuestion_id(rs.getInt("question_id"));
				question.setContent(rs.getString("question_content"));
				answer.setQuestion(question);

				MemberBean member = new MemberBean();
				member.setMember_id(rs.getInt("member_id"));
				member.setId(rs.getString("id"));
				answer.setMember(member);

				answerList.add(answer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answerList;
	}

	public int updateAnswer(int answer_id, String content) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareCall("update answer set content = ? where answer_id = ?")) {
			pstmt.setString(1, content);
			pstmt.setInt(2, answer_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delAsnwer(int answer_id) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareCall("delete from answer where answer_id = ?")) {
			pstmt.setInt(1, answer_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
