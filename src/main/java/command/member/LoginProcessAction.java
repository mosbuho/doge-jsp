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
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}

		JSONObject jsonRequest = new JSONObject(sb.toString());
		String id = jsonRequest.getString("id");
		String pw = jsonRequest.getString("pw");

		MemberDAO member = MemberDAO.getInstance();
		int member_id = member.login(id, pw);

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("member_id", member_id);

		if (member_id != 0) {
			request.getSession().setAttribute("member_id", member_id);
		}

		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}