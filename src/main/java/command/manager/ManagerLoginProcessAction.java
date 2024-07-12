package command.manager;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.ManagerDAO;
import process.CommandAction;

public class ManagerLoginProcessAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}

		JSONObject jsonRequest = new JSONObject(sb.toString());
		String id = jsonRequest.getString("id");
		String pw = jsonRequest.getString("pw");

		ManagerDAO manager = ManagerDAO.getInstance();
		boolean check = manager.login(id, pw);

		if (check) {
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("admin", check);
		}

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("success", check);
		response.getWriter().write(jsonResponse.toString());
		return null;
	}
}