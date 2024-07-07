package command.question;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.QuestionDAO;
import process.CommandAction;

public class UpdateQuestionAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = request.getReader()) {
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}

		JSONObject jsonRequest = new JSONObject(sb.toString());
		int question_id = jsonRequest.getInt("question_id");
		String content = jsonRequest.getString("content");
		QuestionDAO qdao = QuestionDAO.getInstance();
		qdao.updateQuestion(question_id, content);

		return null;
	}
}