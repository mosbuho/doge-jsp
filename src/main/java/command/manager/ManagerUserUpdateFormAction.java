package command.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberBean;
import dao.MemberDAO;
import process.CommandAction;

public class ManagerUserUpdateFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int member_id = Integer.parseInt(request.getParameter("member_id"));
		MemberDAO mdao = MemberDAO.getInstance();
		MemberBean member = mdao.getMember(member_id);
		request.setAttribute("member", member);
		return "manager/managerUserUpdateForm.jsp";
	}
}