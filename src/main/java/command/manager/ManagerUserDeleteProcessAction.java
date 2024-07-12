package command.manager;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.MemberDAO;
import process.CommandAction;

public class ManagerUserDeleteProcessAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if ((boolean) request.getSession().getAttribute("admin")) {
			request.setCharacterEncoding("utf-8");

			StringBuilder sb = new StringBuilder();
			try (BufferedReader reader = request.getReader()) {
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			}

			JSONObject jsonRequest = new JSONObject(sb.toString());
			int member_id = jsonRequest.getInt("member_id");
			int result = 0;

			MemberDAO mdao = MemberDAO.getInstance();
			result = mdao.deleteMember(member_id);

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("success", result != 0);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponse.toString());

		}
		return null;
	}
}