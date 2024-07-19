package command.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.AnswerDAO;
import dao.QuestionDAO;
import process.CommandAction;

public class ManagerNewAwnserProcess implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if ((boolean) request.getSession().getAttribute("admin")) {
			int adaoRes = 0;
			int qdaoRes = 0;
			request.setCharacterEncoding("UTF-8");

			StringBuilder sb = new StringBuilder();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			}

			JSONObject jsonObject = new JSONObject(sb.toString());
			int question_id = jsonObject.getInt("question_id");
			String content = jsonObject.getString("content");
			AnswerDAO adao = AnswerDAO.getInstance();
			adaoRes = adao.newAnswer(question_id, content);

			QuestionDAO qdao = QuestionDAO.getInstance();
			qdaoRes = qdao.done(question_id);

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("success", adaoRes != 0 && qdaoRes != 0);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponse.toString());

		}
		return null;
	}
}