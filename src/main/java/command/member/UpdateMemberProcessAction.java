package command.member;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.MemberBean;
import dao.MemberDAO;
import process.CommandAction;

public class UpdateMemberProcessAction implements CommandAction {

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

		MemberBean member = new MemberBean(jsonRequest.getInt("member_id"), jsonRequest.getString("pw"),
				jsonRequest.getString("name"), jsonRequest.getString("phone"), jsonRequest.getString("addr"));

		int result = 0;
		JSONObject jsonResponse = new JSONObject();
		MemberDAO mdao = MemberDAO.getInstance();
		result = mdao.updateMember(member);
		jsonResponse.put("success", result != 0);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}