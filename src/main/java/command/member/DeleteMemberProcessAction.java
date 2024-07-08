package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import process.CommandAction;

public class DeleteMemberProcessAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession(false);
		int member_id = (int) session.getAttribute("member_id");
		MemberDAO mdao = MemberDAO.getInstance();
		mdao.deleteMember(member_id);
		if (session != null) {
			session.invalidate();
		}
		return "/main/main.jsp";
	}
}