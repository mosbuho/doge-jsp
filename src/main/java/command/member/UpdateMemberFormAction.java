package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberBean;
import dao.MemberDAO;
import process.CommandAction;

public class UpdateMemberFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int member_id = (int) request.getSession().getAttribute("member_id");
		MemberDAO mdao = MemberDAO.getInstance();
		MemberBean member = mdao.getMember(member_id);

		request.setAttribute("member", member);
		return "/member/updateMemberForm.jsp";
	}
}
