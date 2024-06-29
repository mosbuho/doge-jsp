package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.MemberDAO;
import process.CommandAction;
import java.io.BufferedReader;

public class LoginProcessAction implements CommandAction {

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

		MemberDAO member = MemberDAO.getInstance();
		boolean check = member.login(id, pw);

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("check", check);

		if (check) {
			request.getSession().setAttribute("id", id);
		}

		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}