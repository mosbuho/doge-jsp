package command.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberBean;
import dao.MemberDAO;
import process.CommandAction;

public class ManagerUserListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		if ((boolean) request.getSession().getAttribute("admin")) {
			MemberDAO mdao = MemberDAO.getInstance();
			ArrayList<MemberBean> memberList = mdao.getMemberList();
			request.setAttribute("memberList", memberList);
			return "manager/managerUserList.jsp";
		} else {
			return "main/main.jsp";
		}
	}
}