package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import bean.MemberBean;
import process.CommandAction;

public class LoginProcessAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		MemberBean member = MemberBean.getInstance();
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