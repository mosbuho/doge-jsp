package command.member;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.MemberBean;
import dao.MemberDAO;
import process.CommandAction;

public class RegisterProcessAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = request.getReader()) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}

		JSONObject jsonRequest = new JSONObject(sb.toString());
		String id = jsonRequest.getString("id");
		String pw = jsonRequest.getString("pw");
		String name = jsonRequest.getString("name");
		String phone = jsonRequest.getString("phone");
		String addr = jsonRequest.getString("addr");
		MemberBean member = new MemberBean(id, pw, name, phone, addr);

		MemberDAO mdao = MemberDAO.getInstance();
		mdao.register(member);

		return null;
	}
}