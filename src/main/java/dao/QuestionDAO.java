package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import bean.AnswerBean;
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
						"insert into question(member_id, goods_id, content) values(?, ?, ?)",
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

	public QuestionBean getQuestion(int question_id) {
		QuestionBean question = null;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from question where question_id = ?")) {
			pstmt.setInt(1, question_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					question = new QuestionBean(rs.getInt("question_id"), rs.getInt("member_id"), rs.getInt("goods_id"),
							rs.getString("content"), rs.getDate("reg_date"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return question;
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

	public int delQuestion(int question_id) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from question where question_id = ?")) {
			pstmt.setInt(1, question_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateQuestion(int question_id, String content) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("update question set content = ? where question_id = ?")) {
			pstmt.setString(1, content);
			pstmt.setInt(2, question_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public HashMap<Integer, ArrayList<QuestionBean>> getQuestionList(int member_id) {
		HashMap<Integer, ArrayList<QuestionBean>> questionMap = new HashMap<Integer, ArrayList<QuestionBean>>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"select q.goods_id, q.content, q.reg_date, g.title, g.title_img from question q join goods g on q.goods_id = g.goods_id where q.member_id = ? order by q.goods_id")) {
			pstmt.setInt(1, member_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int goods_id = rs.getInt("goods_id");

					if (!questionMap.containsKey(goods_id)) {
						questionMap.put(goods_id, new ArrayList<>());
					}
					ArrayList<QuestionBean> questionList = questionMap.get(goods_id);

					QuestionBean question = new QuestionBean(rs.getInt("goods_id"), rs.getString("content"),
							rs.getDate("reg_date"), rs.getString("title"), rs.getString("title_img"));
					questionList.add(question);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionMap;
	}

	public ArrayList<QuestionBean> getAllQuestions() {
		ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("select * from question where done = 0 order by question_id desc")) {
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

	public int done(int question_id) {
		int result = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareCall("update question set done = ?  where question_id = ?")) {
			pstmt.setInt(1, 1);
			pstmt.setInt(2, question_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<QuestionBean> getQuestionsWithAnswers(int goods_id) {
		ArrayList<QuestionBean> list = new ArrayList<>();
		String sql = "SELECT q.*, a.answer_id, a.content AS answer_content, a.reg_date AS answer_reg_date, m.id AS m_id "
				+ "FROM question q LEFT JOIN answer a ON q.question_id = a.question_id "
				+ "INNER JOIN member m ON q.member_id = m.member_id WHERE q.goods_id = ? ORDER BY q.reg_date DESC";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, goods_id);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					QuestionBean question = new QuestionBean(rs.getInt("question_id"), rs.getInt("member_id"),
							rs.getString("content"), rs.getDate("reg_date"), rs.getString("m_id"));
					if (rs.getObject("answer_id") != null) {
						AnswerBean answer = new AnswerBean();
						answer.setAnswer_id(rs.getInt("answer_id"));
						answer.setContent(rs.getString("answer_content"));
						answer.setReg_date(rs.getDate("answer_reg_date"));
						question.setAnswer(answer);
					}
					list.add(question);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
