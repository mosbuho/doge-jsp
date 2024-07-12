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

		int result = 0;
		QuestionDAO qdao = QuestionDAO.getInstance();
		result = qdao.updateQuestion(question_id, content);

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("success", result != 0);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}